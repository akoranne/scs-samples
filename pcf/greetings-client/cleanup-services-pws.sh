#!/usr/bin/env bash
cf s
cf ds -f service-registry
cf ds -f config-server
cf s
