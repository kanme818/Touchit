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

import touchit.browser.manager.SettingsManager;

public class Toolbar extends JFrame {
	private Keyboard keyboard = new Keyboard();

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

		final Display display = Display.getCurrent();

		final JButton btnKeyboard = new JButton("打开虚拟键盘");
		btnKeyboard.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnKeyboard.setBounds(new java.awt.Rectangle(0, 0, frameSize.width / 2, frameSize.height));
		btnKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						if (!keyboard.isVisible()) {
							keyboard.setVisible(true);
							btnKeyboard.setText("关闭虚拟键盘");
						} else {
							keyboard.setVisible(false);
							btnKeyboard.setText("打开虚拟键盘");
						}
					}
				});

			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnKeyboard);

		JButton btnTouchPad = new JButton("打开手写板");
		btnTouchPad.setFont(new Font("微软雅黑", Font.BOLD, 18));

		btnTouchPad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		btnTouchPad.setBounds(new java.awt.Rectangle(frameSize.width / 2, 0, frameSize.width / 2, frameSize.height));
		contentPane.add(btnTouchPad);
	}
}
