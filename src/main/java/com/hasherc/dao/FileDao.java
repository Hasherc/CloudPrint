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

    int deleteFile(@Param("uuid") String uuid, @Param("fileName") String fileName);

    List<String> getFileList(@Param("uuid") String uuid);

    int getFilePage(@Param("uuid") String uuid, @Param("fileName") String fileName);
}
