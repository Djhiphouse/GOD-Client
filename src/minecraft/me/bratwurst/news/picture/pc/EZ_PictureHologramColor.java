package me.bratwurst.news.picture.pc;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EZ_PictureHologramColor {
    BLACK('0', 0),
    DARK_BLUE('1', 1),
    DARK_GREEN('2', 2),
    DARK_AQUA('3', 3),
    DARK_RED('4', 4),
    DARK_PURPLE('5', 5),
    GOLD('6', 6),
    GRAY('7', 7),
    DARK_GRAY('8', 8),
    BLUE('9', 9),
    GREEN('a', 10),
    AQUA('b', 11),
    RED('c', 12),
    LIGHT_PURPLE('d', 13),
    YELLOW('e', 14),
    WHITE('f', 15),
    MAGIC('k', 16, true),
    BOLD('l', 17, true),
    STRIKETHROUGH('m', 18, true),
    UNDERLINE('n', 19, true),
    ITALIC('o', 20, true),
    RESET('r', 21);

    public static final char COLOR_CHAR = '\u00a7';
    private static final Pattern STRIP_COLOR_PATTERN;
    private final int intCode;
    private final char code;
    private final boolean isFormat;
    private final String toString;
    private static final Map<Integer, EZ_PictureHologramColor> BY_ID;
    private static final Map<Character, EZ_PictureHologramColor> BY_CHAR;

    static {
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
        BY_ID = Maps.newHashMap();
        BY_CHAR = Maps.newHashMap();
        for (EZ_PictureHologramColor color : EZ_PictureHologramColor.values()) {
            BY_ID.put(color.intCode, color);
            BY_CHAR.put(color.code, color);
        }
    }

    private EZ_PictureHologramColor(char code, int intCode) {
        this(code, intCode, false);
    }

    private EZ_PictureHologramColor(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{'\u00a7', code});
    }

    public char getChar() {
        return this.code;
    }

    public String toString() {
        return this.toString;
    }

    public boolean isFormat() {
        return this.isFormat;
    }

    public boolean isColor() {
        return !this.isFormat && this != RESET;
    }

    public static EZ_PictureHologramColor getByChar(char code) {
        return BY_CHAR.get(code);
    }

    public static EZ_PictureHologramColor getByChar(String code) {
        return BY_CHAR.get(code.charAt(0));
    }

    public static String stripColor(String input2) {
        if (input2 == null) {
            return null;
        }
        return STRIP_COLOR_PATTERN.matcher(input2).replaceAll("");
    }

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] != altColorChar || "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) <= -1) continue;
            b[i] = 167;
            b[i + 1] = Character.toLowerCase(b[i + 1]);
        }
        return new String(b);
    }

    public static String getLastColors(String input2) {
        String result2 = "";
        int length = input2.length();
        for (int index2 = length - 1; index2 > -1; --index2) {
            EZ_PictureHologramColor color;
            char c;
            char section = input2.charAt(index2);
            if (section != '\u00a7' || index2 >= length - 1 || (color = EZ_PictureHologramColor.getByChar(c = input2.charAt(index2 + 1))) == null) continue;
            result2 = color.toString() + result2;
            if (color.isColor() || color.equals((Object)RESET)) break;
        }
        return result2;
    }
}


