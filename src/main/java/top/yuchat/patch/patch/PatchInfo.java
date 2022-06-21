package top.yuchat.patch.patch;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import top.yuchat.patch.utils.Md5CaculateUtil;
import top.yuchat.patch.utils.ProgressUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @authoer: yanlongqi
 * @createDate: 2022/6/20
 * @description:
 */
public class PatchInfo {
    private static final String DIR_PATH_KEY = "dirPath";
    private static final String FILE_INFO_KEY = "fileInfo";


    /**
     * 保存原始文件信息
     *
     * @param dirPath    补丁原始目录
     * @param targetPath 原始目录信息保存
     * @throws IOException
     */
    public static void patchBegin(String dirPath, String targetPath) throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(dirPath));
        System.out.println("开始扫描目录");
        List<Path> paths = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        int allNum = paths.size();
        JSONObject json = new JSONObject();
        json.put(DIR_PATH_KEY, dirPath);
        ProgressUtils progressUtils = new ProgressUtils();
        progressUtils.begin();
        JSONObject fileInfo = new JSONObject();
        for (int i = 0; i < allNum; i++) {
            Path path = paths.get(i);
            File file = path.toFile();
            String md5 = Md5CaculateUtil.getMD5(file);
            fileInfo.put(file.getAbsolutePath(), md5);
            progressUtils.printCurrentNum((i + 1) * 100 / allNum);
        }
        json.put(FILE_INFO_KEY, fileInfo);
        System.out.println();

        File file = new File(targetPath);
        FileOutputStream fis = new FileOutputStream(file);
        fis.write(json.toString().getBytes(StandardCharsets.UTF_8));
        fis.close();
        System.out.println("处理完成");
    }


    /**
     * 开始补丁
     *
     * @param dirPath    补丁目标文件
     * @param targetPath 目标补丁信息文件
     * @throws IOException
     */
    public static void patch(String dirPath, String targetPath) throws IOException {
        JSONObject jsonObject = JSON.parseObject(new FileInputStream(targetPath));
        String dirpath = jsonObject.getString(DIR_PATH_KEY);
        JSONObject fileInfo = jsonObject.getJSONObject(FILE_INFO_KEY);
        Stream<Path> walk = Files.walk(Paths.get(dirpath));
        List<Path> paths = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        int allNum = paths.size();
        ProgressUtils progressUtils = new ProgressUtils();
        progressUtils.begin();
        for (int i = 0; i < allNum; i++) {
            Path path = paths.get(i);
            File file = path.toFile();
            String md5 = Md5CaculateUtil.getMD5(file);
            String fileMd5 = fileInfo.getString(file.getAbsolutePath());
            // 如果文件不存在，或者文件被修改过，则进行补丁
            if (StringUtils.isEmpty(fileMd5) || !md5.equals(fileMd5)) {
                File targetFile = new File(path.toString().replace(dirpath, dirPath));
                File parentFile = targetFile.getParentFile();
                if (!(parentFile.exists() && parentFile.isDirectory())) {
                    parentFile.mkdirs();
                }
                Files.copy(path, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            progressUtils.printCurrentNum((i + 1) * 100 / allNum);
        }


    }
}
