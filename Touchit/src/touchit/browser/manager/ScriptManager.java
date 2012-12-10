package touchit.browser.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ScriptManager {
	private static Logger logger = Logger.getLogger(ScriptManager.class);
	public static final String FORBID_KEY = "forbid_key.script";
	public static final String CALLBACK_INPUT_ONFOCUS = "callback_input_onfocus.script";
	public static final String FORBID_IE_ERROR = "forbid_ie_error.script";
	public static final String force_open_link_in_self = "force_open_link_in_self.script";

	public static String loadScript(String fileName) {
		InputStream is = ScriptManager.class.getResourceAsStream("script/" + fileName);
		BufferedReader br = null;
		String temp;
		StringBuffer sb = new StringBuffer(";");
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (IOException e) {
			logger.debug(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.debug(e.getMessage());
				}
			}
		}
		logger.debug("Load one script - from: " + fileName + " script is: " + sb.toString());
		return sb.toString();
	}
}
