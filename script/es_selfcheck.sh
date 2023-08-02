#!/bin/bash

# Elasticsearch 集群地址
ELASTICSEARCH_HOST="localhost:9200"

# 获取 /home 目录的占用空间和使用率
HOME_USAGE_PERCENT=$(df -h /home | awk 'NR==2{print $5}' | sed 's/%//')
# shellcheck disable=SC2028
echo -e "/home目录使用率：$HOME_USAGE_PERCENT%"
# 如果使用率超过 70%，则删除每个索引前三个月的数据
if [ $HOME_USAGE_PERCENT -gt 1 ]; then
  # 获取所有索引名称
  INDEX_NAMES=$(curl -s "$ELASTICSEARCH_HOST/_cat/indices" | awk '{print $3}')

  # 循环删除每个索引前三个月的数据
  for INDEX_NAME in $INDEX_NAMES; do
    echo -n "删除索引【$INDEX_NAME】前三个月的数据  "
    QUERY="{\"query\":{\"range\":{\"updateTime\":{\"lt\":\"now-300d/d\"}}}}"
    curl -X POST "$ELASTICSEARCH_HOST/$INDEX_NAME/_delete_by_query" -H 'Content-Type: application/json' -d "$QUERY"
    echo
  done
fi