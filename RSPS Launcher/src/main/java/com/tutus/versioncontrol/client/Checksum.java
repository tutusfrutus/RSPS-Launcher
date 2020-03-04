package com.tutus.versioncontrol.client;

import com.tutus.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

    /**
     * Get's the checksum/version from the local computer
     * @return - Returns -1 if it couldn't find anything (making it update either way)
     */
        public static String getLocalChecksum() {
            File local = new File(Configuration.CLIENT_SAVE_DIR + Configuration.CLIENT_SAVE_NAME);
            if(local.exists()) {
                try (FileInputStream fis = new FileInputStream(local)) {
                    return Checksum.calculateMd5(fis);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "-1";
        }

    /**
     * Get's the checksum/version from the host/download URL
     * @return - Returns -2 if it couldn't find anything (making it update either way)
     */
    public static String getRemoteChecksum() {
            try (InputStream stream = new URL(Configuration.CLIENT_DOWNLOAD_URL).openStream()) {
                return  Checksum.calculateMd5(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "-2";
        }

        public static String calculateMd5(final InputStream instream) {
            return calculateDigest(instream, "MD5");
        }

        private static String calculateDigest(final InputStream instream, final String algorithm) {
            final byte[] buffer = new byte[4096];
            final MessageDigest messageDigest = getMessageDigest(algorithm);
            messageDigest.reset();
            int bytesRead;
            try {
                while ((bytesRead = instream.read(buffer)) != -1) {
                    messageDigest.update(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.err.println("Error making a '" + algorithm + "' digest on the inputstream");
            }
            return toHex(messageDigest.digest());
        }

        public static String toHex(final byte[] ba) {
            int baLen = ba.length;
            char[] hexchars = new char[baLen * 2];
            int cIdx = 0;
            for (int i = 0; i < baLen; ++i) {
                hexchars[cIdx++] = hexdigit[(ba[i] >> 4) & 0x0F];
                hexchars[cIdx++] = hexdigit[ba[i] & 0x0F];
            }
            return new String(hexchars);
        }

        public static MessageDigest getMessageDigest(final String algorithm) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("The '" + algorithm + "' algorithm is not available");
            }
            return messageDigest;
        }

        private static final char[] hexdigit = {
                '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f'
        };
    }
