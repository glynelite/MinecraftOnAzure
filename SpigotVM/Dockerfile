FROM forq/minecraft-server

RUN mkdir -m 777 /world-data \
  && chown minecraft:minecraft /world-data

VOLUME /world-data

COPY tmp/config /data/
COPY tmp/plugins /plugins/