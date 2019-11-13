package com.wangke.javastar.job.tools;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Unzip {


    public static void main(String[] args) throws IOException {
        copyFile("E:\\upload\\create\\1436144988371_JL33041594.xml", "E:\\test\\upload");
//        deleteFile("E:\\test\\upload\\");
    }

    /**
     * 移动 文件或者文件夹
     *
     * @param oldPath
     * @param newPath
     * @throws IOException
     */
    public static void moveTo(String oldPath, String newPath) throws IOException {
        copyFile(oldPath, newPath);
        deleteFile(oldPath);
    }

    /**
     * 删除 文件或者文件夹
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();

            for (File f : list) {
                deleteFile(f.getAbsolutePath());
            }
        }
        file.delete();
    }

    /**
     * 复制 文件或者文件夹
     *
     * @param oldPath
     * @param newPath
     * @throws IOException
     */
    public static void copyFile(String oldPath, String newPath) throws IOException {
        System.out.println("copy file from [" + oldPath + "] to [" + newPath + "]");

        File oldFile = new File(oldPath);
        if (oldFile.exists()) {

            if (oldFile.isDirectory()) { // 如果是文件夹
                File newPathDir = new File(newPath);
                newPathDir.mkdirs();
                File[] lists = oldFile.listFiles();
                if (lists != null && lists.length > 0) {
                    for (File file : lists) {
                        copyFile(file.getAbsolutePath(), newPath.endsWith(File.separator) ? newPath + file.getName() : newPath + File.separator + file.getName());
                    }
                }
            } else {
                InputStream inStream = new FileInputStream(oldFile);  //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                write2Out(inStream, fs);
                inStream.close();
            }
        }
    }

    /**
     * 重命名文件
     *
     * @param file
     * @param name
     * @return
     */
    public static File renameFile(File file, String name) {
        String fileName = file.getParent() + File.separator + name;
        File dest = new File(fileName);
        file.renameTo(dest);
        return dest;
    }

    /**
     * 压缩多个文件。
     *
     * @param zipFileName 压缩输出文件名
     * @param files       需要压缩的文件
     * @return
     * @throws Exception
     */
    public static File createZip(String zipFileName, File... files) throws Exception {
        File outFile = new File(zipFileName);
        ZipOutputStream out = null;
        BufferedOutputStream bo = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(outFile));
            bo = new BufferedOutputStream(out);

            for (File file : files) {
                zip(out, file, file.getName(), bo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } finally {
                out.close(); // 输出流关闭
            }
        }
        return outFile;
    }

    /**
     * @param zipFileName 压缩输出文件名
     * @param inputFile   需要压缩的文件
     * @return
     * @throws Exception
     */
    public static File createZip(String zipFileName, File inputFile) throws Exception {
        File outFile = new File(zipFileName);
        ZipOutputStream out = null;
        BufferedOutputStream bo = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(outFile));
            bo = new BufferedOutputStream(out);
            zip(out, inputFile, inputFile.getName(), bo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } finally {
                out.close(); // 输出流关闭
            }
        }
        return outFile;
    }

    private static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception { // 方法重载
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (fl == null || fl.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/")); // 创建创建一个空的文件夹
            } else {
                for (int i = 0; i < fl.length; i++) {
                    zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
                }
            }

        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入 base 文件
            System.out.println(base);
            BufferedInputStream bi = new BufferedInputStream(new FileInputStream(f));

            try {
                write2Out(bi, out);
            } catch (IOException e) {
                //Ignore
            } finally {
                bi.close();// 输入流关闭
            }
        }
    }

    private static void write2Out(InputStream input, OutputStream out) throws IOException {
        byte[] b = new byte[1024];
        int c = 0;
        while ((c = input.read(b)) != -1) {
            out.write(b, 0, c);
            out.flush();
        }
        out.flush();
    }
}
