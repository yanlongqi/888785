package top.yuchat.patch.patch;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description:
 */
public class HelpInfo {

    public static void printHelpInfo(){

        StringBuffer buffer = new StringBuffer();
        buffer.append("补丁助手帮助信息：\r\n");
        buffer.append("参数列表：\r\n");
        buffer.append("--------------------------------------\r\n");
        buffer.append("| begin            | 记录补丁信息      |\r\n");
        buffer.append("--------------------------------------\r\n");
        buffer.append("| patch            | 执行补丁操作      |\r\n");
        buffer.append("--------------------------------------\r\n");
        buffer.append("| -po              | 补丁目标文件夹路径 |\r\n");
        buffer.append("--------------------------------------\r\n");
        buffer.append("| -pt              | 目标文件路径      |\r\n");
        buffer.append("--------------------------------------\r\n");
        buffer.append("示例：\r\n");
        buffer.append("记录补丁信息：java -jar yc-patch.jar begin -po /apps/myapp -pt /home/myapp.json \r\n");
        buffer.append("执行补丁操作：java -jar yc-patch.jar patch -po /apps/myapp -pt /home/myapp.json\r\n");
        System.out.println(buffer);


    }
}
