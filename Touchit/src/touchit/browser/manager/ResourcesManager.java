package touchit.browser.manager;

import java.awt.Font;

import javax.swing.ImageIcon;

public class ResourcesManager {
	private static Font font1;
	private static Font font2;

	private static ImageIcon keyIconSmall;
	private static ImageIcon keyIconSemiMiddle;
	private static ImageIcon keyIconMiddle;
	private static ImageIcon keyIconLong;

	private static ImageIcon keyIconSmallSelected;
	private static ImageIcon keyIconSemiMiddleSelected;
	private static ImageIcon keyIconMiddleSelected;
	private static ImageIcon keyIconLongSelected;

	public static ImageIcon getKeyIconSmallSelected() {
		return keyIconSmallSelected;
	}

	private static ImageIcon keyboardBg;
	static {
		font1 = new Font("Courier New", Font.BOLD, 30);
		font2 = new Font("微软雅黑", Font.BOLD, 22);
		keyIconSmall = new ImageIcon(ResourcesManager.class.getResource("/Button_small_unselected.png"));
		keyIconSemiMiddle = new ImageIcon(ResourcesManager.class.getResource("/Button_semi_middle_unselected.png"));
		keyIconMiddle = new ImageIcon(ResourcesManager.class.getResource("/Button_middle_unselected.png"));
		keyIconLong = new ImageIcon(ResourcesManager.class.getResource("/Button_long_unselected.png"));

		keyIconSmallSelected = new ImageIcon(ResourcesManager.class.getResource("/Button_small_selected.png"));
		keyIconSemiMiddleSelected = new ImageIcon(ResourcesManager.class.getResource("/Button_semi_middle_selected.png"));
		keyIconMiddleSelected = new ImageIcon(ResourcesManager.class.getResource("/Button_middle_selected.png"));
		keyIconLongSelected = new ImageIcon(ResourcesManager.class.getResource("/Button_long_selected.png"));

		keyboardBg = new ImageIcon(ResourcesManager.class.getResource("/Keyboard_bg.png"));
	}

	public static Font getFontKeyboardEN() {
		return font1;
	}

	public static Font getFontKeyboardCN() {
		return font2;
	}

	public static ImageIcon getKeyboardBg() {
		return keyboardBg;
	}

	public static ImageIcon getKeyIconSmall() {
		return keyIconSmall;
	}

	public static ImageIcon getKeyIconSemiMiddle() {
		return keyIconSemiMiddle;
	}

	public static ImageIcon getKeyIconMiddle() {
		return keyIconMiddle;
	}

	public static ImageIcon getKeyIconLong() {
		return keyIconLong;
	}

	public static ImageIcon getKeyIconSemiMiddleSelected() {
		return keyIconSemiMiddleSelected;
	}

	public static ImageIcon getKeyIconMiddleSelected() {
		return keyIconMiddleSelected;
	}

	public static ImageIcon getKeyIconLongSelected() {
		return keyIconLongSelected;
	}

}
