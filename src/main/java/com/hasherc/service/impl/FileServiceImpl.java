package com.hasherc.service.impl;

import Util.FileUtil;
import Util.JsonUtil;
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

    public String upload(String uuid, MultipartFile file) {

        FileEntity fileEntity;
        //文件存入硬盘
        try {
            fileEntity = FileUtil.uploadFile(uuid, file);
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
    public String deleteFile(String uuid, String fileListJson) {
        //检测json格式
        if (!JsonUtil.valid(fileListJson)) {
            return JsonUtil.resultToJson(StatusCode.STATUS_INVALID_JSON);
        }

        //将json 数组转为List
        List<String> fileNameList = JSON.parseArray(fileListJson, String.class);

        //获得文件所在目录
        String directroy = FileUtil.getUserDir(uuid);
        //遍历并删除文件
        for (String fileName : fileNameList) {
            //如果硬盘上文件删除成功，就将数据库文件删除
            if (FileUtil.deleteFile(directroy + File.separator + fileName)) {
                fileDao.deleteFile(uuid, fileName);
            }
        }

        return JsonUtil.resultToJson(StatusCode.GLOBAL_SUCCESS);
    }

    @Override
    public String getFileList(String uuid) {
        //访问数据库获得文件名列表
        List<String> fileList = fileDao.getFileList(uuid);
        JSONObject result = JsonUtil.resultToJsonObj(StatusCode.GLOBAL_SUCCESS);
        result.put("fileList", fileList);
        return result.toJSONString();
    }
}
