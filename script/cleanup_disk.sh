# 磁盘空间清理：定期删除日志文件等

# 获取 /home 目录的占用空间和使用率
HOME_USAGE_PERCENT=$(df -h /home | awk 'NR==2{print $5}' | sed 's/%//')

#删除VOPS日志文件 一个月之前
find /gdsoft/soft/vops/logs -name "*" -mtime +30 -exec rm -rfv {} \;

#删除spring.log
find /gdsoft/soft/tmp/ -name "spring.log*" -mtime +0 -exec rm -rfv {} \;

