package com.tutus;

import java.io.File;

public class Configuration {

    /*
    Configure your Launcher here
     */

    // Server Settings
    public static String SERVER_NAME = "";
    public static String SERVER_SLOGAN = "";

    // Cache Settings
    public static String CACHE_DOWNLOAD_URL = "http://downloads.arix-rsps.com/arixcache.zip";
    public static String CACHE_SAVE_NAME = "TestLauncherCache.zip";
    public static String CACHE_SAVE_DIR = System.getProperty("user.home") + File.separator;

    // Audio Settings
    public static boolean enableAudio = true;

    // Client Settings
    public static String CLIENT_DOWNLOAD_URL = "";
    public static String CLIENT_SAVE_NAME = "Arix.jar";
    public static String CLIENT_SAVE_DIR = System.getProperty("user.home") + File.separator;
}
