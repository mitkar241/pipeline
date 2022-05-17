sudo yum install epel-release -y
sudo yum install java -y
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo --no-check-certificate
rpm --import http://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum install jenkins -y
#sudo vi /etc/sysconfig/jenkins
sudo service jenkins start
sudo service jenkins status
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
