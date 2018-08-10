package com.campione_tech.ws.password_generator_ws;

import java.util.Properties;

/**
 *
 *
 * @author D. Campione
 *
 */
public class ConfigManager {

    private Properties properties;

    private static ConfigManager instance;

    private static ThreadLocal<Boolean> initHolder = new ThreadLocal<Boolean>();

    public void setProperties(Properties props) {
        properties = props;
    }

    public void addProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public Properties getProperties() {
        return properties;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public static ConfigManager getInstance() {
        if (initHolder.get() == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                    initHolder.set(Boolean.TRUE);
                }
            }
        }
        return instance;
    }
}