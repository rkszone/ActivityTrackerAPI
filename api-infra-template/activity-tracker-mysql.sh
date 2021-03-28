#!/bin/sh

az $AZ_RESOURCE_GROUP=ActivityTracker
az $AZ_DATABASE_NAME=activitytracker2021
az $AZ_LOCATION=westeurope
az $AZ_MYSQL_USERNAME=activitytracker
az $AZ_MYSQL_PASSWORD=Activity*#2021
az $AZ_LOCAL_IP_ADDRESS=217.100.148.210

echo "Creating cloud resources..."

echo "-----------------------------------------------------"
echo "Using environment variables:"
echo "AZ_RESOURCE_GROUP=$AZ_RESOURCE_GROUP"
echo "AZ_LOCATION=$AZ_LOCATION"
echo "AZ_MYSQL_USERNAME=$AZ_MYSQL_USERNAME"
echo "AZ_MYSQL_PASSWORD=$AZ_MYSQL_PASSWORD"
echo "AZ_LOCAL_IP_ADDRESS=$AZ_LOCAL_IP_ADDRESS"

echo "-----------------------------------------------------"
echo "Creating resource group"

az group create \
    --name $AZ_RESOURCE_GROUP \
    --location $AZ_LOCATION \
    | jq

echo "-----------------------------------------------------"
echo "Creating MySQL Server instance"

az mysql server create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_DATABASE_NAME \
    --location $AZ_LOCATION \
    --sku-name B_Gen5_1 \
    --storage-size 5120 \
    --admin-user $AZ_MYSQL_USERNAME \
    --admin-password $AZ_MYSQL_PASSWORD \
    | jq

echo "-----------------------------------------------------"
echo "Configuring MySQL Server firewall"
echo "Allowing local IP address: $AZ_LOCAL_IP_ADDRESS"

az mysql server firewall-rule create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_DATABASE_NAME-database-allow-local-ip \
    --server $AZ_DATABASE_NAME \
    --start-ip-address $AZ_LOCAL_IP_ADDRESS \
    --end-ip-address $AZ_LOCAL_IP_ADDRESS \
    | jq

echo "-----------------------------------------------------"
echo "Configuring MySQL Server database"

az mysql db create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name activitytracker \
    --server-name $AZ_DATABASE_NAME \
    | jq