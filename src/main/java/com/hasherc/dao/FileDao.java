package com.hasherc.dao;

import com.hasherc.entity.FileEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hasherc
 * @ 17-8-11
 */
@Repository
public interface FileDao {
    int insertFile(FileEntity file);

    int deleteFile(@Param("userUuid") String userUuid, @Param("fileName") String fileName);

    List<String> getFileList(@Param("userUuid") String userUuid);

    int getFilePage(@Param("userUuid") String uuid, @Param("fileName") String fileName);
}
