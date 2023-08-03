#!/bin/bash

# 下载 Git 2.30.1 的源代码包
wget https://mirrors.edge.kernel.org/pub/software/scm/git/git-2.30.1.tar.gz -P /tmp/

# 解压源代码包
tar -xf /tmp/git-2.30.1.tar.gz -C /tmp/

# 进入解压后的 Git 源代码目录
cd /tmp/git-2.30.1/

# 安装 Git 相关依赖
sudo apt-get update
sudo apt-get install -y curl wget gettext libcurl4-gnutls-dev libexpat1-dev zlib1g-dev libssl-dev

# 配置 Git 的安装目录为 /opt/git
./configure --prefix=/opt/git

# 编译并安装 Git
make && sudo make install

# 将 Git 的可执行文件路径添加到系统 PATH 中
echo 'export PATH=$PATH:/opt/git/bin' >> ~/.bashrc
source ~/.bashrc

# 验证 Git 是否安装成功
git --version