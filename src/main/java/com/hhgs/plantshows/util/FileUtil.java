package com.hhgs.plantshows.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    /**
     * 验证指定路径的文件是否存在  不存在则新建
     * @param path 路径
     */
    public static void checkIsExist(String path){
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 将指定目录的多个文件压缩为一个指定的zip文件
     * @param readPath  读取目录
     * @param writePath 压缩后输出目录
     */
    public static void zipFiles(String readPath, String writePath) {
        File file = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        String dir = writePath.substring(0, writePath.lastIndexOf("/"));
        File test = new File(dir);
        if (!test.exists()) {
            test.mkdirs();
        }
        try {
            file = new File(readPath);
            fos = new FileOutputStream(writePath);
            zos = new ZipOutputStream(fos);
            zos.setComment("多文件处理");
            zipFile(file, zos, "");
        } catch (Exception e) {

            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    static int number = 0;

    private static void zipFile(File infile, ZipOutputStream zos, String dir) throws Exception {
        if (infile.isDirectory()) {
            File[] files = infile.listFiles();
            for (File file : files) {
                zipFile(file, zos, file.getName());
            }
        } else {
            FileInputStream in = new FileInputStream(infile);
            String entryName = infile.getName();
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);

            byte[] buffer = new byte[512];
            while ((number = in.read(buffer)) != -1) {
                zos.write(buffer, 0, number);
            }
            zos.closeEntry();
            in.close();

        }
    }

    /**
     * 验证目录是否存在；
     * 不存在则新建该目录
     * 存在则删除该目录的子目录和子文件
     * @param path 目录
     * @throws Exception 异常
     */
    public static void checkDirAndDel(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {// 不存在则新建目录
            file.mkdirs();
        } else {//存在则删除该目录下的文件
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File temp = files[i];
                    temp.delete();
                }
            }

        }
    }

    /**
     *
     * @return
     */
    public static File createCsv(String filePath) {

        File dir = new File(filePath);
        dir.delete();
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file =  new File(filePath + DateUtil.getCurrentTime() + ".csv");
        return file;
    }


    /**
     * 删除指定文件夹下的所有内容，（包括此文件），递归删除
     * @param file
     */
    public static void deleteDirectory(File file) {
        if (file.isFile()) {
            //表示该文件不是文件夹
            file.delete();
        } else {
            //首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath()+"/"+childFilePath);
                deleteDirectory(childFile);
            }
            file.delete();
        }
    }

    public static void main(String[] args) {
        FileUtil.zipFiles("C:/Users/lenovo/Desktop/kks","C:/Users/lenovo/Desktop/kks.zip");
    }
}
