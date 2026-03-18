#!/bin/bash

echo " Iniciando todos los microservicios..."


start_service() {
  local name=$1
  local jar=$2

  if [ ! -f "$jar" ]; then
    echo " No se encontró $jar"
    exit 1
  fi

  echo " Arrancando $name..."
  nohup java -jar "$jar" > "logs/$name.log" 2>&1 &
  echo $! > "pids/$name.pid"
}

# Crear carpetas si no existen
mkdir -p logs
mkdir -p pids

# Arrancar servicios
start_service "eureka-server" "eureka-server/target/eureka-server-1.0.0.jar"
start_service "api-gateway" "api-gateway/target/api-gateway-1.0.0.jar"
start_service "user-service" "user-service/target/user-service-1.0.0.jar"
start_service "auth-service" "auth-service/target/auth-service-1.0.0.jar"

echo " Todos los servicios están arrancando."
echo " Logs en: ./logs/"
echo " PIDs en: ./pids/"
echo " Para parar todo: ./stop-all.sh"
