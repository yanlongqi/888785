package top.yuchat.patch.patch.exec;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import top.yuchat.patch.constant.ParamsEnum;
import top.yuchat.patch.utils.Md5CaculateUtil;
import top.yuchat.patch.utils.Params;
import top.yuchat.patch.utils.ProgressUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BeginExec implements IPatchExec {

    @Override
    public void run(String... args) {
        String projectName = args[1];
        JSONArray jsonArray = Params.scanFolderArray;
        String appsInstallPath = Params.getAppsInstallPath(projectName);
        String descriptionPath = Params.getDescriptionFile(projectName);
        int len = jsonArray.size();
        System.out.println("开始扫描目录");
        JSONObject array = new JSONObject();
        try {
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String scan = jsonObject.getString(ParamsEnum.INCLUDE.getName());
                JSONArray ignores = jsonObject.getJSONArray(ParamsEnum.IGNORES.getName());
                String allScanPath = appsInstallPath + File.separator + scan;

                System.out.println("开始扫描：" + allScanPath);
                List<Path> paths = Files.walk(Paths.get(allScanPath)).filter(Files::isRegularFile).collect(Collectors.toList());
                int allNum = paths.size();

                ProgressUtils progressUtils = new ProgressUtils();
                progressUtils.begin();
                JSONObject json = new JSONObject();
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
                    json.put(file.getAbsolutePath(), md5);
                    progressUtils.printCurrentNum((i + 1) * 100 / allNum);
                }
                System.out.println();
                array.put(scan, json);
            }
            File file = new File(descriptionPath);
            FileOutputStream fis = new FileOutputStream(file);
            fis.write(array.toString().getBytes(StandardCharsets.UTF_8));
            fis.close();
            System.out.println("处理完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
