#!/bin/sh
CURRENTPATH=$(cd `dirname $0`; pwd)
nohup java -Xms512m -Xmx512m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8896 -jar auxiliary.jar >/dev/null 2>&1 &

