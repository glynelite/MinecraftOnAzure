#!/bin/bash

docker pull itzg/minecraft-server
sudo docker run -e TYPE=PAPER -d -p 25565:25565 -e EULA=TRUE -e PAPER_DOWNLOAD_URL=goo.gl/7ENTX8 --name draypus itzg/minecraft-server --noconsole