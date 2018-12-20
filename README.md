quick-map是一个分布式的图床服务器.可以提供高性能,高可用的分布式图片访问服务.

# 功能

* 上传图片
* 上传base64格式图片.
* 批量上传图片
* 生成缩略图
* 文件搜索/基于redisearch的自动补全功能
* 支持多种文件格式

# 特性
* 高性能，高可用,支持横向拓展,服务治理使用了spring cloud,文件存储使用了fastdfs
* 基于jwt的无状态权限控制

# 使用方法

## 环境
* 安装Mysql

Centos:  
```
sudo yum install mariadb-server mariadb
```  

Ubuntu:  
```
sudo apt-get install mysql-server
```  
* Redis & Redisearch  

1.安装redis4.0以上版本,支持module

2.在redis安装目录下clone最新版Redisearch(需要安装git)
```
git clone https://github.com/RedisLabsModules/RediSearch.git
```  
3.编译 && 安装(需要cmake,gcc,perl)
```
cd RediSearch
cmake .. -DCMAKE_BUILD_TYPE=RelWithDebInfo
make
```  
4.编译完成后,执行启动Redisearch
```
cd ..
./src/redis-server --loadmodule ./RediSearch/redisearch.so
```


* Fastdfs & Fastdfs-Nginx-Module
