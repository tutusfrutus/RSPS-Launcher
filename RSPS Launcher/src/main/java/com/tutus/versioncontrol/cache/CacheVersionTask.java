package com.tutus.versioncontrol.cache;

import com.tutus.Configuration;
import com.tutus.download.cache.CacheDownloader;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.net.URL;

public class CacheVersionTask {

    private CacheDownloader cacheDownloader = new CacheDownloader();

    @FXML
    private ProgressBar progressMain;

    /**
     * Checks whether there is a difference between versions (Local versus Host)
     */
    public void checkCacheVersionTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try{
                    double latestVersion = getLatestVersion();
                    if(latestVersion != getCurrentVersion() || Configuration.forceUpdate == true){
                        cacheDownloader.downloadCache();
                        OutputStream out = new FileOutputStream(Configuration.CACHE_VERSION_FILE);
                        out.write(String.valueOf(latestVersion).getBytes());;
                    }
                } catch(Exception e){
                    System.out.println("Something went wrong Running CacheVersion " + e.toString());
                    System.out.println("Something went wrong Running CacheVersion " + e.getStackTrace());
                }
                return null;
            }
};
        new Thread(task).start();
    }

    /**
     * Grabs the current local version from the user's computer
     */
    private double getCurrentVersion(){
        try {
            BufferedReader localVersion = new BufferedReader(new InputStreamReader(new FileInputStream(Configuration.CACHE_VERSION_FILE)));
            return Double.parseDouble(localVersion.readLine());
        } catch (Exception e) {
            System.out.println("Couldn't find latest version from cacheVersion");
            return 0.1;
        }
    }

    /**
     * Grabs the latest online version from the Cache Version URL
     */
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
