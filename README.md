# **Fitness Tracker Backend**
## Setup
Runs just the db which is required for compiling (else flyway cries)
``` shell
    docker compose up db --build
```
Compiles our code into .jar file in ```./target```
``` shell
    mvn clean install
```
Runs our Docker container with the db and app images (created using existing ```.jar```)
``` shell
    docker compose up --build --force-recreate --remove-orphans
```

## Database Design
![database_design.png](database_design.png)