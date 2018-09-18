sudo apt-get update -y
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common -y
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-get update -y
sudo apt-get install docker-ce -y
sudo docker run -e TYPE=PAPER -d -p 25565:25565 -e EULA=TRUE -e PAPER_DOWNLOAD_URL=goo.gl/7ENTX8 --name teamsmc itzg/minecraft-server --noconsole