package com.hasherc.controller;

import com.hasherc.consts.StringConsts;
import util.JsonUtil;
import com.hasherc.consts.StatusCode;
import com.hasherc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.UUIDUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

        String userUUID = (String) session.getAttribute(StringConsts.userUuid);
        String orderUUID = (String) session.getAttribute(StringConsts.orderUuid);
        int fileCount = (int) session.getAttribute(StringConsts.fileCount);
        Map<String,String> fileSessions = (Map<String, String>) session.getAttribute(StringConsts.fileSessions);

        if (file == null) {
            return JsonUtil.resultToJson(StatusCode.STATUS_UPFILE_NOT_EXIST);
        }

        if (userUUID == null){
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        if (orderUUID == null){
            orderUUID = UUIDUtil.getUUID();
            session.setAttribute(StringConsts.orderUuid,orderUUID);
        }
        //上传文件的uuid
        String result = fileService.upload(userUUID,orderUUID, file);

        if (result.contains("status")){
            return result;
        }else{
            session.setAttribute(StringConsts.fileSessions, fileSessions.put(result, file.getOriginalFilename()));
            session.setAttribute(StringConsts.fileCount,fileCount + 1);
        }

        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);
    }

    /*
        删除文件，传入文件名的json list
     */
    @RequestMapping("/deleteFile")
    public String deleteFile(String fileJson, HttpSession session) {

        String uuid = session.getAttribute(StringConsts.userUuid).toString();

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
        if (uuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        return fileService.getFileList(uuid);
    }

}
