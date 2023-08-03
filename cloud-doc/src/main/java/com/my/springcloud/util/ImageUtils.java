package com.my.springcloud.util;

import java.io.*;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;

public class ImageUtils {
    public static File singleFileEnhancement(File f) {
        String thumbnailPathName = "";
        File thumbnailFile = null;
        if (f.isFile()) {
            long size = f.length();
            System.out.println("size--2---->" + (size / 1024));
            double scale = 1.0d;
            if (size >= 400 * 1024) {
                if (size > 0) {
                    scale = (400 * 1024f) / size;
                }
            }

            System.out.println("scale--->" + scale);

            thumbnailPathName = f.getPath();
            if (thumbnailPathName.contains(".png")) {
                thumbnailPathName = thumbnailPathName.replace(".png", ".jpg");
            }
            System.out.println("thumbnailPathName--->" + thumbnailPathName);

            if (size < 200 * 1024) {
                try {
                    Thumbnails.of(f.getPath()).scale(1f).outputFormat("jpg").toFile(thumbnailPathName);
                    thumbnailFile = new File(thumbnailPathName);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    Thumbnails.of(f.getPath()).scale(1f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailPathName);
                    thumbnailFile = new File(thumbnailPathName);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return thumbnailFile;
    }

    public static void sizeEnhancement(File path) {

        File[] tempList = path.listFiles();
        for (File f : tempList) {
            if (f.isFile()) {
                //拼接后台文件名称
                System.out.println("path--->" + f.getPath());
                System.out.println("getName--->" + f.getName());
                System.out.println("getParent---->" + f.getParent());
                long size = f.length();
                System.out.println("size--->" + size);
                System.out.println("size--2---->" + (size / 1024));
                double scale = 1.0d;
                if (size >= 400 * 1024) {
                    if (size > 0) {
                        scale = (400 * 1024f) / size;
                    }
                }

                System.out.println("scale--->" + scale);

                String thumbnailPathName = f.getPath();
                if (thumbnailPathName.contains(".png")) {
                    thumbnailPathName = thumbnailPathName.replace(".png", ".jpg");
                }
                System.out.println("thumbnailPathName--->" + thumbnailPathName);

                if (size > 160 * 1024) {
                    try {
                        Thumbnails.of(f.getPath()).scale(1f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailPathName);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }


            } else if (f.isDirectory()) {
                sizeEnhancement(f);
            }
        }


    }

    /**
     * 获得图片的base64码
     * @param src
     * @return
     */
    public static String getImageBase(String src) {
        if (src == null || src == "") {
            return "";
        }
        File file = new File(src);
        if (!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
