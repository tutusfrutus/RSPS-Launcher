package com.tutus.download.cache;

import com.tutus.Configuration;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CacheDownloader {

// downloads the Cache File
    public static void downloadCache() throws IOException
    {
        //deletes jar before downloading new one (if it exists)
        File file = new File(Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME);
        file.delete();
        System.out.println("Deleted File");

        // Downloads the cache file
        InputStream inputStream = new URL(Configuration.CACHE_DOWNLOAD_URL).openStream();
        Files.copy(inputStream, Paths.get(Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Downloaded File");

        // Get this download's progress.
        // TODO JPROGRESSBAR

        // Unzips the Cache's Content
        unzipCache();
        // Sends a notification that the Cache has been updated (TEMPORARILY)
        cacheUpdated();

    }

    private static String zipFilePath = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_SAVE_NAME;
    private static String destDir = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_FOLDER_NAME;

    private static void unzipCache() {
        System.out.println("Unzipping the Cache");
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            zipFile.extractAll(destDir);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

// Sends an alert upon completion
    public static void cacheUpdated() throws ZipException {
        Alert downloadFinished = new Alert(Alert.AlertType.INFORMATION);
        downloadFinished.setTitle("Cache Update Completed");
        downloadFinished.setHeaderText("Your Cache has been updated completely.");
        System.out.println("Alert Shown");
        downloadFinished.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
