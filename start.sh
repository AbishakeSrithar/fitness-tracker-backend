#!/bin/bash
echo "Executing start.sh Script"

# bring down if up and also remove associated volumes (prevent db data from staying - flyways complaints when .sql changes)
echo "docker compose down -v"
docker compose down -v

echo "docker compose up --build"
docker compose up --build