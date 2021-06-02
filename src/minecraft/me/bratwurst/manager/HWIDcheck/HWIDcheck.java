package me.bratwurst.manager.HWIDcheck;

import org.lwjgl.Sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;

public class HWIDcheck {
    public static final String command = "wmic baseboard get serialnumber";

    public static String getHwid() {
        System.out.println(System.getProperty("os.name"));
        if (!System.getProperty("os.name").toLowerCase().contains("windows"))
            System.exit(1);

        try {
            Process serialNumber = Runtime.getRuntime().exec(command);
            InputStreamReader isr = new InputStreamReader(serialNumber.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            bufferedReader.readLine();
            bufferedReader.readLine();
            String serial = bufferedReader.readLine().trim();
            System.out.println("Serial: " + serial);
            serialNumber.waitFor();
            bufferedReader.close();
            return SHA256(serial);
        } catch (IOException | InterruptedException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.exit(1);
        return "";
    }

    private static String SHA256(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes(StandardCharsets.ISO_8859_1));
        byte[] sha1hash = md.digest();
        return Base64.getEncoder().encodeToString(sha1hash);
    }
}
