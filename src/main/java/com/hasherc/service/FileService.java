package com.hasherc.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author hasherc
 * @ 17-8-11
 */
public interface FileService {
    String upload(String uuid, MultipartFile file);

    String deleteFile(String uuid, String fileListJson);

    String getFileList(String uuid);
}
