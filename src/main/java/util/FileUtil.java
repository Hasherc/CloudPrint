package util;

import com.hasherc.entity.FileEntity;
import org.apache.commons.io.FileExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author hasherc
 * @ 17-8-10
 */
public class FileUtil {

    static String fileDirectoryName = "CloudPrintUserFile";

    public static String getHomePath() {
        return System.getProperty("user.home");
    }


    public static String getFileType(String fileName) {
        if (!fileName.contains(".")) {
            return "";
        }
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileType;
    }

    /**
     * 创建总文件目录
     *
     * @return
     */
    public static void createRootDir() {
        File file = new File(getRootDirPath());
        file.mkdirs();


    }

    /**
     * 得到文件根目录 例如 /home/root/CloudPrint
     *
     * @return
     */
    public static String getRootDirPath() {
        return getHomePath() + File.separator + fileDirectoryName;
    }

    /**
     * 创建用户文件夹
     *
     * @param userUuid 用户uuid
     * @return 创建后的文件夹的绝对路径
     */
    public static void createUserDir(String userUuid) {
        File file = new File(getRootDirPath() + File.separator + userUuid);
        file.mkdirs();
    }

    public static String getUserDir(String userUuid) {
        return getRootDirPath() + File.separator + userUuid;

    }

    /**
     * 将文件存到硬盘
     *
     * @param userUuid 文件所属用户uuid
     * @param file 文件实体
     * @return filename：文件原始名 filePath：文件在硬盘路径及更改过的文件名
     * @throws IOException
     */
    public static FileEntity uploadFile(String userUuid, MultipartFile file) throws FileExistsException {

        String fileName = file.getOriginalFilename();
        File tempFile = new File(getUserDir(userUuid), String.valueOf(fileName));
        System.out.println("filename:" + fileName);
        createUserDir(userUuid);
        if (tempFile.exists()) {
            throw new FileExistsException();
        }

        try {
            tempFile.createNewFile();
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }




        FileEntity fileEntity = new FileEntity();
        fileEntity.setUserUuid(userUuid);
        fileEntity.setFileName(fileName);
        fileEntity.setFilePath(tempFile.getAbsolutePath());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileUuid(UUIDUtil.getUUID());
        fileEntity.setFileType(getFileType(fileName));

        return fileEntity;

    }

    /**
     * 根据文件路径删除文件
     *
     * @param filePath 文件绝对路径
     * @return 删除成功返回true
     */

    public static boolean deleteFile(String filePath) {

        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.delete()) {
            return true;

        } else {
            return false;
        }

    }

}
