#!/bin/bash

heroku deploy:jar -j ./target/personal-auth-server-0.0.1-SNAPSHOT.jar -i ./Procfile --app personal-auth-server