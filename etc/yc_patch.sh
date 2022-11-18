#!/bin/bash
set -e
# 基础路径
base_path="/apps"

# appform安装目录名称
project_name=$1

# 操作类型
type=$2

# appform安装路径
project_path="${base_path}/appform-${project_name}"

# 版本号
version=$3

# 姓名
name="燕陇琪"

# 团队
org="专业服务团队"

# patch基础路径
base_patch_dir="/apps/lqyan/patch"

# patch目录名称
patch_dir_name="JH_Appform_5.3.1_Release_${project_name}"

# patch 路径
patch_dir_patch=${base_patch_dir}/${patch_dir_name}

date=$(date "+%Y%m%d")

# 如果目标文件夹不存在就创建
if [ ! -d "${patch_dir_patch}" ]; then
    mkdir -p "${patch_dir_patch}"
    echo "目标文件夹不存在，已新建：${patch_dir_patch}"
fi

if [ "${type}" = "begin" ]; then
    java -jar /apps/lqyan/tools/yc-patch.jar begin -po "${project_path}" -pt "${patch_dir_patch}/patch_${patch_dir_name}-${date}.json"
    echo "补丁记录完毕，补丁文件路径："
    echo "${patch_dir_patch}/patch_${patch_dir_name}-${date}.json"
    exit 0
fi

patch_dir=${patch_dir_patch}/patch_${patch_dir_name}-${date}

if [ "${type}" = "patch" ]; then
    java -jar /apps/lqyan/tools/yc-patch.jar patch -po "${patch_dir}" -pt "${patch_dir_patch}/patch_${patch_dir_name}-${version}.json"
    echo "文件补丁完成";

    cd "${patch_dir}"
    echo "进入文件夹：${patch_dir}"

    readme_name="readme_${patch_dir_name}-${date}.txt"
    readme_patch="${patch_dir}/patch-version/${readme_name}"
    # 新建readme目录

    if [ ! -d "patch-version" ]; then
        mkdir -p "patch-version"
    fi

    # 删除非必要的文件
    echo "删除非必要文件"
    rm -rf bin DBSchema jhappgateway jhunischeduler jre logstash traefik watchdog download_package install jhfiletransfer jhLicenseServer jhwebproxy logs pgsql tools update work

    # 如果目标文件夹不存在就创建
    if [ ! -d "tomcat" ]; then
        cd tomcat
        echo "进入Tomcat目录"
        rm -rf bin BUILDING.txt conf CONTRIBUTING.md lib LICENSE logs NOTICE README.md RELEASE-NOTES RUNNING.txt shared temp tomcat-juli.jar webapps work
    fi


    echo "生成readme"
    # 自动生成readme内容
    echo "readme_${patch_dir_name}-${date}" >> ${readme_patch}
    echo "" >> ${readme_patch}
    echo "--------------------------------------------------------------------------------------------" >> ${readme_patch}
    echo "" >> ${readme_patch}
    echo "" >> ${readme_patch}
    echo "Patch发布时间：$(date "+%Y-%m-%d")   发布版本：$(date "+%Y-%m-%d")   发布人：${org} - ${name}" >> ${readme_patch}
    echo "" >> ${readme_patch}
    echo "--------------------------------------------------------------------------------------------" >> ${readme_patch}
    echo "" >> ${readme_patch}
    echo "" >> ${readme_patch}
    echo "" >> ${readme_patch}

    echo "* patch目录结构" >> ${readme_patch}
    echo "* 功能介绍" >> ${readme_patch}
    echo "* 替换步骤" >> ${readme_patch}

    echo "" >> ${readme_patch}

    echo "1. patch目录结构" >> ${readme_patch}
    echo "" >> ${readme_patch}
    tree "${patch_dir_patch}/patch_${patch_dir_name}-${version}" >> ${readme_patch}
    echo "" >> ${readme_patch}

    echo "2. 功能介绍" >> ${readme_patch}
    echo "" >> ${readme_patch}


    echo "3. 替换步骤" >> ${readme_patch}
    echo "（1）停止appform，关闭appform全部进程" >> ${readme_patch}
    echo "       	执行formadmin stop后" >> ${readme_patch}
    echo "" >> ${readme_patch}

    echo "（2）备份appfrom目录。" >> ${readme_patch}
    echo "" >> ${readme_patch}

    echo "（3）解压patch覆盖Appform目录" >> ${readme_patch}
    echo "" >> ${readme_patch}


    echo "（4）启动，验证功能" >> ${readme_patch}
    echo "" >> ${readme_patch}

    echo "补丁生成完毕"
    exit 0


fi







