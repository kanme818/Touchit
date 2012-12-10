package touchit.browser.dialog;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import touchit.browser.manager.ResourcesManager;
import touchit.browser.manager.RuntimeManager;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Keyboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Keyboard frame = new Keyboard();
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
	public Keyboard() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		// setDefaultLookAndFeelDecorated(false);

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setForeground(Color.BLACK);
		setForeground(Color.BLACK);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		java.awt.Rectangle frameSize = new java.awt.Rectangle(0, 0, 916, 316);
		setBounds(frameSize);
		setFocusableWindowState(false);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		org.eclipse.swt.graphics.Rectangle screenSize = Display.getDefault().getClientArea();

		setBounds((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height - 50), frameSize.width, frameSize.height);

		final Display display = Display.getCurrent();

		final ActionListener functionKeyPressListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if ("FUNC1".equals(button.getActionCommand())) {
					Event keyEvent = new Event();
					keyEvent.type = SWT.KeyDown;
					keyEvent.keyCode = SWT.SHIFT;
					display.post(keyEvent);

					keyEvent = new Event();
					keyEvent.type = SWT.KeyUp;
					keyEvent.keyCode = SWT.SHIFT;
					display.post(keyEvent);
				}

				if ("FUNC2".equals(button.getActionCommand())) {
					Event keyEvent = new Event();
					keyEvent.type = SWT.KeyDown;
					keyEvent.character = SWT.BS;
					display.post(keyEvent);
				}

				if ("FUNC3".equals(button.getActionCommand())) {
					Event keyEvent = new Event();
					keyEvent.type = SWT.KeyDown;
					keyEvent.keyCode = SWT.CTRL;
					display.post(keyEvent);

					keyEvent = new Event();
					keyEvent.type = SWT.KeyDown;
					keyEvent.character = SWT.SPACE;
					display.post(keyEvent);

					keyEvent = new Event();
					keyEvent.type = SWT.KeyUp;
					keyEvent.character = SWT.SPACE;
					display.post(keyEvent);

					keyEvent = new Event();
					keyEvent.type = SWT.KeyUp;
					keyEvent.keyCode = SWT.CTRL;
					display.post(keyEvent);

				}

				if ("FUNC4".equals(button.getActionCommand())) {
					Event keyEvent = new Event();
					keyEvent.type = SWT.KeyDown;
					keyEvent.character = SWT.SPACE;
					display.post(keyEvent);
				}

				if ("FUNC5".equals(button.getActionCommand())) {
					Event keyEvent = new Event();
					keyEvent.type = SWT.KeyDown;
					keyEvent.keyCode = SWT.CR;
					display.post(keyEvent);
				}

				if ("FUNC6".equals(button.getActionCommand())) {
					// Event keyEvent = new Event();
					// keyEvent.type = SWT.KeyDown;
					// // keyEvent.character = 45;
					// keyEvent.keyCode = SWT.PAGE_UP;
					// display.post(keyEvent);

					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_PAGE_UP);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}

				}

				if ("FUNC7".equals(button.getActionCommand())) {
					// Event keyEvent = new Event();
					// keyEvent.type = SWT.KeyDown;
					// // keyEvent.character = 61;
					// keyEvent.keyCode = SWT.PAGE_DOWN;
					// display.post(keyEvent);

					try {
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_PAGE_DOWN);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}

				}
			}
		};
		final ActionListener textKeyPressListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event keyEvent = new Event();
				keyEvent.type = SWT.KeyDown;
				JButton button = (JButton) e.getSource();
				keyEvent.character = button.getActionCommand().charAt(0);
				display.post(keyEvent);
			}
		};

		final MouseAdapter smallSizeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconSmallSelected());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconSmall());
				super.mouseReleased(e);
			}

		};

		final MouseAdapter semiMiddleSizeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconSemiMiddleSelected());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconSemiMiddle());
				super.mouseReleased(e);
			}

		};

		final MouseAdapter middleSizeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconMiddleSelected());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconMiddle());
				super.mouseReleased(e);
			}

		};

		final MouseAdapter longSizeAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconLongSelected());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JButton button = (JButton) e.getComponent();
				button.setIcon(ResourcesManager.getKeyIconLong());
				super.mouseReleased(e);
			}

		};

		// resources
		String _s_gap = "11px";
		String _r_h = "fill:50px";
		String _btn_h = "50px";
		String _btn_w = "70px";

		Font fontEN = ResourcesManager.getFontKeyboardEN();
		Font fontCN = ResourcesManager.getFontKeyboardCN();

		ImageIcon iconSmall = ResourcesManager.getKeyIconSmall();
		ImageIcon iconSemiMiddle = ResourcesManager.getKeyIconSemiMiddle();
		ImageIcon iconMiddle = ResourcesManager.getKeyIconMiddle();
		ImageIcon iconLong = ResourcesManager.getKeyIconLong();

		// // test
		// ImageIcon keyboardBg = ResourcesManager.getKeyboardBg();
		// JLabel label = new JLabel(keyboardBg);
		// label.setBounds(0, 0, getWidth(), getHeight());
		//
		// JPanel imagePanel = (JPanel) getContentPane();
		// imagePanel.setOpaque(false);
		// getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		// //

		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						RowSpec.decode(_s_gap), RowSpec.decode(_r_h), RowSpec.decode(_s_gap), RowSpec.decode(_r_h), RowSpec.decode(_s_gap),
						RowSpec.decode(_r_h), RowSpec.decode(_s_gap), RowSpec.decode(_r_h), RowSpec.decode(_s_gap), RowSpec.decode(_r_h),
						RowSpec.decode(_s_gap), }));

		// 1-0
		JPanel panel_0 = new JPanel();
		panel_0.setOpaque(false);
		panel_0.setForeground(Color.BLACK);
		getContentPane().add(panel_0, "2, 2, fill, fill");
		panel_0.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), },
				new RowSpec[] { RowSpec.decode(_btn_h), }));

		JButton btn1 = new JButton();
		btn1.addMouseListener(smallSizeAdapter);
		btn1.setForeground(Color.BLACK);
		btn1.setHorizontalTextPosition(SwingConstants.CENTER);
		btn1.setFont(fontEN);
		btn1.setText("1");
		btn1.setIconTextGap(0);
		btn1.setIcon(iconSmall);
		btn1.setBorder(null);
		btn1.setFocusPainted(false);
		btn1.setActionCommand("1");
		btn1.addActionListener(textKeyPressListener);
		panel_0.add(btn1, "2, 1, fill, fill");

		JButton btn2 = new JButton();
		btn2.addMouseListener(smallSizeAdapter);
		btn2.setForeground(Color.BLACK);
		btn2.setHorizontalTextPosition(SwingConstants.CENTER);
		btn2.setFont(fontEN);
		btn2.setText("2");
		btn2.setIcon(iconSmall);
		btn2.setIconTextGap(0);
		btn2.setBorder(null);
		btn2.setFocusPainted(false);
		btn2.setActionCommand("2");
		btn2.addActionListener(textKeyPressListener);
		panel_0.add(btn2, "4, 1, fill, fill");

		JButton btn3 = new JButton();
		btn3.addMouseListener(smallSizeAdapter);
		btn3.setForeground(Color.BLACK);
		btn3.setHorizontalTextPosition(SwingConstants.CENTER);
		btn3.setText("3");
		btn3.setFont(fontEN);
		btn3.setIcon(iconSmall);
		btn3.setIconTextGap(0);
		btn3.setBorder(null);
		btn3.setFocusPainted(false);
		btn3.setActionCommand("3");
		btn3.addActionListener(textKeyPressListener);
		panel_0.add(btn3, "6, 1, fill, fill");

		JButton btn4 = new JButton();
		btn4.addMouseListener(smallSizeAdapter);
		btn4.setForeground(Color.BLACK);
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		btn4.setText("4");
		btn4.setFont(fontEN);
		btn4.setIcon(iconSmall);
		btn4.setIconTextGap(0);
		btn4.setBorder(null);
		btn4.setFocusPainted(false);
		btn4.setActionCommand("4");
		btn4.addActionListener(textKeyPressListener);
		panel_0.add(btn4, "8, 1, fill, fill");

		JButton btn5 = new JButton();
		btn5.addMouseListener(smallSizeAdapter);
		btn5.setForeground(Color.BLACK);
		btn5.setHorizontalTextPosition(SwingConstants.CENTER);
		btn5.setText("5");
		btn5.setFont(fontEN);
		btn5.setIcon(iconSmall);
		btn5.setIconTextGap(0);
		btn5.setBorder(null);
		btn5.setFocusPainted(false);
		btn5.setActionCommand("5");
		btn5.addActionListener(textKeyPressListener);
		panel_0.add(btn5, "10, 1, fill, fill");

		JButton btn6 = new JButton();
		btn6.addMouseListener(smallSizeAdapter);
		btn6.setForeground(Color.BLACK);
		btn6.setHorizontalTextPosition(SwingConstants.CENTER);
		btn6.setText("6");
		btn6.setFont(fontEN);
		btn6.setIcon(iconSmall);
		btn6.setIconTextGap(0);
		btn6.setBorder(null);
		btn6.setFocusPainted(false);
		btn6.setActionCommand("6");
		btn6.addActionListener(textKeyPressListener);
		panel_0.add(btn6, "12, 1, fill, fill");

		JButton btn7 = new JButton();
		btn7.addMouseListener(smallSizeAdapter);
		btn7.setForeground(Color.BLACK);
		btn7.setHorizontalTextPosition(SwingConstants.CENTER);
		btn7.setText("7");
		btn7.setFont(fontEN);
		btn7.setIcon(iconSmall);
		btn7.setIconTextGap(0);
		btn7.setBorder(null);
		btn7.setFocusPainted(false);
		btn7.setActionCommand("7");
		btn7.addActionListener(textKeyPressListener);
		panel_0.add(btn7, "14, 1, fill, fill");

		JButton btn8 = new JButton();
		btn8.addMouseListener(smallSizeAdapter);
		btn8.setForeground(Color.BLACK);
		btn8.setHorizontalTextPosition(SwingConstants.CENTER);
		btn8.setText("8");
		btn8.setFont(fontEN);
		btn8.setIcon(iconSmall);
		btn8.setIconTextGap(0);
		btn8.setBorder(null);
		btn8.setFocusPainted(false);
		btn8.setActionCommand("8");
		btn8.addActionListener(textKeyPressListener);
		panel_0.add(btn8, "16, 1, fill, fill");

		JButton btn9 = new JButton();
		btn9.addMouseListener(smallSizeAdapter);
		btn9.setForeground(Color.BLACK);
		btn9.setHorizontalTextPosition(SwingConstants.CENTER);
		btn9.setText("9");
		btn9.setFont(fontEN);
		btn9.setIcon(iconSmall);
		btn9.setIconTextGap(0);
		btn9.setBorder(null);
		btn9.setFocusPainted(false);
		btn9.setActionCommand("9");
		btn9.addActionListener(textKeyPressListener);
		panel_0.add(btn9, "18, 1, fill, fill");

		JButton btn0 = new JButton();
		btn0.addMouseListener(smallSizeAdapter);
		btn0.setForeground(Color.BLACK);
		btn0.setHorizontalTextPosition(SwingConstants.CENTER);
		btn0.setText("0");
		btn0.setFont(fontEN);
		btn0.setIcon(iconSmall);
		btn0.setIconTextGap(0);
		btn0.setBorder(null);
		btn0.setFocusPainted(false);
		btn0.setActionCommand("0");
		btn0.addActionListener(textKeyPressListener);
		panel_0.add(btn0, "20, 1, fill, fill");

		JButton button = new JButton();
		button.addMouseListener(smallSizeAdapter);
		button.addActionListener(functionKeyPressListener);
		button.setIcon(iconSmall);
		button.setText("前页");
		button.setIconTextGap(0);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setForeground(Color.BLACK);
		button.setFont(fontCN);
		button.setFocusPainted(false);
		button.setBorder(null);
		button.setActionCommand("FUNC6");
		panel_0.add(button, "22, 1, fill, fill");

		// Q-P
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setForeground(Color.BLACK);
		getContentPane().add(panel_1, "2, 4, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), },
				new RowSpec[] { RowSpec.decode(_btn_h), }));

		JButton btnQ = new JButton();
		btnQ.addMouseListener(smallSizeAdapter);
		btnQ.setForeground(Color.BLACK);
		btnQ.setHorizontalTextPosition(SwingConstants.CENTER);
		btnQ.setText("Q");
		btnQ.setFont(fontEN);
		btnQ.setIcon(iconSmall);
		btnQ.setIconTextGap(0);
		btnQ.setBorder(null);
		btnQ.setFocusPainted(false);
		btnQ.setActionCommand("Q");
		btnQ.addActionListener(textKeyPressListener);
		panel_1.add(btnQ, "2, 1, fill, fill");

		JButton btnW = new JButton();
		btnW.addMouseListener(smallSizeAdapter);
		btnW.setForeground(Color.BLACK);
		btnW.setHorizontalTextPosition(SwingConstants.CENTER);
		btnW.setText("W");
		btnW.setFont(fontEN);
		btnW.setIcon(iconSmall);
		btnW.setIconTextGap(0);
		btnW.setBorder(null);
		btnW.setFocusPainted(false);
		btnW.setActionCommand("W");
		btnW.addActionListener(textKeyPressListener);
		panel_1.add(btnW, "4, 1, fill, fill");

		JButton btnE = new JButton();
		btnE.addMouseListener(smallSizeAdapter);
		btnE.setForeground(Color.BLACK);
		btnE.setHorizontalTextPosition(SwingConstants.CENTER);
		btnE.setText("E");
		btnE.setFont(fontEN);
		btnE.setIcon(iconSmall);
		btnE.setIconTextGap(0);
		btnE.setBorder(null);
		btnE.setFocusPainted(false);
		btnE.setActionCommand("E");
		btnE.addActionListener(textKeyPressListener);
		panel_1.add(btnE, "6, 1, fill, fill");

		JButton btnR = new JButton();
		btnR.addMouseListener(smallSizeAdapter);
		btnR.setForeground(Color.BLACK);
		btnR.setHorizontalTextPosition(SwingConstants.CENTER);
		btnR.setText("R");
		btnR.setFont(fontEN);
		btnR.setIcon(iconSmall);
		btnR.setIconTextGap(0);
		btnR.setBorder(null);
		btnR.setFocusPainted(false);
		btnR.setActionCommand("R");
		btnR.addActionListener(textKeyPressListener);
		panel_1.add(btnR, "8, 1, fill, fill");

		JButton btnT = new JButton();
		btnT.addMouseListener(smallSizeAdapter);
		btnT.setForeground(Color.BLACK);
		btnT.setHorizontalTextPosition(SwingConstants.CENTER);
		btnT.setText("T");
		btnT.setFont(fontEN);
		btnT.setIcon(iconSmall);
		btnT.setIconTextGap(0);
		btnT.setBorder(null);
		btnT.setFocusPainted(false);
		btnT.setActionCommand("T");
		btnT.addActionListener(textKeyPressListener);
		panel_1.add(btnT, "10, 1, fill, fill");

		JButton btnY = new JButton();
		btnY.addMouseListener(smallSizeAdapter);
		btnY.setForeground(Color.BLACK);
		btnY.setHorizontalTextPosition(SwingConstants.CENTER);
		btnY.setText("Y");
		btnY.setFont(fontEN);
		btnY.setIcon(iconSmall);
		btnY.setIconTextGap(0);
		btnY.setBorder(null);
		btnY.setFocusPainted(false);
		btnY.setActionCommand("Y");
		btnY.addActionListener(textKeyPressListener);
		panel_1.add(btnY, "12, 1, fill, fill");

		JButton btnU = new JButton();
		btnU.addMouseListener(smallSizeAdapter);
		btnU.setForeground(Color.BLACK);
		btnU.setHorizontalTextPosition(SwingConstants.CENTER);
		btnU.setText("U");
		btnU.setFont(fontEN);
		btnU.setIcon(iconSmall);
		btnU.setIconTextGap(0);
		btnU.setBorder(null);
		btnU.setFocusPainted(false);
		btnU.setActionCommand("U");
		btnU.addActionListener(textKeyPressListener);
		panel_1.add(btnU, "14, 1, fill, fill");

		JButton btnI = new JButton();
		btnI.addMouseListener(smallSizeAdapter);
		btnI.setForeground(Color.BLACK);
		btnI.setHorizontalTextPosition(SwingConstants.CENTER);
		btnI.setText("I");
		btnI.setFont(fontEN);
		btnI.setIcon(iconSmall);
		btnI.setIconTextGap(0);
		btnI.setBorder(null);
		btnI.setFocusPainted(false);
		btnI.setActionCommand("I");
		btnI.addActionListener(textKeyPressListener);
		panel_1.add(btnI, "16, 1, fill, fill");

		JButton btnO = new JButton();
		btnO.addMouseListener(smallSizeAdapter);
		btnO.setForeground(Color.BLACK);
		btnO.setHorizontalTextPosition(SwingConstants.CENTER);
		btnO.setText("O");
		btnO.setFont(fontEN);
		btnO.setIcon(iconSmall);
		btnO.setIconTextGap(0);
		btnO.setBorder(null);
		btnO.setFocusPainted(false);
		btnO.setActionCommand("O");
		btnO.addActionListener(textKeyPressListener);
		panel_1.add(btnO, "18, 1, fill, fill");

		JButton btnP = new JButton();
		btnP.addMouseListener(smallSizeAdapter);
		btnP.setForeground(Color.BLACK);
		btnP.setHorizontalTextPosition(SwingConstants.CENTER);
		btnP.setText("P");
		btnP.setFont(fontEN);
		btnP.setIcon(iconSmall);
		btnP.setIconTextGap(0);
		btnP.setBorder(null);
		btnP.setFocusPainted(false);
		btnP.setActionCommand("P");
		btnP.addActionListener(textKeyPressListener);
		panel_1.add(btnP, "20, 1, fill, fill");

		JButton button_1 = new JButton();
		button_1.addMouseListener(smallSizeAdapter);
		button_1.addActionListener(functionKeyPressListener);
		button_1.setIcon(iconSmall);
		button_1.setText("后页");
		button_1.setIconTextGap(0);
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);
		button_1.setForeground(Color.BLACK);
		button_1.setFont(fontCN);
		button_1.setFocusPainted(false);
		button_1.setBorder(null);
		button_1.setActionCommand("FUNC7");
		panel_1.add(button_1, "22, 1, fill, fill");

		// A-L
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setForeground(Color.BLACK);
		getContentPane().add(panel_2, "2, 6, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("52px"), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode("51px"), }, new RowSpec[] { RowSpec.decode(_btn_h), }));

		JButton btnA = new JButton();
		btnA.addMouseListener(smallSizeAdapter);
		btnA.setForeground(Color.BLACK);
		btnA.setHorizontalTextPosition(SwingConstants.CENTER);
		btnA.setText("A");
		btnA.setFont(fontEN);
		btnA.setIcon(iconSmall);
		btnA.setIconTextGap(0);
		btnA.setBorder(null);
		btnA.setFocusPainted(false);
		btnA.setActionCommand("A");
		btnA.addActionListener(textKeyPressListener);
		panel_2.add(btnA, "2, 1, fill, fill");

		JButton btnS = new JButton();
		btnS.addMouseListener(smallSizeAdapter);
		btnS.setForeground(Color.BLACK);
		btnS.setHorizontalTextPosition(SwingConstants.CENTER);
		btnS.setText("S");
		btnS.setFont(fontEN);
		btnS.setIcon(iconSmall);
		btnS.setIconTextGap(0);
		btnS.setBorder(null);
		btnS.setFocusPainted(false);
		btnS.setActionCommand("S");
		btnS.addActionListener(textKeyPressListener);
		panel_2.add(btnS, "4, 1, fill, fill");

		JButton btnD = new JButton();
		btnD.addMouseListener(smallSizeAdapter);
		btnD.setForeground(Color.BLACK);
		btnD.setHorizontalTextPosition(SwingConstants.CENTER);
		btnD.setText("D");
		btnD.setFont(fontEN);
		btnD.setIcon(iconSmall);
		btnD.setIconTextGap(0);
		btnD.setBorder(null);
		btnD.setFocusPainted(false);
		btnD.setActionCommand("D");
		btnD.addActionListener(textKeyPressListener);
		panel_2.add(btnD, "6, 1, fill, fill");

		JButton btnF = new JButton();
		btnF.addMouseListener(smallSizeAdapter);
		btnF.setForeground(Color.BLACK);
		btnF.setHorizontalTextPosition(SwingConstants.CENTER);
		btnF.setText("F");
		btnF.setFont(fontEN);
		btnF.setIcon(iconSmall);
		btnF.setIconTextGap(0);
		btnF.setBorder(null);
		btnF.setFocusPainted(false);
		btnF.setActionCommand("F");
		btnF.addActionListener(textKeyPressListener);
		panel_2.add(btnF, "8, 1, fill, fill");

		JButton btnG = new JButton();
		btnG.addMouseListener(smallSizeAdapter);
		btnG.setForeground(Color.BLACK);
		btnG.setHorizontalTextPosition(SwingConstants.CENTER);
		btnG.setText("G");
		btnG.setFont(fontEN);
		btnG.setIcon(iconSmall);
		btnG.setIconTextGap(0);
		btnG.setBorder(null);
		btnG.setFocusPainted(false);
		btnG.setActionCommand("G");
		btnG.addActionListener(textKeyPressListener);
		panel_2.add(btnG, "10, 1, fill, fill");

		JButton btnH = new JButton();
		btnH.addMouseListener(smallSizeAdapter);
		btnH.setForeground(Color.BLACK);
		btnH.setHorizontalTextPosition(SwingConstants.CENTER);
		btnH.setText("H");
		btnH.setFont(fontEN);
		btnH.setIcon(iconSmall);
		btnH.setIconTextGap(0);
		btnH.setBorder(null);
		btnH.setFocusPainted(false);
		btnH.setActionCommand("H");
		btnH.addActionListener(textKeyPressListener);
		panel_2.add(btnH, "12, 1, fill, fill");

		JButton btnJ = new JButton();
		btnJ.addMouseListener(smallSizeAdapter);
		btnJ.setForeground(Color.BLACK);
		btnJ.setHorizontalTextPosition(SwingConstants.CENTER);
		btnJ.setText("J");
		btnJ.setFont(fontEN);
		btnJ.setIcon(iconSmall);
		btnJ.setIconTextGap(0);
		btnJ.setBorder(null);
		btnJ.setFocusPainted(false);
		btnJ.setActionCommand("J");
		btnJ.addActionListener(textKeyPressListener);
		panel_2.add(btnJ, "14, 1, fill, fill");

		JButton btnK = new JButton();
		btnK.addMouseListener(smallSizeAdapter);
		btnK.setForeground(Color.BLACK);
		btnK.setHorizontalTextPosition(SwingConstants.CENTER);
		btnK.setText("K");
		btnK.setFont(fontEN);
		btnK.setIcon(iconSmall);
		btnK.setIconTextGap(0);
		btnK.setBorder(null);
		btnK.setFocusPainted(false);
		btnK.setActionCommand("K");
		btnK.addActionListener(textKeyPressListener);
		panel_2.add(btnK, "16, 1, fill, fill");

		JButton btnL = new JButton();
		btnL.addMouseListener(smallSizeAdapter);
		btnL.setForeground(Color.BLACK);
		btnL.setHorizontalTextPosition(SwingConstants.CENTER);
		btnL.setText("L");
		btnL.setFont(fontEN);
		btnL.setIcon(iconSmall);
		btnL.setIconTextGap(0);
		btnL.setBorder(null);
		btnL.setFocusPainted(false);
		btnL.setActionCommand("L");
		btnL.addActionListener(textKeyPressListener);
		panel_2.add(btnL, "18, 1, fill, fill");

		// Z-M
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setForeground(Color.BLACK);
		getContentPane().add(panel_3, "2, 8, fill, fill");
		panel_3.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode(_s_gap), ColumnSpec.decode("111px"), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w), ColumnSpec.decode(_s_gap), ColumnSpec.decode(_btn_w),
				ColumnSpec.decode(_s_gap), ColumnSpec.decode("111px"), ColumnSpec.decode(_s_gap), }, new RowSpec[] { RowSpec.decode(_btn_h), }));

		JButton btnClose = new JButton();
		btnClose.addMouseListener(semiMiddleSizeAdapter);
		btnClose.setForeground(Color.BLACK);
		btnClose.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClose.setIcon(iconSemiMiddle);
		btnClose.setFont(fontCN);
		btnClose.setIconTextGap(0);
		btnClose.setText("中 / 英");
		btnClose.setBorder(null);
		btnClose.setFocusPainted(false);
		btnClose.setActionCommand("FUNC1");
		btnClose.addActionListener(functionKeyPressListener);
		panel_3.add(btnClose, "2, 1, fill, fill");

		JButton btnZ = new JButton();
		btnZ.addMouseListener(smallSizeAdapter);
		btnZ.setForeground(Color.BLACK);
		btnZ.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZ.setText("Z");
		btnZ.setFont(fontEN);
		btnZ.setIcon(iconSmall);
		btnZ.setIconTextGap(0);
		btnZ.setBorder(null);
		btnZ.setFocusPainted(false);
		btnZ.setActionCommand("Z");
		btnZ.addActionListener(textKeyPressListener);
		panel_3.add(btnZ, "4, 1, fill, fill");

		JButton btnX = new JButton();
		btnX.addMouseListener(smallSizeAdapter);
		btnX.setForeground(Color.BLACK);
		btnX.setHorizontalTextPosition(SwingConstants.CENTER);
		btnX.setText("X");
		btnX.setFont(fontEN);
		btnX.setIcon(iconSmall);
		btnX.setIconTextGap(0);
		btnX.setBorder(null);
		btnX.setFocusPainted(false);
		btnX.setActionCommand("X");
		btnX.addActionListener(textKeyPressListener);
		panel_3.add(btnX, "6, 1, fill, fill");

		JButton btnC = new JButton();
		btnC.addMouseListener(smallSizeAdapter);
		btnC.setForeground(Color.BLACK);
		btnC.setHorizontalTextPosition(SwingConstants.CENTER);
		btnC.setText("C");
		btnC.setFont(fontEN);
		btnC.setIcon(iconSmall);
		btnC.setIconTextGap(0);
		btnC.setBorder(null);
		btnC.setFocusPainted(false);
		btnC.setActionCommand("C");
		btnC.addActionListener(textKeyPressListener);
		panel_3.add(btnC, "8, 1, fill, fill");

		JButton btnV = new JButton();
		btnV.addMouseListener(smallSizeAdapter);
		btnV.setForeground(Color.BLACK);
		btnV.setHorizontalTextPosition(SwingConstants.CENTER);
		btnV.setText("V");
		btnV.setFont(fontEN);
		btnV.setIcon(iconSmall);
		btnV.setIconTextGap(0);
		btnV.setBorder(null);
		btnV.setFocusPainted(false);
		btnV.setActionCommand("V");
		btnV.addActionListener(textKeyPressListener);
		panel_3.add(btnV, "10, 1, fill, fill");

		JButton btnB = new JButton();
		btnB.addMouseListener(smallSizeAdapter);
		btnB.setForeground(Color.BLACK);
		btnB.setHorizontalTextPosition(SwingConstants.CENTER);
		btnB.setText("B");
		btnB.setFont(fontEN);
		btnB.setIcon(iconSmall);
		btnB.setIconTextGap(0);
		btnB.setBorder(null);
		btnB.setFocusPainted(false);
		btnB.setActionCommand("B");
		btnB.addActionListener(textKeyPressListener);
		panel_3.add(btnB, "12, 1, fill, fill");

		JButton btnN = new JButton();
		btnN.addMouseListener(smallSizeAdapter);
		btnN.setForeground(Color.BLACK);
		btnN.setHorizontalTextPosition(SwingConstants.CENTER);
		btnN.setText("N");
		btnN.setFont(fontEN);
		btnN.setIcon(iconSmall);
		btnN.setIconTextGap(0);
		btnN.setBorder(null);
		btnN.setFocusPainted(false);
		btnN.setActionCommand("N");
		btnN.addActionListener(textKeyPressListener);
		panel_3.add(btnN, "14, 1, fill, fill");

		JButton btnM = new JButton();
		btnM.addMouseListener(smallSizeAdapter);
		btnM.setForeground(Color.BLACK);
		btnM.setHorizontalTextPosition(SwingConstants.CENTER);
		btnM.setText("M");
		btnM.setFont(fontEN);
		btnM.setIcon(iconSmall);
		btnM.setIconTextGap(0);
		btnM.setBorder(null);
		btnM.setFocusPainted(false);
		btnM.setActionCommand("M");
		btnM.addActionListener(textKeyPressListener);
		panel_3.add(btnM, "16, 1, fill, fill");

		JButton btnBackspace = new JButton();
		btnBackspace.addMouseListener(semiMiddleSizeAdapter);
		btnBackspace.setForeground(Color.BLACK);
		btnBackspace.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBackspace.setIcon(iconSemiMiddle);
		btnBackspace.setFont(fontCN);
		btnBackspace.setIconTextGap(0);
		btnBackspace.setText("回退");
		btnBackspace.setBorder(null);
		btnBackspace.setFocusPainted(false);
		btnBackspace.setActionCommand("FUNC2");
		btnBackspace.addActionListener(functionKeyPressListener);
		panel_3.add(btnBackspace, "20, 1, fill, fill");

		// SHIFT SPACE ENTER
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setForeground(Color.BLACK);
		getContentPane().add(panel_4, "2, 10, fill, fill");
		panel_4.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode(_s_gap), ColumnSpec.decode("192px"), ColumnSpec.decode(_s_gap),
				ColumnSpec.decode("475px"), ColumnSpec.decode(_s_gap), ColumnSpec.decode("192px"), ColumnSpec.decode(_s_gap), }, new RowSpec[] { RowSpec
				.decode(_btn_h), }));

		JButton btnShift = new JButton();
		btnShift.addMouseListener(middleSizeAdapter);
		btnShift.setForeground(Color.BLACK);
		btnShift.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShift.setIcon(iconMiddle);
		btnShift.setFont(fontCN);
		btnShift.setIconTextGap(0);
		btnShift.setText("切换输入法");
		btnShift.setBorder(null);
		btnShift.setFocusPainted(false);
		btnShift.setActionCommand("FUNC3");
		btnShift.addActionListener(functionKeyPressListener);
		panel_4.add(btnShift, "2, 1, fill, fill");

		JButton btnSpace = new JButton();
		btnSpace.addMouseListener(longSizeAdapter);
		btnSpace.setIcon(iconLong);
		btnSpace.setForeground(Color.BLACK);
		btnSpace.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSpace.setFont(fontCN);
		btnSpace.setIconTextGap(0);
		btnSpace.setBorder(null);
		btnSpace.setFocusPainted(false);
		btnSpace.setActionCommand("FUNC4");
		btnSpace.addActionListener(functionKeyPressListener);
		panel_4.add(btnSpace, "4, 1, fill, fill");

		JButton btnEnter = new JButton();
		btnEnter.addMouseListener(middleSizeAdapter);
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEnter.setIcon(iconMiddle);
		btnEnter.setFont(fontCN);
		btnEnter.setIconTextGap(0);
		btnEnter.setText("确认");
		btnEnter.setBorder(null);
		btnEnter.setFocusPainted(false);
		btnEnter.setActionCommand("FUNC5");
		btnEnter.addActionListener(functionKeyPressListener);
		panel_4.add(btnEnter, "6, 1, fill, fill");

		RuntimeManager.setWindowOpacity(this, new Float(0.85));
		RuntimeManager.setWindowRoundCorner(this);
		RuntimeManager.smoothWindowCorner(this);
	}
}
