package touchit.browser.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

public class ExitAction extends Action {

	@Override
	public String getText() {
		return "退出";
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String code = sdf.format(date);
		InputDialog d = new InputDialog(null, "请输入密码", "", code, null);
		d.setBlockOnOpen(true);
		d.open();

		if (d.getValue().equals(code)) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().close();
		} else {
			MessageDialog md = new MessageDialog(null, "错误", null, "密码不正确", 0,
					new String[] { "确认" }, 0);
			md.open();
		}

	}
}
