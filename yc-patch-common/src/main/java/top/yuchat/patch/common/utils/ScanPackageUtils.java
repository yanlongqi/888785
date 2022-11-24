package top.yuchat.patch.common.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;


public class ScanPackageUtils {

    /**
     * 扫描包
     *
     * @param packageName 包名
     * @return
     * @throws URISyntaxException
     * @throws ClassNotFoundException
     */
    public static Class<?>[] scan(String packageName) throws URISyntaxException, ClassNotFoundException {
        URL url = Thread.currentThread()
                .getContextClassLoader()
                .getResource(packageName.replace('.', File.separatorChar));
        assert url != null;
        File file = new File(url.toURI());
        return scanFile(file, packageName);
    }

    /**
     * 扫描文件，这里不使用递归，使用出栈入栈方式遍历
     */
    private static Class<?>[] scanFile(File file, String packageName) throws ClassNotFoundException {

        LinkedList<Class<?>> classList = new LinkedList<>();
        LinkedList<File> fileList = new LinkedList<>();
        fileList.push(file);

        while (!fileList.isEmpty()) {
            File targetFile = fileList.pop();
            if (targetFile.isFile() && targetFile.getName().endsWith(".class")) {
                //获取绝对路径
                String path = targetFile.getAbsolutePath();
                //获取包路径
                String packagePath = path.substring(path.indexOf(packageName.replace('.', File.separatorChar)));
                //处理包路径，变成包名的格式
                String className = packagePath.replace(File.separatorChar, '.');
                if (className.endsWith(".class")) {
                    className = className.substring(0, className.lastIndexOf("."));
                }
                //添加class到集合中
                classList.push(Thread.currentThread().getContextClassLoader().loadClass(className));

            } else if (targetFile.isDirectory()) {
                fileList.addAll(Arrays.asList(Objects.requireNonNull(targetFile.listFiles())));
            }
        }
        return classList.toArray(new Class[0]);
    }
}
