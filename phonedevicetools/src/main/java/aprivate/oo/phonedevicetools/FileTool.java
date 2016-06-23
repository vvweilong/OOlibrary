package aprivate.oo.phonedevicetools;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.BaseAdapter;

import java.io.File;
import java.io.IOException;


/**
 * Created by zhuxiaolong on 16/5/13.
 */
public class FileTool extends BaseTool {
    private static String TAG="FileTool";

    static File rootDirectory = Environment.getExternalStorageDirectory();
    static String rootDirectoryPath = rootDirectory.getPath();


    /*创建 文件*/
    public static File createFile(String fileName) throws IOException {
        return createFile(fileName, rootDirectoryPath);
    }


    private static File createFile(String fileName, File dir) throws IOException {
        return createFile(fileName, dir.getPath());
    }

    private static File createFile(String fileName, String dirPath) throws IOException {
        File file = new File(dirPath + "/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        logInfor(TAG,"createFile :" + file.exists());
        return file;
    }

    /*创建 目录*/
    /**
     * 指定名字创建目录 目录在默认路径下
     * @param name 目录的名字
     * */
    public static File createDirectory(String name) throws IOException {

        File dir = new File(rootDirectoryPath + "/" + name + "/");
        if (!dir.exists()) {
            dir.createNewFile();
        }
        logInfor(TAG,"createDirectory exists:" + dir.exists());
        return dir;
    }


    private static File createDirectory(String fileName, File dir) throws IOException {
        return createFile(fileName, dir.getPath());
    }


    /**
     * 指定 目录 名字
     *
     *
     * */
    private static File createDirectory(String fileName, String dirPath) throws IOException {
        File file = new File(dirPath + "/" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        logError(TAG,"deleteFile file exists "+file.exists());
        return file;
    }


    /*删除 文件*/


    /**
     * 删除文件
     * @param file 要删除的文件对象
     * @return true 删除成功 false 文件不存在 或 删除失败
     * */
    public static boolean deleteFile(@NonNull File file) {
        if (file.exists()) {
            return file.delete();
        }
        logError(TAG,"deleteFile file not exists");
        return false;
    }

    /**
     * 根据文件名删除文件 在APP 默认目录下
     * @param fileName 要删除的文件名 在默认目录下
     * @return true 删除成功  false 文件不存在 或 删除失败
     * */
    public static boolean deleteFile(String fileName) {
        return deleteFile(fileName, rootDirectoryPath);
    }
    /**
     * 指定文件名 和文件目录 删除文件
     * @param fileName 要删除的文件名
     * @param dirPath 指定文件所在的文件夹
     * @return true 删除成功，false 目录下文件不存在 或 删除失败
     * */
    private static boolean deleteFile(String fileName, String dirPath) {
        File targetFile = new File(dirPath + "/" + fileName);
        return deleteFile(targetFile);
    }




}
