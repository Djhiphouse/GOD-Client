package me.bratwurst.checkHost;

import me.bratwurst.utils.FTools;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class FTools_WebUtils {
    public static boolean openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
            return true;
        }
        catch (Exception e) {
            FTools.getInstance().getLogger().error("Failed to open link", (Throwable)e);
            return false;
        }
    }

    public static String getWebsiteData(String websiteUrl) {
        try {
            String line;
            StringBuilder stringBuilder = new StringBuilder("");
            URL url = new URL(websiteUrl);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
}


