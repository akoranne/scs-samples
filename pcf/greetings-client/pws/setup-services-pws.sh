#!/usr/bin/env bash
cf s
cf cs p-config-server trial config-server -c config-server-setup.json
cf cs p-service-registry trial service-registry
cf s
