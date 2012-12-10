package touchit.browser.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import touchit.browser.bean.Settings;

public class SettingsManager {
	private static Logger logger = Logger.getLogger(SettingsManager.class);
	private static final String SETTINGS_FILE = "settings.properties";
	private static Settings settings;

	private SettingsManager() {
		super();
	}

	public static void loadSettings() {
		File settingsFile = new File(AppManager.getInstallPath() + SETTINGS_FILE);
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			if (!settingsFile.exists()) {
				inputStream = SettingsManager.class.getClassLoader()
						.getResourceAsStream(SETTINGS_FILE);
			} else {
				inputStream = new FileInputStream(settingsFile);
			}
			properties.load(inputStream);
		} catch (IOException ex) {
		}
		logger.debug("Loaded properites: " + properties.size());
		settings = new Settings();
		settings.setHomePage(properties.getProperty("HOME_PAGE", "about:blank"));
		settings.setHandPanelKey(properties.getProperty("HAND_PANEL_KEY", "A")
				.charAt(0));
		if (!settingsFile.exists()) {
			saveSettings(settings);
		}
	}

	public static Settings getSettings() {
		if (settings == null) {
			loadSettings();
		}
		return settings;
	}

	public static void saveSettings(Settings settings) {
		URL url = SettingsManager.class.getClassLoader().getResource(
				SETTINGS_FILE);
		logger.debug(url);
		Properties properties = new Properties();
		properties.setProperty("HOME_PAGE", settings.getHomePage());
		properties.setProperty("HAND_PANEL_KEY",
				String.valueOf(settings.getHandPanelKey()));
		try {
			FileOutputStream fos = new FileOutputStream(new File(
					AppManager.getInstallPath() + SETTINGS_FILE));
			properties.store(fos, null);
		} catch (FileNotFoundException e) {
			logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
		SettingsManager.settings = settings;
	}

}
