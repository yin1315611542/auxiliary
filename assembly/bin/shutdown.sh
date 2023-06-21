#!/bin/sh

CURRENTPATH=$(cd `dirname $0`; pwd)

keywords=(
    auxiliary
)

for keyword in "${keywords[@]}";
do
    ps -ef | grep $keyword | awk '{print $2}' | xargs -r kill -9
done

echo "auxiliary stop complete."