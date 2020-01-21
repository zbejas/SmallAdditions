package si.zbe.smalladd;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

    /*
     * TODO ResourceBundle implement ways to deal with languages. Properties could look like
     *  language.properties
     *  language_de.properties
     *  language_fr.properties
     */
    private static final String BUNDLE_EN = "si.zbe.languages.english";
    private static final String BUNDLE_SLO = "si.zbe.Languages.slovene";
    private static final ResourceBundle RESOURCE_EN = ResourceBundle.getBundle(BUNDLE_EN);
    private static final ResourceBundle RESOURCE_SLO = ResourceBundle.getBundle(BUNDLE_SLO);

    public static String getString(String key) {
        final String language = Main.plugin.getConfig().getString("Language");
        //TODO language may be null, to inconsistent config files
        if (language.equalsIgnoreCase("english"))
            try {
                return RESOURCE_EN.getString(key);
            } catch (MissingResourceException e) {
                return "!" + key + "!";
            }
        if (language.equalsIgnoreCase("slovene")) {
            try {
                return RESOURCE_SLO.getString(key);
            } catch (MissingResourceException e) {
                return "!" + key + "!";
            }
        }
        try {
            Main.plugin.getLogger().info(RESOURCE_EN.getString("SA.WrongLanguage") + "Selected language: " + language);
            return RESOURCE_EN.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!";
        }
    }
}
