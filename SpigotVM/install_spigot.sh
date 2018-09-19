#!/bin/bash

docker pull itzg/minecraft-server
docker run -d -p 25565:25565 --name mc itzg/minecraft-server