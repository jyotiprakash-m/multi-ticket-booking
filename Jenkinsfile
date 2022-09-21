pipeline{
    agent any
    stages{
        stage("clone"){
            steps{
            retry(3){
               bat "git clone https://github.com/jyotiprakash-m/multi-ticket-booking.git"
               }
            }
        }
        
         stage("packaging + docker image"){
            steps{
            bat "mvn clean package -DskipTests"
            }
        }
        }
}