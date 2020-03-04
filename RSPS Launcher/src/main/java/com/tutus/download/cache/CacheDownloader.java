package com.tutus.download.cache;

import com.tutus.Configuration;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CacheDownloader {

    private static String zipFilePath = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME;
    private static String destDir = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_FOLDER_NAME;

// downloads the Cache File
    public void downloadCache()
    {
        //deletes jar before downloading new one (if it exists)
        File file = new File(Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("Deleted File");

        // Downloads the cache file
        InputStream inputStream = null;
        try {
            inputStream = new URL(Configuration.CACHE_DOWNLOAD_URL).openStream();
            Files.copy(inputStream, Paths.get(Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Downloaded File");

        // Get this download's progress.
        // TODO JPROGRESSBAR

        // Unzips the Cache's Content
            unzipCache();
    }

    private void unzipCache() {
        System.out.println("Unzipping the Cache");
        ZipFile zipFile = new ZipFile(zipFilePath);
        boolean cacheUpdated = false;
        try {
            zipFile.extractAll(destDir);
            if (zipFile.getProgressMonitor().getResult() == ProgressMonitor.Result.SUCCESS) {
                cacheUpdated = true;
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Removing the old zip file");
            try {
                Files.deleteIfExists(Paths.get(zipFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            cacheUpdated = true;
        }
    }
}
