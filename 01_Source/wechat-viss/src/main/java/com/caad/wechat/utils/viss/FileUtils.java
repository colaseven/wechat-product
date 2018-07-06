package com.caad.wechat.utils.viss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 文件操作工具类
 */
public final class FileUtils {
    public static String DEFAULT_CHARSET_TO_WRITE = "GBK";
    public static int INT_ARRAY_LENGTH = 4 * 256 * 1024;//定义字节数组的长度

    private FileUtils() {

    }

    /**
     * 字符集定义
     */
    static class CharsetEncoding {
        /**
         * 编码格式.
         */
        public String encoding = "UTF-8";

        /**
         * BOM的长度.
         */
        public int bomlength = 0;
    }

    /**
     * 从classpath路径中获取文件内容。
     *
     * @param clazz    调用的类
     * @param filename 文件路径
     * @return
     * @throws IOException
     */
    public static InputStream openInputStreamInClasspath(Class<?> clazz, String filename) throws IOException {
        InputStream inputstream = clazz.getResourceAsStream(filename);
        return inputstream;
    }

    /**
     * 文件是否存在。
     *
     * @param file
     * @return boolean
     * @throws IOException
     */
    public static boolean existsFile(File file) throws IOException {
        return file.exists() && !file.isDirectory();
    }

    /**
     * 文件是否存在。
     *
     * @param filename
     * @return boolean
     * @throws IOException
     */
    public static boolean existsFile(String filename) throws IOException {
        return existsFile(new File(filename));
    }

    /**
     * 文件夹是否存在。
     *
     * @param file
     * @return boolean
     * @throws IOException
     */
    public static boolean existsDirectory(File file) throws IOException {
        return file.exists() && file.isDirectory();
    }

    /**
     * 文件夹是否存在。
     *
     * @param dirname
     * @return boolean
     * @throws IOException
     */
    public static boolean existsDirectory(String dirname) throws IOException {
        return existsDirectory(new File(dirname));
    }

    /**
     * 强制生成空目录。
     * 同时，确保将父目录也一并创建。
     *
     * @param dirname
     * @throws IOException
     */
    public static void createNewDir(String dirname) throws IOException {
        createNewDir(new File(dirname));
    }


    /**
     * 强制生成空目录。
     *
     * @param file
     * @throws IOException
     */
    public static void createNewDir(File file) throws IOException {
        if (!file.exists()) {
            createNewFileOrDir(file, "d");
        }
    }

    /**
     * 强制生成空文件。
     *
     * @param filename 文件路径
     * @throws IOException
     */
    public static void createNewFile(String filename) throws IOException {
        createNewFile(new File(filename));
    }

    /**
     * 强制生成空文件
     *
     * @param file
     * @throws IOException
     */
    public static void createNewFile(File file) throws IOException {
        if (!file.exists()) {
            createNewFileOrDir(file, "f");
        }
    }


    /**
     * 强制生成空文件或目录。
     *
     * @param file 文件
     * @param type 类型    d:强制生成目录   f:强制生成文件
     * @throws IOException
     */
    private static void createNewFileOrDir(File file, String type) throws IOException {
        if (type == null) {
            throw new IOException("文件类型未知！");
        } else if (type.equals("d")) {
            boolean bln = file.mkdirs();
            if (!bln) {
                throw new IOException("文件夹创建失败！");
                //log.info("d is created! " + file.getName());
            }
        } else if (type.equals("f")) {
            boolean bln = file.getParentFile().exists() ? true : file.getParentFile().mkdirs();
            if (!bln) {
                throw new IOException("文件的文件夹创建失败！");
            }
            bln = file.createNewFile();
            if (!bln) {
                throw new IOException("文件创建失败！");
                //log.info("f is created! " + file.getName());
            }
        } else {
            throw new IOException("文件类型未知！" + type);
        }
    }

