package de.Hero.clickgui.util;

import me.bratwurst.Client;

import java.awt.Color;


/**
 * Made by HeroCode
 * it's free to use
 * but you have to credit me
 *
 * @author HeroCode
 */
public class ColorUtil {

    public static Color getClickGUIColor() {
        return new Color((int) Client.setmgr.getSettingByName("GuiRed").getValDouble(), (int) Client.setmgr.getSettingByName("GuiGreen").getValDouble(), (int) Client.setmgr.getSettingByName("GuiBlue").getValDouble());
    }
}
