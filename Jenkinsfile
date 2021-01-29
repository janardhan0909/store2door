node{
    stage('SCM Checkout'){
		checkout([$class: 'GitSCM', branches: [[name: 'main']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']],submoduleCfg: [], 
		userRemoteConfigs: [[credentialsId: 'Git_Id', url: 'https://github.com/vdmavendodu/store-2-door-api.git']]])
	}
	stage('Maven Build'){
	    def mvnHome = tool name: 'maven', type: 'maven'
		def mvnCMD = "${mvnHome}/bin/mvn"
	    bat "${mvnCMD} clean install -DskipTests"
	}
	stage('Build Docker Image'){
		bat "docker build -f Dockerfile -t store2door-uat ."
	}
	
	stage ('Push Image to ECR'){
        docker.withRegistry('https://519425210903.dkr.ecr.ap-south-1.amazonaws.com/store2door-uat', 'ecr:ap-south-1:store2door-jenkins-use') {
        docker.image('store2door-uat').push('latest')
      }
	}
	
	stage ('Remove Local Image'){
        bat "docker rmi store2door-uat:latest"
        bat "docker rmi 519425210903.dkr.ecr.ap-south-1.amazonaws.com/store2door-uat:latest"
	}
	
	stage ('Start the ECS service'){
        echo "Yet to implement"
	}

}
