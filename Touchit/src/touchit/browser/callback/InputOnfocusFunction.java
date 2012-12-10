package touchit.browser.callback;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import touchit.browser.manager.RuntimeManager;
import touchit.browser.manager.AppManager;

public class InputOnfocusFunction extends BrowserFunction {
	private static final Logger logger = Logger
			.getLogger(InputOnfocusFunction.class);
	public static final String FUNCTION_NAME = "onFocusCallBack";

	public InputOnfocusFunction(Browser browser, String name) {
		super(browser, name);
	}

	private ProcessBuilder pb_keyBoard = new ProcessBuilder(
			AppManager.getInstallPath() + "SoftBoard.exe");

	public Object function(Object[] arguments) {
		logger.debug(FUNCTION_NAME + " is called.");
		if (RuntimeManager.findProcess("SoftBoard.exe")) {
			logger.debug("'SoftBoard.exe' is already started");
			return "RCP OK";
		}
		try {
			logger.debug("'SoftBoard.exe' has not started yet, ready to start it.");
			pb_keyBoard.start();
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}

		return "RCP OK";
	}
}
