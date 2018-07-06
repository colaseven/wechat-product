package com.caad.wechat.utils.viss;

import java.io.*;


public class IoUtils {
    private IoUtils() {

    }

    public static void closeQuietly(java.io.Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable == null) {
                continue;
            }
            try {
                closeable.close();
            } catch (Exception ex) {
                // NOTODO
            }
        }
    }

    public static String getStreamAsString(InputStream stream, String charset) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset), 8192);
            StringWriter writer = new StringWriter();

            char[] chars = new char[8192];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }
            return writer.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            closeQuietly(stream);
        }
    }

    public static void write(InputStream inputstream, OutputStream outputstream, boolean blncloseinputstream, boolean blncloseoutputstream) throws IOException {
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = inputstream.read(b, 0, 1024)) != -1) {
            outputstream.write(b, 0, len);
        }
        if (blncloseinputstream) {
            closeQuietly(inputstream);
        }
        if (blncloseoutputstream) {
            closeQuietly(outputstream);
        }
    }
}
