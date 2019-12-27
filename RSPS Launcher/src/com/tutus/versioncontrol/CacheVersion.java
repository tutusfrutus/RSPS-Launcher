package com.tutus.versioncontrol;

import com.tutus.Configuration;
import com.tutus.download.cache.CacheDownloader;

import java.io.*;
import java.net.URL;

public class CacheVersion {

    public static final String VERSION_URL = "http://downloads.arix-rsps.com/version.txt";
    public static final String VERSION_FILE = Configuration.CACHE_SAVE_DIR + "cacheVersion.dat";

    public static void run() {
        try{
            double latestVersion = getLatestVersion();
            if(latestVersion > CacheVersion.getCurrentVersion()){
                CacheDownloader.downloadCache();
                OutputStream out = new FileOutputStream(VERSION_FILE);
                out.write(String.valueOf(latestVersion).getBytes());;
            }
        } catch(Exception e){
            handleException(e);
        }
    }

    private static double getCurrentVersion(){
        try {
            BufferedReader localVersion = new BufferedReader(new InputStreamReader(new FileInputStream(VERSION_FILE)));
            return Double.parseDouble(localVersion.readLine());
        } catch (Exception e) {
            return 0.1;
        }
    }

    private static double getLatestVersion(){
        try {
            URL versionFile = new URL(VERSION_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(versionFile.openStream()));
            return Double.parseDouble(br.readLine());
        } catch (Exception e) {
            handleException(e);
            return -1;
        }
    }

    private static void handleException(Exception e) {
        //TODO
    }
}
