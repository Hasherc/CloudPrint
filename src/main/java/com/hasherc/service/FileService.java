package com.hasherc.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author hasherc
 * @ 17-8-11
 */
public interface FileService {
    String upload(String userUuid, String orderUuid, String fileUuid,  MultipartFile file);

    String deleteFile(String userUuid, String fileName);

    String getFileList(String userUuid);
}
