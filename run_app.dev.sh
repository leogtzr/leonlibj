#!/bin/bash

set -x

export LEONLIB_DB_PASSWORD=${LEONLIB_DB_PASSWORD}
export LEONLIB_DB_USER="leo"
export LEONLIB_DB="leonlib"
export LEONLIB_DB_HOST="leonlib"
# sqlite, postgres or memory
export DB_MODE="sqlite"
export PORT=8180
export RUN_MODE="prod"

if [[ "${1}" == "build" ]]; then
    make
fi

docker-compose -f docker-compose.dev.yml up --build

exit
