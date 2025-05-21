#!/bin/bash
echo "Executing Start Script"

containers_to_check=("kotlin_api" "postgresql")

for name in "${containers_to_check[@]}"; do
  # Get container IDs
  container_ids=$(docker ps -a --filter "name=$name" --filter "status=exited" --format "{{.ID}}")

  for id in $container_ids; do
    echo "docker remove $id"
    docker remove "$id"
  done
done

cd ..

echo "mvn clean install"
mvn clean install

echo "docker compose up --build"
docker compose up --build