#!/bin/bash

# MySQL数据库连接参数
MYSQL_HOST="localhost"
MYSQL_USER="root"
MYSQL_PASSWORD="goldencis"
MYSQL_DATABASE="vops_alarm_hub"

# 计算 30 天前的日期
THIRTY_DAYS_AGO=$(date -d "9 days ago" +%Y-%m-%d)


# 执行删除操作的SQL语句
DELETE_QUERY="DELETE FROM vops_alarm_hub.t_alarm WHERE create_time <= '$THIRTY_DAYS_AGO';"

# 使用mysql命令执行删除操作
mysql -h "$MYSQL_HOST" -u "$MYSQL_USER" -p"$MYSQL_PASSWORD" -D "$MYSQL_DATABASE" -e "$DELETE_QUERY"

# 检查mysql命令的退出码
if [ $? -eq 0 ]; then
echo '删除表t_alarm中'$THIRTY_DAYS_AGO'之前的数据成功！'
else
echo '删除表t_alarm中'$THIRTY_DAYS_AGO'之前的数据失败！'
fi
