package com.tutus.versioncontrol.client;

import com.tutus.Configuration;
import com.tutus.download.client.ClientDownloader;
import javafx.concurrent.Task;
import sun.security.krb5.Config;

import java.io.*;
import java.util.Objects;

public class ClientVersionTask {

    ClientDownloader clientDownloader = new ClientDownloader();

    /**
     * Checks for the client's version
     */
    public void checkClientVersionTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    String localCheck = Checksum.getLocalChecksum();
                    System.out.println("LocalCheck == " + localCheck);
                    String remoteCheck = Checksum.getRemoteChecksum();
                    System.out.println("RemoteCheck == " + remoteCheck);
                    if (!localCheck.equals(remoteCheck) || localCheck.equals("-1") || Configuration.forceUpdate == true) {
                        try {
                            clientDownloader.downloadClient();
                            Configuration.forceUpdate = false;
                        } catch (IOException e) {
                            e.getStackTrace();
                        }
                    } else {
                        System.out.println("Checksums are identical");
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong Running CacheVersion");
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
                return null;
            }
        };

        new Thread(task).start();
    }
}