package com.qming.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qming.consts.StatusCode;
import com.qming.consts.StringConsts;
import com.qming.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.JsonUtil;
import util.UUIDUtil;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qming
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
    @ResponseBody
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {

        String userUUID = (String) session.getAttribute(StringConsts.userUuid);

        if (userUUID == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        if (file == null) {
            return JsonUtil.resultToJson(StatusCode.STATUS_UPFILE_NOT_EXIST);
        }

        String orderUUID = (String) session.getAttribute(StringConsts.orderUuid);
        if (orderUUID == null) {
            orderUUID = UUIDUtil.getUUID();
            session.setAttribute(StringConsts.orderUuid, orderUUID);
        }

        int fileCount = (int) session.getAttribute(StringConsts.fileCount);
        if (fileCount == 3) {
            return JsonUtil.resultToJson(StatusCode.GLOBAL_FAIL);
        }
        String fileName = file.getOriginalFilename();

        Map<String, ArrayList<String>> fileMap = (Map<String, ArrayList<String>>) session.getAttribute(StringConsts.fileSessions);
        ArrayList<String> fileNames = fileMap.get("fileNames");
        ArrayList<String> fileUuids = fileMap.get("fileUuids");

        String fileUuid = UUIDUtil.getUUID();
        String result = fileService.upload(userUUID, orderUUID, fileUuid, file);
        JSONObject resultJson = JSON.parseObject(result);
        int status = resultJson.getInteger("status");

        if (status != 1) {
            return result;
        } else {
            fileNames.add(fileName);
            fileUuids.add(fileUuid);
            fileCount++;
            session.setAttribute(StringConsts.fileCount, fileCount);
        }
        resultJson.put("fileName", fileName);
        System.out.println();
        return resultJson.toJSONString();
    }

    /*
        删除文件，传入文件名的json list
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestBody String fileName, HttpSession session) {

        String userUuid = session.getAttribute(StringConsts.userUuid).toString();
        if (userUuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        int fileCount = (int) session.getAttribute(StringConsts.fileCount);
        if (fileCount == 0) {
            return JsonUtil.resultToJson(StatusCode.GLOBAL_FAIL);
        }
        System.out.println(fileName);
        Map<String, ArrayList<String>> fileMap = (Map<String, ArrayList<String>>) session.getAttribute(StringConsts.fileSessions);
        ArrayList<String> fileNames = fileMap.get("fileNames");
        ArrayList<String> fileUuids = fileMap.get("fileUuids");
        if (!fileNames.contains(fileName)) {
            return JsonUtil.resultToJson(StatusCode.GLOBAL_FAIL);
        }
        int index = fileNames.lastIndexOf(fileName);
        String fileUuid = fileUuids.get(index);
        fileService.deleteFile(userUuid, fileName);
        fileNames.remove(index);
        fileUuids.remove(index);
        fileMap.put("fileNames", fileNames);
        fileMap.put("fileUuids", fileUuids);
        fileCount--;
        session.setAttribute(StringConsts.fileCount, fileCount);
        session.setAttribute(StringConsts.fileSessions, fileMap);
        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);
    }

    /*
        返回用户拥有的所有文件信息
     */
    @ResponseBody
    @RequestMapping("/getFile")
    public String getFileList(HttpSession session) {
        String uuid = (String) session.getAttribute("uuid");
        if (uuid == null) {
            return JsonUtil.resultToJson(StatusCode.SESSION_TIMEOUT);
        }
        return fileService.getFileList(uuid);
    }

    @ResponseBody
    @RequestMapping("/fileList")
    public String getfileList(HttpSession session) {
        HashMap<String, ArrayList> fileSessions = (HashMap<String, ArrayList>) session.getAttribute(StringConsts.fileSessions);
        return JSON.toJSONString(fileSessions);
    }

    @RequestMapping("/fileNum")
    public String getfileNum(HttpSession session) {
        JSONObject result = new JSONObject();
        result.put("fileNum", session.getAttribute(StringConsts.fileCount));
        return result.toJSONString();
    }

}
