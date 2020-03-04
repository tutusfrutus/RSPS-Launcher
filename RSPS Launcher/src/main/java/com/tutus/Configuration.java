package com.tutus;

import java.awt.*;
import java.io.File;
import java.net.URL;

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
    public static String CACHE_FOLDER_NAME = "PlutusTest";
    // Cache Version Control
    public static final String CACHE_VERSION_URL = "http://downloads.arix-rsps.com/version.txt";
    public static final String CACHE_VERSION_FILE = Configuration.CACHE_SAVE_DIR + Configuration.CACHE_FOLDER_NAME +  File.separator + "cacheVersion.dat";

    // Audio Settings
    public static boolean enableAudio = false;
    public static double audioVolume = 0.20;
    public static URL launcherAudio = Configuration.class.getResource("/Audio/launcherAudio.mp3");

    // Client Settings
    public static String CLIENT_DOWNLOAD_URL = "http://downloads.arix-rsps.com/arix-client.jar";
    public static String CLIENT_SAVE_NAME = "Arix.jar";
    public static String CLIENT_SAVE_DIR = System.getProperty("user.home") + File.separator;

    // Launcher Settings
    public static String LAUNCHER_TITLE = "RuneLife Launcher by Tutus Frutus";
    public static URL iconImage = Configuration.class.getResource("/Images/icon.png");
    public static int appWidth = 800;
    public static int appHeight = 600;

    //Force Updates
    public static boolean forceUpdate = false;

}
