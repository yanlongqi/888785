# yc-patch
快速生成程序补丁

# 1. 运行环境和安装
## 1.1 运行环境
    1. 需要安装jdk1.8的运行环境
    2. 编译需要maven环境
## 1.2 安装
```shell
    # 拉取代码 
    git clone https://github.com/yanlongqi/yc-patch.git
    
    # 编译
    mvn clean package
```

> 或者直接下载项目目录下yc-patch.jar

# 2. 使用


```shell
    java -jar [option] -po [originalPath] -pt [targetPacth]
    
    # option 执行类型 
    # begin 开始记录程序的初始文件信息
    # patch 开始打包补丁
    
    # openPath 原始文件夹路径或者补丁文件夹生成路径（根据option的不同来选择）
    
    # targetPacth 目标保存的json文件或者读取的目标源文件（根据option的不同来选择）
    
  
```

帮助
```shell
    # 获得帮助信息 --help
    java -jar yc-patch.jar --hepl
    
    # D:\plus>java -jar yc-patch.jar --hepl
    # 命令执行错误，获取帮助请使用 --help
    # 补丁助手帮助信息：
    # 参数列表：
    # --------------------------------------
    # | begin            | 记录补丁信息      |
    # --------------------------------------
    # | patch            | 执行补丁操作      |
    # --------------------------------------
    # | -po              | 补丁目标文件夹路径 |
    # --------------------------------------
    # | -pt              | 目标文件路径      |
    # --------------------------------------
    # 示例：
    # 记录补丁信息：java -jar yc-patch.jar begin -po /apps/myapp -pt /home/myapp.json
    # 执行补丁操作：java -jar yc-patch.jar patch -po /apps/myapp -pt /home/myapp.json
```

