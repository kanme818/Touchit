package touchit.browser.action;

import org.eclipse.jface.action.Action;

import touchit.browser.dialog.SettingsDialog;

public class SettingsAction extends Action {

	@Override
	public String getText() {
		return "设置";
	}

	@Override
	public void run() {
		SettingsDialog settingDialog = new SettingsDialog(null);
		settingDialog.open();
	}

}
