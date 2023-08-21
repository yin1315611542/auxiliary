#!/bin/bash

# Elasticsearch 地址
ELASTICSEARCH_HOST="localhost:9200"

# 发送HTTP请求并获取任务列表
tasks=$(curl -s "$ELASTICSEARCH_HOST/_cat/tasks?format=json")

# 检查任务列表中是否存在删除任务
if echo "$tasks" | grep -q 'delete/byquery'; then
  echo "存在正在进行的删除任务。"
else
  # 获取 /home 目录的占用空间和使用率
  HOME_USAGE_PERCENT=$(df -h /home | awk 'NR==2{print $5}' | sed 's/%//')
  echo -e "/home目录使用率：$HOME_USAGE_PERCENT%"
  # 如果使用率超过 70%，则删除每个索引前三个月的数据
  if [ $HOME_USAGE_PERCENT -gt 70 ]; then
    # 获取所有索引名称
    INDEX_NAMES=$(curl -s "$ELASTICSEARCH_HOST/_cat/indices" | awk '{print $3}')
    # 循环删除每个索引90之前的数据
    for INDEX_NAME in $INDEX_NAMES; do
      echo -n "删除索引【$INDEX_NAME】前三个月的数据  "
      QUERY="{\"query\":{\"range\":{\"updateTime\":{\"lt\":\"now-50d/d\"}}}}"
      curl -X POST "$ELASTICSEARCH_HOST/$INDEX_NAME/_delete_by_query" -H 'Content-Type: application/json' -d "$QUERY"
      echo
    done
  fi
fi