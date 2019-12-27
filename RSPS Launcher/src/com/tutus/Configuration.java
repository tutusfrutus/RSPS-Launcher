package com.tutus;

import java.awt.*;
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
    public static String CLIENT_DOWNLOAD_URL = "http://downloads.arix-rsps.com/arix-client.jar";
    public static String CLIENT_SAVE_NAME = "Arix.jar";
    public static String CLIENT_SAVE_DIR = System.getProperty("user.home") + File.separator;

    // Launcher Settings
    public static int appWidth = 500;
    public static int appHeight = 300;
}
