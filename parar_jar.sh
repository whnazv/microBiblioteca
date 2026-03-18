#!/bin/bash

echo " Deteniendo todos los microservicios..."

if [ ! -d "pids" ]; then
  echo " No existe la carpeta pids/. "
  exit 1
fi

for pidfile in pids/*.pid; do
  if [ -f "$pidfile" ]; then
    pid=$(cat "$pidfile")
    name=$(basename "$pidfile" .pid)

    echo " Matando $name (PID $pid)..."
    kill $pid 2>/dev/null

    rm "$pidfile"
  fi
done

echo " Todos los servicios han sido detenidos."
