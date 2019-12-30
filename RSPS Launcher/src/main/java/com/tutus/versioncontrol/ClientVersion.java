package com.tutus.versioncontrol;

import com.tutus.Configuration;
import com.tutus.download.client.ClientDownloader;

import java.io.*;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class ClientVersion {

    ClientDownloader clientDownloader = new ClientDownloader();

    public void run() {
        try{
            double latestVersion = getLatestVersion();
            if(latestVersion > getCurrentVersion()){
                try {
                    clientDownloader.downloadClient();
                } catch (IOException e) {
                    e.getStackTrace();
                }
                OutputStream out = new FileOutputStream(Configuration.CLIENT_VERSION_FILE);
                out.write(String.valueOf(latestVersion).getBytes());;
            }
        } catch(Exception e){
            System.out.println("Something went wrong Running CacheVersion");
        }
    }

    private double getCurrentVersion(){
        try {
            BufferedReader localVersion = new BufferedReader(new InputStreamReader(new FileInputStream(Configuration.CLIENT_VERSION_FILE)));
            return Double.parseDouble(localVersion.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from cacheVersion");
            return 0.1;
        }
    }

    private double getLatestVersion() {
        try {
            String versionFile = checkHashURL(Configuration.CLIENT_DOWNLOAD_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader());
            return Double.parseDouble(br.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from URL");
            return -1;
        }
    }

    public static String checkHashURL(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream is = new URL(input).openStream();

            try {
                is = new DigestInputStream(is, md);

                int b;

                while (is.read() != -1) {
                    ;
                }
            } finally {
                is.close();
            }
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < digest.length; i++) {
                sb.append(
                        Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(
                                1));
            }
            return sb.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
