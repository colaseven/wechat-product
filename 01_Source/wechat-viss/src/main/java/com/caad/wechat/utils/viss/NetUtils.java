package com.caad.wechat.utils.viss;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

import com.caad.wechat.controller.SystemController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("netUtils")
public class NetUtils {
    private static Log log = LogFactory.getLog(SystemController.class);

    private static boolean isLoclePortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
            if (!flag) {
                InetAddress inetAddr = getLocalAddress();
                String ip = inetAddr.getHostAddress();
                flag = isPortUsing(ip, port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {

        }
        return flag;
    }

    public static int genServicePort() {
        return genRandomPortNum(8000, 8999);
    }

    private static int genRandomPortNum(int minNum, int maxNum) {
        Random random = new Random();
        int port = 0;
        for (int i = 0; i < 10; i++) {
            port = random.nextInt(maxNum) % (maxNum - minNum + 1) + minNum;
            if (!isLoclePortUsing(port)) {
                return port;
            }

        }
        return port;
    }


    public static InetAddress getLocalAddress() {
        try {
            if (isWindows()) {
                return InetAddress.getLocalHost();
            }

            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                InetAddress inetAddr = getHostAddr("eth1", netInterface);
                return (inetAddr == null) ? getHostAddr("eth0", netInterface) : inetAddr;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    private static InetAddress getHostAddr(String netInterName, NetworkInterface netInterface) {
        if (netInterName.equals(netInterface.getName())) {
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress inetAddr = (InetAddress) addresses.nextElement();
                if (inetAddr != null && inetAddr instanceof Inet4Address) {
                    //return ip.getHostAddress();
                    return inetAddr;
                }
            }
        }
        return null;
    }

    private static boolean isWindows() {
        boolean isWindows = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindows = true;
        }
        return isWindows;
    }

//    public static void main(String[] args){
//    	/*for (int i = 0; i < 100; i++) {
//    		System.out.println(genRandomPortNum(8000, 8999));
//
//		}*/
//    	System.out.println(getLocalAddress().getHostAddress());
//    	System.out.print("\ndone!");
//    }
}
