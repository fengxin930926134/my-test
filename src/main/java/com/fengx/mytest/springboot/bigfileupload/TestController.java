package com.fengx.mytest.springboot.bigfileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Slf4j
@RequestMapping("/file")
@RestController
@CrossOrigin
public class TestController {

    @PostMapping("/normal/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "file is empty";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 文件存储路径
        String filePath = "D:/data/" + UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
        log.info("save file to:" + filePath);
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @PostMapping("/big/upload")
    public String uploadBigFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file == null || file.isEmpty()) {
            return "file is empty";
        }
        try {
            int index = BigFileUploadUtil.uploadBigFile(file, request);
            if (index == -1) {
                // 完成上传
                // 检查是否存在数据库 如果不存在则保存进入数据库
                return "-1";
            } else {
                return index + "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
