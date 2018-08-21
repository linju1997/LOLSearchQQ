#!/usr/bin/env bash
PROJECT="LOLSearchQQ"

rm ${PROJECT} -rf

git clone -b $1 git@github.com:fves/LOLSearchQQ.git

cd ${PROJECT}
mvn clean package -DSkipTest
