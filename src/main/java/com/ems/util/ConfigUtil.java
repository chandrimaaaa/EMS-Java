package com.ems.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input =
                ConfigUtil.class.getClassLoader()
                                .getResourceAsStream("config.properties");

            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}