package com.hasherc.service.impl;

import netscape.javascript.JSUtil;
import util.FileUtil;
import util.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hasherc.consts.StatusCode;
import com.hasherc.dao.FileDao;
import com.hasherc.entity.FileEntity;
import com.hasherc.service.FileService;
import org.apache.commons.io.FileExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author hasherc
 * @ 17-8-11
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override

    public String upload(String userUuid,String orderUuid, String fileUuid, MultipartFile file) {

        FileEntity fileEntity;
        //文件存入硬盘
        try {
            fileEntity = FileUtil.uploadFile(userUuid, fileUuid, file);
            if (fileEntity == null){
                return JsonUtil.resultToJson(StatusCode.STATUS_FILE_ERROR);
            }
            fileEntity.setOrderUuid(orderUuid);
        } catch (FileExistsException e) {
            return JsonUtil.resultToJson(StatusCode.STATUS_FILE_EXIST);
        }

        //文件信息存入数据库
        int inserResult = fileDao.insertFile(fileEntity);
        //如果插入失败，就要将存入硬盘的文件杉树
        if (inserResult != 1) {
            FileUtil.deleteFile(fileEntity.getFilePath());
            JsonUtil.resultToJson(StatusCode.STATUS_FILE_ERROR);
        }

        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);
    }

    @Override
    public String deleteFile(String userUuid, String fileName) {
        //获得文件所在目录
        String directroy = FileUtil.getUserDir(userUuid);
        //如果硬盘上文件删除成功，就将数据库文件删除
        if (FileUtil.deleteFile(directroy + File.separator + fileName)) {
            fileDao.deleteFile(userUuid, fileName);
        }
        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);
    }

    @Override
    public String getFileList(String userUuid) {
        //访问数据库获得文件名列表
        List<String> fileList = fileDao.getFileList(userUuid);
        JSONObject result = JsonUtil.resultToJsonObj(StatusCode.GLOBAL_SUCCESS);
        result.put("fileList", fileList);
        return result.toJSONString();
    }
}
