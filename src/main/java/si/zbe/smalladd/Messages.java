package si.zbe.smalladd;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class Messages {
    private static File langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");
    private static FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langYml);

    private static final String BUNDLE_EN = "si.zbe.languages.english";
    private static final ResourceBundle RESOURCE_EN = ResourceBundle.getBundle(BUNDLE_EN);

    public static String getString(String key) {
        return langConfig.getString(key);
    }

    public static void setupMessages() {
        for (String key : RESOURCE_EN.keySet()) {
            if (langConfig.getString(key) == null)
                langConfig.set(key, RESOURCE_EN.getString(key));
        }
        try {
            langConfig.save(langYml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
