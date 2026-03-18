#!/bin/bash

KEYCLOAK_URL="http://localhost:8083"
MASTER_USER="admin"
MASTER_PASS="admin"
REALM="biblioteca-dev"
REALM_ADMIN_USER="admin-bib"
REALM_ADMIN_PASS="admin"

echo "Obteniendo token del master..."
MASTER_TOKEN=$(curl -s -X POST "$KEYCLOAK_URL/realms/master/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=admin-cli" \
  -d "username=$MASTER_USER" \
  -d "password=$MASTER_PASS" \
  -d "grant_type=password" | jq -r '.access_token')

if [ "$MASTER_TOKEN" = "null" ] || [ -z "$MASTER_TOKEN" ]; then
  echo "ERROR: No se pudo obtener el token del master."
  exit 1
fi

echo "Creando realm: $REALM..."
curl -s -X POST "$KEYCLOAK_URL/admin/realms" \
  -H "Authorization: Bearer $MASTER_TOKEN" \
  -H "Content-Type: application/json" \
  -d "{
        \"realm\": \"$REALM\",
        \"enabled\": true
      }"

echo "Obteniendo token del nuevo realm..."
REALM_TOKEN=$(curl -s -X POST "$KEYCLOAK_URL/realms/$REALM/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=admin-cli" \
  -d "username=$MASTER_USER" \
  -d "password=$MASTER_PASS" \
  -d "grant_type=password" | jq -r '.access_token')

if [ "$REALM_TOKEN" = "null" ] || [ -z "$REALM_TOKEN" ]; then
  echo "ERROR: No se pudo obtener el token del realm."
  exit 1
fi

echo "Creando roles USER y ADMIN..."
curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM/roles" \
  -H "Authorization: Bearer $REALM_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "USER"}'

curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM/roles" \
  -H "Authorization: Bearer $REALM_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "ADMIN"}'

echo "Creando usuario administrador del realm..."
curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM/users" \
  -H "Authorization: Bearer $REALM_TOKEN" \
  -H "Content-Type: application/json" \
  -d "{
        \"username\": \"$REALM_ADMIN_USER\",
        \"enabled\": true,
        \"credentials\": [{
            \"type\": \"password\",
            \"value\": \"$REALM_ADMIN_PASS\",
            \"temporary\": false
        }]
      }"

echo "Obteniendo ID del usuario..."
USER_ID=$(curl -s -X GET "$KEYCLOAK_URL/admin/realms/$REALM/users?username=$REALM_ADMIN_USER" \
  -H "Authorization: Bearer $REALM_TOKEN" | jq -r '.[0].id')

echo "Asignando roles al usuario..."
ROLE_ADMIN=$(curl -s -X GET "$KEYCLOAK_URL/admin/realms/$REALM/roles/ADMIN" \
  -H "Authorization: Bearer $REALM_TOKEN")

ROLE_USER=$(curl -s -X GET "$KEYCLOAK_URL/admin/realms/$REALM/roles/USER" \
  -H "Authorization: Bearer $REALM_TOKEN")

curl -s -X POST "$KEYCLOAK_URL/admin/realms/$REALM/users/$USER_ID/role-mappings/realm" \
  -H "Authorization: Bearer $REALM_TOKEN" \
  -H "Content-Type: application/json" \
  -d "[$ROLE_ADMIN, $ROLE_USER]"

echo "Proceso completado."
echo "Realm creado: $REALM"
echo "Usuario administrador: $REALM_ADMIN_USER / $REALM_ADMIN_PASS"
echo "Roles creados: ADMIN, USER"

