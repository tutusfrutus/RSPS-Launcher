package com.tutus.versioncontrol.client;

import com.tutus.Configuration;
import com.tutus.download.client.ClientDownloader;
import sun.security.krb5.Config;

import java.io.*;
import java.util.Objects;

public class ClientVersion {

    ClientDownloader clientDownloader = new ClientDownloader();

    public void run() {
        try {
            String localCheck = Checksum.getLocalChecksum();
            System.out.println("LocalCheck == " + localCheck);
            String remoteCheck = Checksum.getRemoteChecksum();
            System.out.println("RemoteCheck == " + remoteCheck);
            if (!localCheck.equals(remoteCheck) || localCheck.equals("-1")) {
                try {
                    clientDownloader.downloadClient();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            } else {
                System.out.println("Checksums are identical");
            }
            FileWriter out = new FileWriter(Configuration.CLIENT_VERSION_FILE);
            out.write(remoteCheck);
        } catch (Exception e) {
            System.out.println("Something went wrong Running CacheVersion");
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
