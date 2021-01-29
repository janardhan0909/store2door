package com.store2door.api.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.store2door.api.dto.*;
import com.store2door.api.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store2door.api.dto.Store2doorStoresDTO;
import com.store2door.api.model.CategoryItems;
import com.store2door.api.model.Coupons;
import com.store2door.api.model.Store2doorStores;
import com.store2door.api.model.StoreAdmin;
import com.store2door.api.model.StoreCategories;
import com.store2door.api.model.StoreCategoryImages;
import com.store2door.api.model.StoreCategoryItemImages;
import com.store2door.api.model.StoresImages;
import com.store2door.api.model.User;
import com.store2door.api.repository.Store2doorStoreRepository;
import com.store2door.api.security.UserPrincipal;
import com.store2door.api.service.StoresService;
import com.store2door.api.utils.JString;

@Service
public class StoresServiceImpl implements StoresService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	Store2doorStoreRepository store2doorStoreRepository;
	@Autowired
	StoreCategoriesRepository storeCategoriesRepository;
	@Autowired
	CategoryItemsRepository categoryItemsRepository;
	@Autowired
	CategoryImagesRepository categoryImagesRepository;
	@Autowired
	CateroryItemImagesRepository categoryItemsImagesRepository;
	@Autowired
	StoreImagesRepository storeImagesRepository;
	
	@Autowired
	CouponsRepository couponsRepository;
	@Override
	public List<Store2doorStoresDTO> loadAllStoresForSuperAdmin(char deleteFlag) throws Exception {
		List<Store2doorStoresDTO> Store2doorStoresDTOs;
		try {
			Store2doorStoresDTOs = new ArrayList<>();
			List<Store2doorStores> dbData = store2doorStoreRepository.findByDeleteFlag('N');
			for (Store2doorStores data : dbData) {
				Store2doorStoresDTO dto = modelMapper.map(data, Store2doorStoresDTO.class);
				dto.setStoreAdminId(
						data.getStoreAdmins().isEmpty() ? 0 : data.getStoreAdmins().iterator().next().getId());

				List<String> storeImages = new ArrayList<String>();
				data.getStoresImageses().forEach(image -> {
					storeImages.add(image.getImage());
				});
				dto.setStoreImages(storeImages);
				Store2doorStoresDTOs.add(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}
		return Store2doorStoresDTOs;
	}

	@Override
	public List<Store2doorStoresDTO> addStore(Store2doorStoresDTO dto) throws Exception {
		List<Store2doorStoresDTO> Store2doorStoresDTOs = new ArrayList<Store2doorStoresDTO>();
		try {
			if (!JString.isEmpty(dto.getId())) {
				List<StoresImages> storeImages = storeImagesRepository.findByStore2doorStoresId(dto.getId());
				if (!storeImages.isEmpty()) {
					storeImagesRepository.deleteAll(storeImages);
				}
			}
			Store2doorStores Store2doorStores = modelMapper.map(dto, Store2doorStores.class);
			Store2doorStores.setUserByCreatedBy(new User(dto.getUserId()));
			Store2doorStores.setUserByLastUpdatedBy(new User(dto.getUserId()));
			Store2doorStores.setCreatedTime(new Date());
			Store2doorStores.setLastUpdatedTime(new Date());
			Set<StoreAdmin> storeAdmins = new HashSet<StoreAdmin>();
			StoreAdmin storeAdmin = new StoreAdmin(dto.getStoreAdminId(), Store2doorStores, new User(dto.getUserId()));
			storeAdmins.add(storeAdmin);
			Store2doorStores.setStoreAdmins(storeAdmins);
			Set<StoresImages> storesImagesesSet = new HashSet<StoresImages>();
			dto.getStoreImages().forEach(data -> {
				StoresImages image=null;
				if(data.split(",").length>1) 
					image = new StoresImages(Store2doorStores, data.split(",")[1]);
				else 
					image = new StoresImages(Store2doorStores, data);
				
				storesImagesesSet.add(image);
			});
			Store2doorStores.setStoresImageses(storesImagesesSet);
			store2doorStoreRepository.save(Store2doorStores);
			Store2doorStoresDTOs = loadAllStoresForSuperAdmin('N');
		} catch (Exception exception) {
			throw exception;
		}
		return Store2doorStoresDTOs;
	}

	@Override
	public List<StoreCategoriesDTO> loadAllCategoriesForStore(long storeId) throws Exception {
		List<StoreCategoriesDTO> storeCategoriesDTOs = new ArrayList<StoreCategoriesDTO>();
		List<String> imagesList = null;
		try {
			for (StoreCategories dbdata : storeCategoriesRepository
					.findByStore2doorStoresInAndDeleteFlag(new Store2doorStores(storeId), 'N')) {
				imagesList = new ArrayList<String>();
				modelMapper.getConfiguration().setAmbiguityIgnored(true);
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				StoreCategoriesDTO dto = modelMapper.map(dbdata, StoreCategoriesDTO.class);
				for (StoreCategoryImages categoryImages : dbdata.getStoreCategoryImageses().stream()
						.collect(Collectors.toList())) {
					String image = categoryImages.getCategoryImage();
					imagesList.add(image);
				}
				dto.setCategoryImages(imagesList);
				dto.setStoreId(storeId);
				storeCategoriesDTOs.add(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}
		return storeCategoriesDTOs;
	}

	@Override
	public List<StoreCategoriesDTO> addCategory(StoreCategoriesDTO dto, UserPrincipal currentUser) throws Exception {
		List<StoreCategoriesDTO> storeCategoriesDTOs = new ArrayList<StoreCategoriesDTO>();
		try {
			if (!JString.isEmpty(dto.getId())) {
				List<StoreCategoryImages> categoriesImagess = categoryImagesRepository
						.findByStoreCategoriesId(dto.getId());
				if (!categoriesImagess.isEmpty())
					categoryImagesRepository.deleteAll(categoriesImagess);
			}
			StoreCategories storeCategories = modelMapper.map(dto, StoreCategories.class);
			storeCategories.setLastUpdatedTime(new Date());
			storeCategories.setCreatedTime(new Date());
			storeCategories.setstore2doorStores(new Store2doorStores(dto.getStoreId()));
			storeCategories.setUserByCreatedBy(new User(currentUser.getId()));
			storeCategories.setUserByLastUpdatedBy(new User(currentUser.getId()));
			Set<StoreCategoryImages> storesCategoryImagesesSet = new HashSet<StoreCategoryImages>();
			dto.getCategoryImages().forEach(data -> {
				StoreCategoryImages image=null;
				if(data.split(",").length>1) 
					image = new StoreCategoryImages(storeCategories, data.split(",")[1]);
				else 
					image = new StoreCategoryImages(storeCategories, data);
				storesCategoryImagesesSet.add(image);
			});
			storeCategories.setStoreCategoryImageses(storesCategoryImagesesSet);
			storeCategoriesRepository.save(storeCategories);
			storeCategoriesDTOs = loadAllCategoriesForStore(storeCategories.getId());
		} catch (Exception exception) {
			throw exception;
		}
		return storeCategoriesDTOs;
	}

	@Override
	public List<CategoryItemsDTO> loadAllItemsForStore(long categoryId) throws Exception {
		List<CategoryItemsDTO> categoryItemsDTOs = new ArrayList<CategoryItemsDTO>();
		List<String> itemImagesList = null;
		List<CategoryItemRatingDTO> itemRatingDto=new ArrayList<CategoryItemRatingDTO>();
		try {
			List<CategoryItems> categoryItems = categoryItemsRepository
					.findByStoreCategoriesInAndDeleteFlagOrderByDisplayOrderAsc(new StoreCategories(categoryId), 'N');
			for (CategoryItems dbdata : categoryItems) {
				CategoryItemsDTO dto = modelMapper.map(dbdata, CategoryItemsDTO.class);
				itemImagesList = new ArrayList<String>();
				for (StoreCategoryItemImages itemImages : dbdata.getStoreCategoryItemImageses().stream()
						.collect(Collectors.toList())) {
					itemRatingDto=dbdata.getUserOrderItemsHistories().stream().filter(data->data.getRating()!=0).collect(Collectors.toList()).stream().map(data->new CategoryItemRatingDTO(data.getRating(),data.getComments(),data.getUserOrdersHistory().getUser().getName())).collect(Collectors.toList());
					String itemImage = itemImages.getItemImage();
					itemImagesList.add(itemImage);
				}
				dto.setItemsImages(itemImagesList);
				dto.setStoreId((dbdata.getStoreCategories().getstore2doorStores() != null)
						? dbdata.getStoreCategories().getstore2doorStores().getId():0);
				dto.setItemRatingDto(itemRatingDto);
				categoryItemsDTOs.add(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}
		return categoryItemsDTOs;
	}

	@Override
	public List<CategoryItemsDTO> addItem(CategoryItemsDTO dto, UserPrincipal currentUser) throws Exception {
		List<CategoryItemsDTO> storeCategoriesDTOs;
		try {
			if (!JString.isEmpty(dto.getId())) {
				List<StoreCategoryItemImages> itemImages = categoryItemsImagesRepository
						.findByCategoryItemsId(dto.getId());
				if (!itemImages.isEmpty())
					categoryItemsImagesRepository.deleteAll(itemImages);
			}
			CategoryItems categoryItems = modelMapper.map(dto, CategoryItems.class);
			categoryItems.setLastUpdatedTime(new Date());
			categoryItems.setCreatedTime(new Date());
			categoryItems.setStoreCategories(new StoreCategories(dto.getCategoryId()));
			categoryItems.setUserByCreatedBy(new User(currentUser.getId()));
			categoryItems.setUserByLastUpdatedBy(new User(currentUser.getId()));
			Set<StoreCategoryItemImages> storesCategoryItemImagesSet = new HashSet<StoreCategoryItemImages>();
			dto.getItemsImages().forEach(data -> {
				StoreCategoryItemImages image=null;
				if(data.split(",").length>1) 
					image = new StoreCategoryItemImages(categoryItems, data.split(",")[1]);
				else 
					image = new StoreCategoryItemImages(categoryItems, data);
				storesCategoryItemImagesSet.add(image);
			});
			categoryItems.setStoreCategoryItemImageses(storesCategoryItemImagesSet);
			categoryItemsRepository.save(categoryItems);
			storeCategoriesDTOs = loadAllItemsForStore(dto.getCategoryId());
		} catch (Exception exception) {
			throw exception;
		}
		return storeCategoriesDTOs;
	}

	@Override
	public Store2doorStoresDTO loadSelectedStore(long storeId) throws Exception {
		Store2doorStoresDTO dto = new Store2doorStoresDTO();
		try {
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	@Override
	public List<CategoryItemsDTO> loadAllItemsForSearch(long storeId) throws Exception {

		try {
			List<CategoryItemsDTO> loadAllItemsForStore = new ArrayList<CategoryItemsDTO>();
			Store2doorStores store = new Store2doorStores(storeId);
			List<StoreCategories> storeCategories = storeCategoriesRepository.findByStore2doorStoresInAndDeleteFlag(store,'N');
			storeCategories.forEach(categoryData -> {
				categoryData.getCategoryItemses().forEach(itemData -> {
					CategoryItemsDTO dto = new CategoryItemsDTO(itemData.getId(), itemData.getItemName(),
							itemData.getItemDescription(), itemData.getItemPrice(), itemData.getItemQuantity(),
							itemData.getStoreCategoryItemImageses().stream()
									.map(image -> new String(image.getItemImage())).collect(Collectors.toList()));
					loadAllItemsForStore.add(dto);
				});
			});
			return loadAllItemsForStore;
		} catch (Exception exception) {
			throw exception;
		}
	}

	@Override
	public List<CouponsDTO> saveCoupons(CouponsDTO[] couponsList) throws Exception {
		List<CouponsDTO> dtoList=null;
		try {
			dtoList= new ArrayList<>(Arrays.asList(couponsList));
			couponsRepository.deleteAll();
			List<Coupons> saveAll = couponsRepository.saveAll(dtoList.stream().map(data->new Coupons(data.getCouponCode(),data.getExpiryDate(),data.getCouponOffer(),data.getCouponType(),data.getMaximumLimit())).collect(Collectors.toList()));
			dtoList=saveAll.stream().map(data->new CouponsDTO(data.getId(),data.getCouponCode(),data.getExpiryDate(),data.getCouponOffer(),data.getCouponType(),data.getMaximumLimit())).collect(Collectors.toList());
		}catch (Exception e) {
			throw e;
		}
		return dtoList;
	}

	@Override
	public List<CouponsDTO> loadCoupons() throws Exception {
		List<CouponsDTO> dtoList=null;
		try {
			dtoList=couponsRepository.findAll().stream().map(data->new CouponsDTO(data.getId(),data.getCouponCode(),data.getExpiryDate(),data.getCouponOffer(),data.getCouponType(),data.getMaximumLimit())).collect(Collectors.toList());
		}catch (Exception e) {
			throw e;
		}
		return dtoList;
	}
	
}
