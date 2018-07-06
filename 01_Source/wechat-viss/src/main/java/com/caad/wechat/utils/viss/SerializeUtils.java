package com.caad.wechat.utils.viss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializeUtils {

    private SerializeUtils() {

    }

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            //os.close();
            // bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtils.closeQuietly(os, bos);
        }
        return rv;
    }

    public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtils.closeQuietly(is, bis);
        }
        return (T) rv;
    }

    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }
}
