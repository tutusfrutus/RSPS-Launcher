package com.tutus.versioncontrol;

import com.tutus.Configuration;
import com.tutus.download.cache.CacheDownloader;

import java.io.*;

public class ClientVersion {

    CacheDownloader cacheDownloader = new CacheDownloader();

    public void run() {
        try{
            double latestVersion = getLatestVersion();
            if(latestVersion > getCurrentVersion()){
                cacheDownloader.downloadCache();
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

        // TODO MD5 checksum using Configuration.CLIENT_FILE_URL
        return 1;
    }
}
