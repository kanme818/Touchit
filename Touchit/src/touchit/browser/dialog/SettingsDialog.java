package touchit.browser.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import touchit.browser.bean.Settings;
import touchit.browser.manager.SettingsManager;

public class SettingsDialog extends Dialog {
	private Text txtAboutblank;
	private Combo combo;
	private Settings settings;

	public SettingsDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void okPressed() {
		String homePage = txtAboutblank.getText();
		if (homePage == null || homePage.trim().equals("")) {
			homePage = "about:blank";
		}

		char handPanelKey = combo.getText().charAt(0);
		settings.setHomePage(homePage);
		settings.setHandPanelKey(handPanelKey);
		SettingsManager.saveSettings(settings);
		super.okPressed();
	}

	@Override
	protected Point getInitialSize() {
		return new Point(600, 400);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(composite);
		composite.setLayout(new FormLayout());

		Label lblNewLabel = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 30);
		fd_lblNewLabel.left = new FormAttachment(0, 35);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("默认首页：");

		txtAboutblank = new Text(composite, SWT.BORDER);
		txtAboutblank.setText("about:blank");
		FormData fd_txtAboutblank = new FormData();
		fd_txtAboutblank.top = new FormAttachment(lblNewLabel, 6);
		fd_txtAboutblank.right = new FormAttachment(lblNewLabel, 390, SWT.RIGHT);
		fd_txtAboutblank.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		txtAboutblank.setLayoutData(fd_txtAboutblank);

		Button btnNewButton = new Button(composite, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(txtAboutblank, -2, SWT.TOP);
		fd_btnNewButton.left = new FormAttachment(txtAboutblank, 6);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("空白页");

		Label label = new Label(composite, SWT.NONE);
		label.setText("手写屏快捷键：");
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(txtAboutblank, 26);
		fd_label.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		label.setLayoutData(fd_label);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(label, 9);
		fd_lblNewLabel_1.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("CTRL + SHIFT + ");

		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(new String[] { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" });
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(lblNewLabel_1, -3, SWT.TOP);
		fd_combo.left = new FormAttachment(lblNewLabel_1, 6);
		fd_combo.right = new FormAttachment(100, -412);
		combo.setLayoutData(fd_combo);

		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(lblNewLabel_1, 0, SWT.TOP);
		fd_lblNewLabel_2.left = new FormAttachment(combo, 28);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);

		// load settings from file
		settings = SettingsManager.getSettings();
		String[] items = combo.getItems();
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(String.valueOf(settings.getHandPanelKey()))) {
				combo.select(i);
				break;
			}
		}

		txtAboutblank.setText(settings.getHomePage());

		return composite;
	}

}
