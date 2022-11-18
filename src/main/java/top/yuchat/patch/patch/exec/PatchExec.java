package top.yuchat.patch.patch.exec;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import top.yuchat.patch.constant.ParamsEnum;
import top.yuchat.patch.utils.Md5CaculateUtil;
import top.yuchat.patch.utils.Params;
import top.yuchat.patch.utils.ProgressUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class PatchExec implements IPatchExec {

    @Override
    public void run(String... args) {
        JSONArray jsonArray = Params.scanFolderArray;


        try {
            String projectName = args[1];
            String patchVersion = args[2];
            String appPath = Params.getAppsInstallPath(projectName);
            String patchPath = Params.getPatchDescriptionPath(Params.getProjectFolder(projectName), patchVersion);
            JSONObject jsonObject = JSON.parseObject(new FileInputStream(patchPath));
            int len = jsonArray.size();
            for (int i = 0; i < len; i++) {
                JSONObject scanFolder = jsonArray.getJSONObject(i);
                String scan = scanFolder.getString(ParamsEnum.INCLUDE.getName());
                JSONArray ignores = scanFolder.getJSONArray(ParamsEnum.IGNORES.getName());
                String allScanPath = appPath + File.separator + scan;
                List<Path> paths = Files.walk(Paths.get(allScanPath)).filter(Files::isRegularFile).collect(Collectors.toList());
                int allNum = paths.size();
                System.out.println("[patch] 开始扫描：" + allScanPath);
                ProgressUtils progressUtils = new ProgressUtils();
                progressUtils.begin();
                JSONObject fileInfo = jsonObject.getJSONObject(scan);
                dd:
                for (int j = 0; j < allNum; j++) {
                    Path path = paths.get(j);
                    for (int k = 0; k < ignores.size(); k++) {
                        String ig = allScanPath + File.separator + ignores.getString(k);
                        if (path.toString().startsWith(ig)) {
                            continue dd;
                        }
                    }
                    File file = path.toFile();
                    String md5 = Md5CaculateUtil.getMD5(file);
                    String fileMd5 = fileInfo.getString(file.getAbsolutePath());
                    // 如果文件不存在，或者文件被修改过，则进行补丁
                    if (StringUtils.isEmpty(fileMd5) || !md5.equals(fileMd5)) {
                        File targetFile = new File(path.toString().replace(appPath, Params.getPatchDirName(projectName, patchVersion)));
                        File parentFile = targetFile.getParentFile();
                        if (!(parentFile.exists() && parentFile.isDirectory())) {
                            parentFile.mkdirs();
                        }
                        Files.copy(path, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    progressUtils.printCurrentNum((i + 1) * 100 / allNum);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
