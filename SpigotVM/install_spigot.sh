#!/bin/bash
# Custom Spigot server install script for Ubuntu 18.4 lts VM

docker pull itzg/minecraft-server
docker run -d -p 25565:25565 --name mc itzg/minecraft-server