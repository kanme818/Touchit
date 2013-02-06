package touchit.browser.callback;

import org.apache.log4j.Logger;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import touchit.browser.dialog.Keyboard;
import touchit.browser.dialog.Toolbar;
import touchit.browser.manager.RuntimeManager;

public class InputOnfocusFunction extends BrowserFunction {
	private static final Logger logger = Logger.getLogger(InputOnfocusFunction.class);
	public static final String FUNCTION_NAME = "onFocusCallBack";

	public InputOnfocusFunction(Browser browser, String name) {
		super(browser, name);
	}

	public Object function(Object[] arguments) {
		logger.debug(FUNCTION_NAME + " is called.");
		if (RuntimeManager.findProcess(RuntimeManager.PROCESS_HANDINPUT)) {
			logger.debug("'handinput.exe' is already started");
			return "RCP OK";
		}
		logger.debug("'Keyboard' has not started yet, ready to start it.");
		Keyboard keyboard = Keyboard.getKeyboard();
		if (!keyboard.isVisible()) {
			keyboard.setVisible(true);
			Toolbar.getToolbar().getBtnKeyboard().setText(Toolbar.TXT_CLOSE_KEYBOARD);
		}

		return "RCP OK";
	}
}
