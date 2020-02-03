package si.zbe.smalladd;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_EN = "si.zbe.languages.english";
    private static final ResourceBundle RESOURCE_EN = ResourceBundle.getBundle(BUNDLE_EN);

    public static String getString(String key) {
        final String language = Main.plugin.getConfig().getString("Language");

        if (language.equalsIgnoreCase("english"))
            try {
                return RESOURCE_EN.getString(key);
            } catch (MissingResourceException e) {
                return "!" + key + "!";
            }
        else
            try {
                Main.plugin.getLogger().info(RESOURCE_EN.getString("SA.WrongLanguage") + "Selected language: " + language);
                return RESOURCE_EN.getString(key);
            } catch (MissingResourceException e) {
                return "!" + key + "!";
            }
    }
}
