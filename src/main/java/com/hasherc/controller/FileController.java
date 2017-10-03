package com.hasherc.controller;

import Util.JsonUtil;
import com.hasherc.consts.StatusCode;
import com.hasherc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author hasherc
 * @ 17-8-10
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    /*
        文件上传
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "file",required = false) MultipartFile file, HttpSession session) {

        System.out.println(file);
        if (file == null) return JsonUtil.resultToJson(StatusCode.STATUS_UPFILE_NOT_EXIST);
        if (session.getAttribute("uuid") == null){
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        String uuid = session.getAttribute("uuid").toString();
        //用户未登录或超时



        return fileService.upload(uuid, file);
    }

    /*
        删除文件，传入文件名的json list
     */
    @RequestMapping("/deleteFile")
    public String deleteFile(String fileJson, HttpSession session) {

        String uuid = session.getAttribute("uuid").toString();

        if (uuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }

        return fileService.deleteFile(uuid, fileJson);
    }

    /*
        返回用户拥有的所有文件信息
     */
    @RequestMapping("/getFile")
    public String getFileList(HttpSession session) {
        String uuid = (String) session.getAttribute("uuid");
        if (uuid == null) return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        return fileService.getFileList(uuid);
    }

}
