package com.tutus.versioncontrol;

import com.tutus.Configuration;
import com.tutus.download.cache.CacheDownloader;

import java.io.*;
import java.net.URL;

public class CacheVersion implements Runnable {

    private CacheDownloader cacheDownloader = new CacheDownloader();

    public void run() {
        try{
            double latestVersion = getLatestVersion();
            if(latestVersion > getCurrentVersion()){
                cacheDownloader.downloadCache();
                OutputStream out = new FileOutputStream(Configuration.CACHE_VERSION_FILE);
                out.write(String.valueOf(latestVersion).getBytes());;
            }
        } catch(Exception e){
            System.out.println("Something went wrong Running CacheVersion");
        }
    }

    private double getCurrentVersion(){
        try {
            BufferedReader localVersion = new BufferedReader(new InputStreamReader(new FileInputStream(Configuration.CACHE_VERSION_FILE)));
            return Double.parseDouble(localVersion.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from cacheVersion");
            return 0.1;
        }
    }

    private double getLatestVersion(){
        try {
            URL versionFile = new URL(Configuration.CACHE_VERSION_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(versionFile.openStream()));
            return Double.parseDouble(br.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from URL");
            return -1;
        }
    }
}
