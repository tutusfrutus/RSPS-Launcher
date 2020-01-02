package com.tutus.download.client;

import com.tutus.Configuration;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ClientDownloader {

        // downloads the Cache File
        public void downloadClient() throws IOException
        {
            //deletes jar before downloading new one (if it exists)
            File file = new File(Configuration.CLIENT_SAVE_DIR + Configuration.CLIENT_SAVE_NAME);
            if(file.exists()) {
                file.delete();
            }
            System.out.println("Deleted Client File");

            //downloads the cache file
            InputStream inputStream = new URL(Configuration.CLIENT_DOWNLOAD_URL).openStream();
            Files.copy(inputStream, Paths.get(Configuration.CLIENT_SAVE_DIR + Configuration.CLIENT_SAVE_NAME), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Downloaded Client File");

            // Get this download's progress.
            // TODO JPROGRESSBAR
        }
    }
