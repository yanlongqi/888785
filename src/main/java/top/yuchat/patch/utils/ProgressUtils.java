package top.yuchat.patch.utils;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description: 进度条处理工具类
 */
public class ProgressUtils {

    //控制输出的进度条宽度
    private static int maxIndex = 60;

    /**
     * 开始进度条
     */
    public void begin() {
        StringBuffer kg = new StringBuffer();
        for (int i = 0; i < maxIndex; i++) {
            kg.append(" ");
        }
        System.out.print("处理中:00%[>" + kg.toString() + "]");
    }

    private void focusGoto() {
        for (int i = maxIndex + 6; i > 0; i--) {
            System.out.print('\b');
        }
    }

    /**
     * 打印当前进度
     *
     * @param i 当前进度百分比
     */
    public void printCurrentNum(int i) {
        String num = "000" + i;
        num = num.substring(num.length() - 3);
        StringBuffer s = new StringBuffer(num + "%[");
        focusGoto();
        int prec = (i * 100) / 100;
        for (int index = 0; index < maxIndex; index++) {
            int c = (index * 100) / maxIndex;
            if (c < prec) {
                s.append("■");
            } else {
                s.append(" ");
            }
        }
        s.append("]");
        System.out.print(s.toString());
    }

}
