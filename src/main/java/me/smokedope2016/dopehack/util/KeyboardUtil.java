package me.smokedope2016.dopehack.util;

import me.smokedope2016.dopehack.features.settings.Bind;

public class KeyboardUtil {
    public static String getKeyName(int key) {
        String str = new Bind(key).toString().toUpperCase();
        str = str.replace("KEY.KEYBOARD", "").replace(".", " ");
        return str;
    }
}
