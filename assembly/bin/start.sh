#!/bin/sh
CURRENTPATH=$(cd `dirname $0`; pwd)
nohup java -Xms512m -Xmx512m -jar $CURRENTPATH/../lib/auxiliary.jar >/dev/null 2>&1 &