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

    private boolean cacheUpdated = false;

// downloads the Cache File
    public void downloadCache() throws IOException
    {
        //deletes jar before downloading new one (if it exists)
        File file = new File(Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("Deleted File");

        // Downloads the cache file
        InputStream inputStream = new URL(Configuration.CACHE_DOWNLOAD_URL).openStream();
        Files.copy(inputStream, Paths.get(Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME));
        System.out.println("Downloaded File");

        // Get this download's progress.
        // TODO JPROGRESSBAR

        // Unzips the Cache's Content
        unzipCache();
        // Sends a notification that the Cache has been updated (TEMPORARILY)

        if(cacheUpdated) {
            Alert downloadFinished = new Alert(Alert.AlertType.INFORMATION);
            downloadFinished.setTitle("Cache Update Completed");
            downloadFinished.setHeaderText("Your Cache has been updated completely.");
            System.out.println("Alert Shown");
            downloadFinished.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");

                }

            });
            //cacheUpdated(); // Use a boolean for this. Don't rely on your exceptions to be thrown
        }
    }

    private static String zipFilePath = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME;
    private static String destDir = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_FOLDER_NAME;

    private void unzipCache() throws IOException {
        System.out.println("Unzipping the Cache");
        ZipFile zipFile = new ZipFile(zipFilePath);
        try {
            zipFile.extractAll(destDir);
            if (zipFile.getProgressMonitor().getResult() == ProgressMonitor.Result.SUCCESS) {
                cacheUpdated = true;
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Removing the old zip file");
            Files.deleteIfExists(Paths.get(zipFilePath));
            cacheUpdated = true;
        }
    }
}
