package touchit.browser.view;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import touchit.browser.action.ExitAction;
import touchit.browser.action.SettingsAction;
import touchit.browser.bean.Settings;
import touchit.browser.callback.InputLosefocusFunction;
import touchit.browser.callback.InputOnfocusFunction;
import touchit.browser.dialog.Toolbar;
import touchit.browser.manager.ScriptManager;
import touchit.browser.manager.SettingsManager;

public class BrowserView extends ViewPart {
	private static Logger logger = Logger.getLogger(BrowserView.class);

	public BrowserView() {
	}

	public static final String ID = "Touchit.BrowserView";

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent instanceof Object[]) {
				return (Object[]) parent;
			}
			return new Object[0];
		}
	}

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		final Browser browser = new Browser(parent, SWT.NONE);
		Settings settings = SettingsManager.getSettings();
		browser.setUrl(settings.getHomePage());

		browser.setMenu(new Menu(browser));

		new InputOnfocusFunction(browser, InputOnfocusFunction.FUNCTION_NAME);
		new InputLosefocusFunction(browser, InputLosefocusFunction.FUNCTION_NAME);

		final String script1 = ScriptManager.loadScript(ScriptManager.force_open_link_in_self);
		browser.addProgressListener(new ProgressListener() {

			@Override
			public void completed(ProgressEvent event) {
				browser.execute(script1);
				browser.execute(ScriptManager.loadScript(ScriptManager.FORBID_IE_ERROR));
				// browser.execute(ScriptManager.loadScript(ScriptManager.FORBID_KEY));
			}

			@Override
			public void changed(ProgressEvent event) {
				// 只在completed时替换target是不够的，用户可能在页面还未完全载入时就点击链接
				browser.execute(script1);
			}
		});

		browser.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

			}

			@Override
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					MenuManager mgr = new MenuManager();
					mgr.setRemoveAllWhenShown(true);
					mgr.addMenuListener(new IMenuListener() {
						public void menuAboutToShow(IMenuManager manager) {
							manager.add(new SettingsAction());
							manager.add(new ExitAction());
						}
					});
					Menu menu = mgr.createContextMenu(browser);
					browser.setMenu(menu);
				}

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {

			}
		});

		browser.addOpenWindowListener(new OpenWindowListener() {
			public void open(WindowEvent e) {
				logger.debug("弹出窗口被禁止。");
				final Shell shell2 = new Shell(Display.getCurrent());
				shell2.setLayout(new org.eclipse.swt.layout.FillLayout());
				Browser brs = new Browser(shell2, SWT.NONE);
				e.browser = brs;
				e.display.asyncExec(new Runnable() {
					public void run() {
						shell2.close();
					}
				});

			}
		});

		Toolbar toolbar = Toolbar.getToolbar();
		toolbar.setVisible(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}

}
