#!/bin/bash
echo "Executing Start Script"
# ------ CAREFUL
# ------ Keeping this in case I need to nuke all Volumes/Containers during local but if we
# ------ have multiple dockerized projects, we probably shouldn't use the commented out part.

#containers_to_check=("kotlin_api" "postgresql")
#
#for name in "${containers_to_check[@]}"; do
#  # Get container IDs
#  container_ids=$(docker ps -a --filter "name=$name" --filter "status=exited" --format "{{.ID}}")
#
#  for id in $container_ids; do
#    echo "docker remove $id"
#    docker remove "$id"
#  done
#done
#
#echo "docker volume prune -f"
#docker volume prune -f

# ------ Moving to DockerFile
#echo "mvn clean install"
#mvn clean install

#echo "docker volume prune -f"
#docker volume prune -f

# bring down if up and also remove associated volumes (prevent db data from staying - flyways complaints when .sql changes)
echo "docker compose down -v"
docker docker compose down -v

echo "docker compose up --build"
docker compose up --build