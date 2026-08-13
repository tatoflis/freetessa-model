pipeline {
    triggers {
        cron(env.BRANCH_NAME == 'develop' ? '0 0 * * *' : '')
    }
  	options {
	    gitLabConnection gitLabConnection: 'GitLab CIC', useAlternativeCredential: true, jobCredentialId: 'ic-apolo-token'
	    gitlabBuilds builds: ['test', 'analyze', 'deploy']
	    buildDiscarder logRotator(numToKeepStr: '10', daysToKeepStr: '90')
	    disableConcurrentBuilds abortPrevious: true
	    withFolderProperties()
  	}
	agent {
	    dockerfile {
	        filename 'Dockerfile4Jenkins'
	        label 'linux'
	        args '-v /datos/.m2/repository:/home/jenkins/.m2/repository -v /var/run/docker.sock:/var/run/docker.sock -v /usr/bin/docker:/usr/bin/docker'
	    }
	}
    stages {
		stage('Test') {
		    options {
		        gitlabCommitStatus('test')
		    }
		    steps{
				withCredentials([usernamePassword(credentialsId: NEXUS_CREDENTIALS, usernameVariable: 'nexusUsername', passwordVariable: 'nexusPassword')]) {
					configFileProvider([configFile(fileId: 'tessa-settings', variable: 'mavenSettings')]) {
						sh 'mvn -s $mavenSettings -Djacoco.formats=xml clean org.jacoco:jacoco-maven-plugin:0.8.14:prepare-agent verify org.jacoco:jacoco-maven-plugin:0.8.14:report -U -Dnexus.username=$nexusUsername -Dnexus.password=$nexusPassword'
				    }
				}
		    }
		}
		stage("Analyze") {
			options {	
		        gitlabCommitStatus('analyze')
		    }
		    steps {
				withCredentials([usernamePassword(credentialsId: NEXUS_CREDENTIALS, usernameVariable: 'nexusUsername', passwordVariable: 'nexusPassword')]) {
					configFileProvider([configFile(fileId: 'tessa-settings', variable: 'mavenSettings')]) {
						script {
				        	String branchName = BRANCH_NAME.toLowerCase().replaceAll(/[\W]+/, '.')
					        withSonarQubeEnv(SONAR_SERVER) {
					        	sh 'mvn -s $mavenSettings org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.projectKey=es.cic.tessa:tessa-commons:' + branchName + ' -Dsonar.projectName=es.cic.tessa-commons:' + branchName + ' -Dnexus.username=$nexusUsername -Dnexus.password=$nexusPassword'
					        }
					        timeout(time: 5, unit: 'MINUTES') {
					        	waitForQualityGate abortPipeline: false
					        }
				      	}	
				    }
				}
		    }
		}
		stage('Deploy') {
		    options {
		      gitlabCommitStatus('deploy')
		    }
		    when {
		        anyOf { 
		        	branch 'develop'
		        }
		    }
		    steps {
				withCredentials([usernamePassword(credentialsId: HARBOR_CREDENTIALS, usernameVariable: 'harborUsername', passwordVariable: 'harborPassword')]) {
					withCredentials([usernamePassword(credentialsId: NEXUS_CREDENTIALS, usernameVariable: 'nexusUsername', passwordVariable: 'nexusPassword')]) {
						configFileProvider([configFile(fileId: 'tessa-settings', variable: 'mavenSettings')]) {
							sh 'mvn -s $mavenSettings clean deploy -U -Dmaven.test.skip=true -Dnexus.username=$nexusUsername -Dnexus.password=$nexusPassword -Dovh.user=$harborUsername -Dovh.password=$harborPassword'
					    }
				    }
				}
		    }   
		}
    }
    post {
        failure {
            office365ConnectorSend webhookUrl: "https://cicconsulting2.webhook.office.com/webhookb2/47d40d5e-03f0-4241-a4e0-cf20b5659066@e9a6fe96-5b3e-403b-be22-415d019e291d/JenkinsCI/61b6bca814bd4108b0a897f2de473679/b745ad07-2fa6-4d52-8748-cde8cbec405e/V2-XZ8_ndoIr3hEfhtqpWQtufEgwiSq5QWqZ_ycLCweGg1",
                status: 'Failure',
                color: '#E30909'
        }
        success{
            office365ConnectorSend webhookUrl: "https://cicconsulting2.webhook.office.com/webhookb2/47d40d5e-03f0-4241-a4e0-cf20b5659066@e9a6fe96-5b3e-403b-be22-415d019e291d/JenkinsCI/61b6bca814bd4108b0a897f2de473679/b745ad07-2fa6-4d52-8748-cde8cbec405e/V2-XZ8_ndoIr3hEfhtqpWQtufEgwiSq5QWqZ_ycLCweGg1",
                status: 'Success',
                color: '#00D100'
        }
    }  
}