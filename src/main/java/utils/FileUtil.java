package utils;

import java.io.*;

/**
 * 文件操作工具类
 *
 * @author wangxin
 * @version 2020/9/17 10:07
 */
public class FileUtil {

    /**
     * byte[]转成临时File去使用
     * @param fileBytes
     * @return
     * @throws IOException
     */
    public static File bytesToTempFile(byte[] fileBytes) throws IOException {
        // 创建临时文件
        final File file = File.createTempFile("temp", ".search");
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileBytes);
        } finally {
            //关闭临时文件
            fos.flush();
            fos.close();
            //程序退出时删除临时文件
            file.deleteOnExit();
        }
        return file;
    }

    /**
     * byte[]转成File
     * @param fileBytes
     * @param filePath
     * @return
     * @throws IOException
     */
    public static File bytesToFile(byte[] fileBytes, String filePath) throws IOException {
        File file = new File(filePath);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(fileBytes);
        } finally {
            //关闭临时文件
            fos.flush();
            fos.close();
            //程序退出时删除临时文件
//			file.deleteOnExit();
        }
        return file;
    }


    /**
     * File转成bytes
     * @param file
     * @return
     */
    public static byte[] FileTobytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * InputStream转byte[]
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = inputStream.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }


    /**
     * 复制InputStream，因为流只能用一次，多次用需要复制（慎用）
     * @param input
     * @return
     */
    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
