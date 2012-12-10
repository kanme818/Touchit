package touchit;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		Rectangle screenSize = Display.getDefault().getClientArea();
		configurer.setInitialSize(new Point(screenSize.width, screenSize.height));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(false);
		configurer.setShowMenuBar(false);
		configurer.setShowPerspectiveBar(false);
		configurer.setShowFastViewBars(false);
		configurer.setShowProgressIndicator(false);
	}

	@Override
	public void postWindowOpen() {
		super.postWindowOpen();
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		// full screen
		configurer.getWindow().getShell().setMaximized(true);
		configurer.getWindow().getShell().setFullScreen(true);

		loadStaticResources();
	}

	private void loadStaticResources() {
		try {
			Class.forName("touchit.browser.manager.ResourcesManager");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
