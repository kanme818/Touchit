package touchit.browser.dialog;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import touchit.browser.manager.RuntimeManager;
import touchit.browser.manager.SettingsManager;

public class Toolbar extends JFrame {
	private Keyboard keyboard = new Keyboard();

	private static final String TXT_OPEN_KEYBOARD = "使用虚拟键盘";
	private static final String TXT_CLOSE_KEYBOARD = "关闭虚拟键盘";
	private static final String TXT_OPEN_TOUCHPAD = "使用手写板";
	private static final String TXT_CLOSE_TOUCHPAD = "使用手写板";

	private JButton btnKeyboard;
	private JButton btnTouchPad;
	private Display display;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Toolbar frame = new Toolbar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Toolbar() {
		java.awt.Rectangle frameSize = new java.awt.Rectangle(0, 0, 300, 50);
		setBounds(frameSize);
		setUndecorated(true);
		setFocusableWindowState(false);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		org.eclipse.swt.graphics.Rectangle screenSize = Display.getDefault().getClientArea();

		setBounds((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height), frameSize.width, frameSize.height);

		contentPane = new JPanel();
		contentPane.setBounds(frameSize);

		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		display = Display.getCurrent();

		btnKeyboard = new JButton(TXT_OPEN_KEYBOARD);
		btnKeyboard.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnKeyboard.setBounds(new java.awt.Rectangle(0, 0, frameSize.width / 2, frameSize.height));

		btnTouchPad = new JButton(TXT_OPEN_TOUCHPAD);
		btnTouchPad.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnTouchPad.setBounds(new java.awt.Rectangle(frameSize.width / 2, 0, frameSize.width / 2, frameSize.height));

		contentPane.setLayout(null);
		contentPane.add(btnKeyboard);
		contentPane.add(btnTouchPad);

		btnKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						if (!keyboard.isVisible()) {
							turnKeyboardOn();
							turnTouchPadOff();
						} else {
							turnKeyboardOff();
						}
					}
				});

			}
		});

		btnTouchPad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						if (!RuntimeManager.findProcess(RuntimeManager.PROCESS_HANDINPUT)) {
							turnTouchPadOn();
							turnKeyboardOff();
						} else {
							turnTouchPadOff();
						}
					}
				});
			}
		});
	}

	private void turnKeyboardOn() {
		keyboard.setVisible(true);
		btnKeyboard.setText(TXT_CLOSE_KEYBOARD);
	}

	private void turnKeyboardOff() {
		keyboard.setVisible(false);
		btnKeyboard.setText(TXT_OPEN_KEYBOARD);
	}

	private void turnTouchPadOn() {
		Event keyEvent = new Event();
		keyEvent.type = SWT.KeyDown;
		keyEvent.keyCode = SWT.CTRL;
		display.post(keyEvent);

		keyEvent = new Event();
		keyEvent.type = SWT.KeyDown;
		keyEvent.keyCode = SWT.SHIFT;
		display.post(keyEvent);

		keyEvent = new Event();
		keyEvent.type = SWT.KeyDown;
		keyEvent.character = SettingsManager.getSettings().getHandPanelKey();
		display.post(keyEvent);

		keyEvent = new Event();
		keyEvent.type = SWT.KeyUp;
		keyEvent.character = SettingsManager.getSettings().getHandPanelKey();
		display.post(keyEvent);

		keyEvent = new Event();
		keyEvent.type = SWT.KeyUp;
		keyEvent.keyCode = SWT.SHIFT;
		display.post(keyEvent);

		keyEvent = new Event();
		keyEvent.type = SWT.KeyUp;
		keyEvent.keyCode = SWT.CTRL;
		display.post(keyEvent);

		btnTouchPad.setText(TXT_CLOSE_TOUCHPAD);

	}

	private void turnTouchPadOff() {
		RuntimeManager.killProcess(RuntimeManager.PROCESS_HANDINPUT);
		btnTouchPad.setText(TXT_OPEN_TOUCHPAD);
	}
}
