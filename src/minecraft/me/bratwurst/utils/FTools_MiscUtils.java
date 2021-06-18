package me.bratwurst.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.Session;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class FTools_MiscUtils {
    public static String lastServer;
    private static JFrame frame;


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String getCurrentTime() {
        SimpleDateFormat dff = new SimpleDateFormat("HH:mm");
        Date todayy = Calendar.getInstance().getTime();
        String renderTime = dff.format(todayy);
        return renderTime;
    }

    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date today = Calendar.getInstance().getTime();
        String renderDate = df.format(today);
        return renderDate;
    }

    public static String getLagColor(long lag) {
        if (lag > 99L && lag < 500L) {
            return "\u00a7a";
        }
        if (lag > 499L && lag < 1000L) {
            return "\u00a7e";
        }
        if (lag > 999L && lag < 1500L) {
            return "\u00a76";
        }
        if (lag > 1499L && lag < 2000L) {
            return "\u00a7c";
        }
        if (lag >= 1999L) {
            return "\u00a74";
        }
        System.out.println(lag + "a");
        return "\u00a7f";
    }

    public static String getPingColor(int ping) {
        if (ping <= 10) {
            return "\u00a7a";
        }
        if (ping <= 50) {
            return "\u00a7e";
        }
        if (ping <= 100) {
            return "\u00a76";
        }
        if (ping <= 250) {
            return "\u00a7c";
        }
        return "\u00a74";
    }

    public static String getTPSColor(double tps) {
        if (tps >= 18.0) {
            return "\u00a7a";
        }
        if (tps >= 15.0) {
            return "\u00a72";
        }
        if (tps >= 13.0) {
            return "\u00a7e";
        }
        if (tps >= 10.0) {
            return "\u00a7c";
        }
        if (tps >= 8.0) {
            return "\u00a74";
        }
        if (tps >= 4.0) {
            return "\u00a74";
        }
        if (tps >= 0.0) {
            return "\u00a74";
        }
        return "\u00a7f";
    }

    public static String getTPSColorByProzent(double p) {
        if (p > 74.0) {
            return "\u00a7a";
        }
        if (p < 74.0) {
            return "\u00a72";
        }
        if (p < 51.0) {
            return "\u00a7e";
        }
        if (p < 26.0) {
            return "\u00a7c";
        }
        return "\u00a74";
    }

    public static int getLongestString(ArrayList<String> list) {
        int len = -1;
        for (String str : list) {
            if (str == null) continue;
            len = Math.max(len, str.length());
        }
        return len;
    }

    public static Session createSession(String name, String password) throws AuthenticationException {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)service.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(name);
        auth.setPassword(password);
        auth.logIn();
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
    }

    public static String getRandomName() {
        int special;
        boolean leet;
        String name = "";
        int nameLength = (int)Math.round(Math.random() * 4.0) + 5;
        String vowels = "aeiouy";
        String consonants = "bcdfghklmnprstvwz";
        int usedConsonants = 0;
        int usedVowels = 0;
        String lastLetter = "blah";
        for (int i = 0; i < nameLength; ++i) {
            int letterIndex;
            String nextLetter = lastLetter;
            if ((new Random().nextBoolean() || usedConsonants == 1) && usedVowels < 2) {
                while (nextLetter.equals(lastLetter)) {
                    letterIndex = (int)(Math.random() * (double)vowels.length() - 1.0);
                    nextLetter = vowels.substring(letterIndex, letterIndex + 1);
                }
                usedConsonants = 0;
                ++usedVowels;
            } else {
                while (nextLetter.equals(lastLetter)) {
                    letterIndex = (int)(Math.random() * (double)consonants.length() - 1.0);
                    nextLetter = consonants.substring(letterIndex, letterIndex + 1);
                }
                ++usedConsonants;
                usedVowels = 0;
            }
            lastLetter = nextLetter;
            name = name.concat(nextLetter);
        }
        int capitalMode = (int)Math.round(Math.random() * 2.0);
        if (capitalMode == 1) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
        } else if (capitalMode == 2) {
            for (int i = 0; i < nameLength; ++i) {
                if ((int)Math.round(Math.random() * 3.0) != 1) continue;
                name = name.substring(0, i) + name.substring(i, i + 1).toUpperCase() + (i == nameLength ? "" : name.substring(i + 1));
            }
        }
        int numberLength = (int)Math.round(Math.random() * 3.0) + 1;
        int numberMode = (int)Math.round(Math.random() * 3.0);
        boolean number = new Random().nextBoolean();
        if (number) {
            int i;
            int nextNumber;
            if (numberLength == 1) {
                nextNumber = (int)Math.round(Math.random() * 9.0);
                name = name.concat(Integer.toString(nextNumber));
            } else if (numberMode == 0) {
                nextNumber = (int)(Math.round(Math.random() * 8.0) + 1L);
                for (i = 0; i < numberLength; ++i) {
                    name = name.concat(Integer.toString(nextNumber));
                }
            } else if (numberMode == 1) {
                nextNumber = (int)(Math.round(Math.random() * 8.0) + 1L);
                name = name.concat(Integer.toString(nextNumber));
                for (i = 1; i < numberLength; ++i) {
                    name = name.concat("0");
                }
            } else if (numberMode == 2) {
                nextNumber = (int)(Math.round(Math.random() * 8.0) + 1L);
                name = name.concat(Integer.toString(nextNumber));
                for (i = 0; i < numberLength; ++i) {
                    nextNumber = (int)Math.round(Math.random() * 9.0);
                    name = name.concat(Integer.toString(nextNumber));
                }
            } else if (numberMode == 3) {
                nextNumber = 99999;
                while (Integer.toString(nextNumber).length() != numberLength) {
                    nextNumber = (int)(Math.round(Math.random() * 12.0) + 1L);
                    nextNumber = (int)Math.pow(2.0, nextNumber);
                }
                name = name.concat(Integer.toString(nextNumber));
            }
        }
        boolean bl = leet = !number && new Random().nextBoolean();
        if (leet) {
            String oldName = name;
            while (name.equals(oldName)) {
                int leetMode = (int)Math.round(Math.random() * 7.0);
                if (leetMode == 0) {
                    name = name.replace("a", "4");
                    name = name.replace("A", "4");
                }
                if (leetMode == 1) {
                    name = name.replace("e", "3");
                    name = name.replace("E", "3");
                }
                if (leetMode == 2) {
                    name = name.replace("g", "6");
                    name = name.replace("G", "6");
                }
                if (leetMode == 3) {
                    name = name.replace("h", "4");
                    name = name.replace("H", "4");
                }
                if (leetMode == 4) {
                    name = name.replace("i", "1");
                    name = name.replace("I", "1");
                }
                if (leetMode == 5) {
                    name = name.replace("o", "0");
                    name = name.replace("O", "0");
                }
                if (leetMode == 6) {
                    name = name.replace("s", "5");
                    name = name.replace("S", "5");
                }
                if (leetMode != 7) continue;
                name = name.replace("l", "7");
                name = name.replace("L", "7");
            }
        }
        if ((special = (int)Math.round(Math.random() * 8.0)) == 3) {
            name = "xX".concat(name).concat("Xx");
        } else if (special == 4) {
            name = name.concat("LP");
        } else if (special == 5) {
            name = name.concat("HD");
        }
        return name;
    }

    public static BufferedImage skinBuffer(String website) {
        try {
            return ImageIO.read(new URL(website).openStream());
        }
        catch (Exception e) {
            return null;
        }
    }

    private static JFrame getFrame() {
        return frame;
    }




}

