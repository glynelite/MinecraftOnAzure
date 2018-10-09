#!/bin/bash

apt-get install
docker pull itzg/minecraft-server
docker run --restart=always -e TYPE=PAPER -d -p 25565:25565 -e EULA=TRUE -e PAPER_DOWNLOAD_URL=goo.gl/3fuFgC --name spigot itzg/minecraft-server --noconsole
