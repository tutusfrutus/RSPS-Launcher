package com.tutus.versioncontrol;

import com.tutus.Configuration;
import com.tutus.download.cache.CacheDownloader;

import java.io.*;
import java.net.URL;

public class CacheVersion {

    private static final String VERSION_URL = "http://downloads.arix-rsps.com/version.txt";
    private static final String VERSION_FILE = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_FOLDER_NAME +  File.separator + "cacheVersion.dat";

    public static void run() {
        try{
            double latestVersion = getLatestVersion();
            if(latestVersion > CacheVersion.getCurrentVersion()){
                CacheDownloader.downloadCache();
                OutputStream out = new FileOutputStream(VERSION_FILE);
                out.write(String.valueOf(latestVersion).getBytes());;
            }
        } catch(Exception e){
            System.out.println("Something went wrong Running CacheVersion");
        }
    }

    private static double getCurrentVersion(){
        try {
            BufferedReader localVersion = new BufferedReader(new InputStreamReader(new FileInputStream(VERSION_FILE)));
            return Double.parseDouble(localVersion.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from cacheVersion");
            return 0.1;
        }
    }

    private static double getLatestVersion(){
        try {
            URL versionFile = new URL(VERSION_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(versionFile.openStream()));
            return Double.parseDouble(br.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from URL");
            return -1;
        }
    }
}
