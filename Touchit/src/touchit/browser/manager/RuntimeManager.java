package touchit.browser.manager;

import java.awt.Window;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class RuntimeManager {
	private static Logger logger = Logger.getLogger(RuntimeManager.class);

	public static final String PROCESS_HANDINPUT = "handinput.exe";

	public static boolean findProcess(String processName) {
		BufferedReader br = null;
		try {
			String command = "tasklist /FI \"IMAGENAME eq " + processName + "\"";
			logger.debug("Call system command: " + command);
			Process proc = Runtime.getRuntime().exec(command);
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.contains(processName)) {
					return true;
				}
			}

			return false;
		} catch (Exception e) {
			logger.debug("exception: " + e.getMessage());
			return false;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception ex) {
					logger.debug("exception: " + ex.getMessage());
				}
			}

		}
	}

	public static void killProcess(String processName) {
		String command = "taskkill /F /IM " + processName;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setWindowOpacity(Window window, Float opacity) {
		try {
			Class<?> clazz = Class.forName("com.sun.awt.AWTUtilities");
			Method method = clazz.getMethod("setWindowOpacity", java.awt.Window.class, Float.TYPE);
			logger.debug("Opacity: " + opacity);
			method.invoke(clazz, window, opacity);
		} catch (Exception e) {
			logger.debug("exception: " + e.getMessage());
		}
	}

	public static void setWindowRoundCorner(Window window) {
		try {
			Class<?> clazz = Class.forName("com.sun.awt.AWTUtilities");
			Method method = clazz.getMethod("setWindowShape", java.awt.Window.class, java.awt.Shape.class);
			logger.debug(window.getWidth() + " " + window.getHeight());
			method.invoke(clazz, window, new RoundRectangle2D.Double(0.0D, 0.0D, window.getWidth(), window.getHeight(), 20.0D, 20.0D));
		} catch (Exception e) {
			logger.debug("exception: " + e.getMessage());
		}
	}

	public static void smoothWindowCorner(Window window) {
		try {
			Class<?> clazz = Class.forName("com.sun.awt.AWTUtilities");
			Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, boolean.class);
			method.invoke(clazz, window, true);
		} catch (Exception e) {
			logger.debug("exception: " + e.getMessage());
		}
	}
}
