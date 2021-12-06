package com.lanzong.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 多文件上传类
 */
@RestController
public class FileUploadsController {

    //注意这里不要少/，不然创建目录会出点小问题，dd会和文件名组合到一起，导致找不到文件
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/uploads")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest req){
        List<String> successFile = new ArrayList<>();
        List<String> failFile = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            //设置保存路径为项目运行目录下的/uploadFile目录，并通过日期对文件进行归类
            String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
            String format = sdf.format(new Date());
            File folder = new File(realPath+format);
            if(!folder.isDirectory()){
                folder.mkdirs();
            }
            //给上传的文件重命名，避免文件名冲突
            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
            try {
                //文件保存操作
                uploadFile.transferTo(new File(folder,newName));
                //返回文件访问路径
                String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/demo1/uploadFile/"+format+newName;
                successFile.add(filePath);
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            failFile.add(newName);
            continue;
        }
        String result="成功的上传文件：";
        for (String s : successFile) {
            result += s;
        }
        result += "失败的文件有：";
        for (String s1: failFile) {
            result += s1;
        }
        return result;
    }
}
