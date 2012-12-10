package touchit.browser.manager;

import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.adaptor.LocationManager;
import org.eclipse.osgi.service.datalocation.Location;

@SuppressWarnings("restriction")
public class AppManager {
	private static Logger logger = Logger.getLogger(AppManager.class);

	public static String getInstallPath() {
		Location installLoc = LocationManager.getInstallLocation();
		String path = null;
		String installPath = null;
		if (installLoc != null) {
			URL installURL = installLoc.getURL();

			path = installURL.getPath();

		}
		installPath = path.substring(1, path.length());
		logger.debug("Install path: " + installPath);
		return installPath;
	}
}
