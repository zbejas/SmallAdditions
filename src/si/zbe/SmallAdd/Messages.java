package si.zbe.SmallAdd;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_EN = "si.zbe.Languages.english";
	private static final String BUNDLE_SLO = "si.zbe.Languages.slovene";
	private static final ResourceBundle RESOURCE_EN = ResourceBundle.getBundle(BUNDLE_EN);
	private static final ResourceBundle RESOURCE_SLO = ResourceBundle.getBundle(BUNDLE_SLO);

	public static String getString(String key) {
		if (Main.plugin.getConfig().getString("Language").toString().equalsIgnoreCase("english"))
			try {
				return RESOURCE_EN.getString(key);
			} catch (MissingResourceException e) {
				return String.valueOf('!') + key + '!';
			}
		if (Main.plugin.getConfig().getString("Language").toString().equalsIgnoreCase("slovene")) {
			try {
				return RESOURCE_SLO.getString(key);
			} catch (MissingResourceException e) {
				return String.valueOf('!') + key + '!';
			}
		}
		try {
			Main.plugin.getLogger().info(String.valueOf(RESOURCE_EN.getString("SA.WrongLanguage"))
					+ "Selected language: " + Main.plugin.getConfig().getString("Language").toString());

			return RESOURCE_EN.getString(key);
		} catch (MissingResourceException e) {
			return String.valueOf('!') + key + '!';
		}
	}
}