    /**
     * 从流中获取文本内容，自动进行编码识别和转换。
     *
     * @param inputstream
     * @return String
     */
    public static String getContentStringAndCloseInputStream(InputStream inputstream) {
        if (inputstream == null) {
            return null;
        }
        //
        StringBuffer sb = new StringBuffer();
        CharsetEncoding ce = null;
        try {
            byte[] ary = new byte[200000];
            for (int arylen = inputstream.read(ary); arylen > 0; arylen = inputstream.read(ary)) {
                if (ce == null) {
                    ce = findCharsetEncoding(ary, arylen);
                }
                sb.append(new String(ary, ce.bomlength, arylen - ce.bomlength, ce.encoding));
                ce.bomlength = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputstream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    /**
     * 获取文件内容。
     *
     * @param filename 文件存放路径
     * @return byte[]
     */
    public static byte[] getContent(String filename) {
        File file = new File(filename);
        if (file == null || !file.exists()) {
            return null;
        }

        byte[] aryByte = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            aryByte = new byte[(int) file.length()];
            fis.read(aryByte);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return aryByte;
    }

    /**
     * 获取文件内容。
     *
     * @param filename 文件存放路径
     * @return InputStream
     * @throws IOException
     */
    public static InputStream getContentStream(String filename) throws IOException {
        File file = new File(filename);
        if (file == null || !file.exists()) {
            return null;
        }
        InputStream is = new java.io.FileInputStream(file);
        return is;
    }

    /**
     * 获取指定字符集的文件内容。
     *
     * @param filename
     * @param charset  指定的字符集
     * @return String
     */
    public static String getContentString(String filename, String charset) {
        try {
            byte[] ary = getContent(filename);
            return ary == null ? null : new String(ary, charset);
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    /**
     * 获取文件内容。
     *
     * @param filename
     * @return String
     */
    public static String getContentString(String filename) {
        return getContentByAutoDetectEncoding(filename);
    }

    /**
     * 自动监测字符编码，从文件获取字符串。
     *
     * @param filename
     * @return String
     */
    public static String getContentByAutoDetectEncoding(String filename) {
        String content = null;
        File file = new File(filename);
        if (file == null || !file.exists()) {
            return null;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            byte[] ary = new byte[(int) file.length()];
            int arylen = is.read(ary);
            CharsetEncoding ce = findCharsetEncoding(ary, arylen);
            content = new String(ary, ce.bomlength, arylen - ce.bomlength, ce.encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 自动检测字符编码。
     * <pre>GBK 亦采用双字节表示，总体编码范围为 8140-FEFE，首字节在 81-FE 之间，尾字节在 40-FE 之间，剔除 xx7F 一条线。总计 23940 个码位，共收入 21886 个汉字和图形符号，其中汉字（包括部首和构件）21003 个，图形符号 883 个。
     * 全部编码分为三大部分：
     * 1. 汉字区。包括：
     * a. GB 2312 汉字区。即 GBK/2: B0A1-F7FE。收录 GB 2312 汉字 6763 个，按原顺序排列。
     * b. GB 13000.1 扩充汉字区。包括：
     * (1) GBK/3: 8140-A0FE。收录 GB 13000.1 中的 CJK 汉字 6080 个。
     * (2) GBK/4: AA40-FEA0。收录 CJK 汉字和增补的汉字 8160 个。CJK 汉字在前，按 UCS 代码大小排列；增补的汉字（包括部首和构件）在后，按《康熙字典》的页码／字位排列。
     * 2. 图形符号区。包括：
     * a. GB 2312 非汉字符号区。即 GBK/1: A1A1-A9FE。其中除 GB 2312 的符号外，还有 10 个小写罗马数字和 GB 12345 增补的符号。计符号 717 个。
     * b. GB 13000.1 扩充非汉字区。即 GBK/5: A840-A9A0。BIG-5 非汉字符号、结构符和“○”排列在此区。计符号 166 个。
     * 3. 用户自定义区：分为(1)(2)(3)三个小区。
     * (1) AAA1-AFFE，码位 564 个。
     * (2) F8A1-FEFE，码位 658 个。
     * (3) A140-A7A0，码位 672 个。
     * 第(3)区尽管对用户开放，但限制使用，因为不排除未来在此区域增补新字符的可能性。
     * </pre>
     *
     * @param ary
     * @param arylen
     * @return CharsetEncoding
     */
    public static CharsetEncoding findCharsetEncoding(byte[] ary, int arylen) {
        CharsetEncoding ce = new CharsetEncoding();
        //
        int lenbom = 0;
        if (ary.length <= 3) {
            ce.encoding = "GBK";
            ce.bomlength = 0;
        } else if (ary[0] == (byte) 0xEF && ary[1] == (byte) 0xBB && ary[2] == (byte) 0xBF) {
            ce.encoding = "UTF-8";
            lenbom = 3;
        } else if (ary[0] == (byte) 0xff && ary[1] == (byte) 0xfe) {
            ce.encoding = "UTF-16";
            ce.bomlength = 2;
        } else if (ary[0] == (byte) 0xfe && ary[1] == (byte) 0xff) {
            ce.encoding = "UTF-16BE";
            ce.bomlength = 3;
        } else { // 默认
            ce.encoding = "GBK";
            ce.bomlength = 0;
            for (int i = lenbom; i < arylen; i++) {
                if ((ary[i] & (byte) 0x80) == (byte) 0x80) {
                    byte b0 = ary[i];
                    byte b1 = ary[i + 1];
                    if ((b0 >= (byte) 0xB0 && b0 <= (byte) 0xF7) && (b1 >= (byte) 0xA1 && b1 <= (byte) 0xFE) // GBK之GB2312的汉字区。
                            || (b0 >= (byte) 0xA1 && b0 <= (byte) 0xA9) && (b1 >= (byte) 0xA1 && b1 <= (byte) 0xFE) // GBK之GB2312的非汉字符号区。
                            ) {
                        i += 1;
                        continue;
                    } else {
                        // log.warn("识别该字符串时，第"+i+"位置的汉字不在GBK码表范围内，本字符串自动为UTF-8。");
                        ce.encoding = "UTF-8";
                        break;
                    }
                }
            }
        }
        // log.info(ce.encoding);
        //
        return ce;
    }


    /**
     * 存入文件内容。
     *
     * @param filename 存入的文件路径
     * @param content  存入文件的内容
     * @param charset  存入文件指定的字符集
     * @throws IOException
     */
    public static void setContentString(String filename, String content, String charset) throws IOException {
        setContent(filename, content.getBytes(charset));
    }

    /**
     * 存入文件内容。
     *
     * @param filename 存入的文件路径
     * @param arybyte  存入文件的字节码
     */
    public static void setContent(String filename, byte[] arybyte) {
        File file = null;
        FileOutputStream fos = null;
        try {
            file = new File(filename);
            createNewFile(file);
            fos = new FileOutputStream(file);
            fos.write(arybyte);
            fos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 存入文件内容。
     *
     * @param filename
     * @param is
     */
    public static void setContent(String filename, InputStream is) {
        File file = null;
        FileOutputStream fos = null;
        try {
            file = new File(filename);
            createNewFile(file);
            fos = new FileOutputStream(file);
            byte[] b = new byte[INT_ARRAY_LENGTH];
            int len = 0;
            do {
                len = is.read(b);
                fos.write(b, 0, len);
            } while (len == INT_ARRAY_LENGTH);
            fos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                is.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 增加内容到文件中
     *
     * @param filePath 文件路径
     * @param text     需要增加的文本
     */
    public static void addTextToFile(String filePath, String text) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8");
            writer.write(text);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 清空文件内容
     *
     * @param filename
     */
    public static void cleanFile(String filename) {
        File f = new File(filename);
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件。
     *
     * @param filename 文件路径
     */
    public static void delete(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                boolean bln = file.delete();
                if (!bln) {
                    throw new IOException(String.valueOf(bln));
                }
            }
        } catch (Exception ex) {
            //log.info("文件删除失败！" + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
