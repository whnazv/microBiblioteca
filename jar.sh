#!/bin/bash

GREEN="\e[32m"
RED="\e[31m"
RESET="\e[0m"

SERVICES=(
  "eureka-server"
  "auth-service"
  "user-service"
  "api-gateway"
)

echo -e "${GREEN}Compilando todos los microservicios...${RESET}"

for SERVICE in "${SERVICES[@]}"; do
    echo -e "${GREEN}Compilando $SERVICE...${RESET}"

    cd "$SERVICE" || { echo -e "${RED}No se pudo acceder a $SERVICE${RESET}"; exit 1; }

    mvn clean install -DskipTests
    if [ $? -ne 0 ]; then
        echo -e "${RED}Error compilando $SERVICE${RESET}"
        exit 1
    fi

    echo -e "${GREEN}$SERVICE compilado correctamente${RESET}"
    cd ..
done

echo -e "${GREEN}Todos los servicios fueron compilados correctamente.${RESET}"

