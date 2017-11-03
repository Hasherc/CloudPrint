package com.qming.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author qming
 * @ 17-8-11
 */
public interface FileService {
    String upload(String userUuid, String orderUuid, String fileUuid, MultipartFile file);

    String deleteFile(String userUuid, String fileName);

    String getFileList(String userUuid);
}
