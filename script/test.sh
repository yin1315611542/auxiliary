#!/bin/bash

# Elasticsearch节点的URL
ES_URL="http://localhost:9200"

# 发送HTTP请求并获取任务列表
tasks=$(curl -s "$ES_URL/_cat/tasks?format=json")

# 检查任务列表中是否存在删除任务
if echo "$tasks" | grep -q '"action":"delete"'; then
  echo "存在正在进行的删除任务。"
else
  echo "不存在正在进行的删除任务。"
fi