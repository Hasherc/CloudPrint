package com.hasherc.entity;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @author hasherc
 * @ 17-8-11
 */
public class FileEntity {

    private String uuid;
    private String fileUuid;
    private String fileName;
    private String filePath;
    private String fileType;
    private Date uploadTime;
    private Long fileSize;
    private int filePages;

    public FileEntity() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getFilePages() {
        return filePages;
    }

    public void setFilePages(int filePages) {
        this.filePages = filePages;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
