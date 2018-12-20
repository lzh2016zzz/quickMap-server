quick-map是一个分布式的图床服务器.可以提供高性能,高可用的分布式图片访问服务.

# 功能

* 上传图片.
* 上传base64编码图片.
* 批量上传图片
* 生成缩略图
* 文件搜索/基于redisearch的自动补全功能
* 支持多种文件格式
* 高性能,高可用的图片访问服务

# 特性
* 高性能，高可用,支持横向拓展,使用spring cloud实现服务治理,文件存储/访问使用了fastdfs + nginx 
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
* 安装Redis & Redisearch  

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
4.编译完成后,执行Redisearch

```
cd ..
./src/redis-server --loadmodule ./RediSearch/redisearch.so
```

或者在```redis.conf ```中加入配置: ``` loadmodule /路径/redisearch.so ```


* 安装Fastdfs & Fastdfs-Nginx-Module

参考 :https://blog.csdn.net/qq_17770183/article/details/79397687
