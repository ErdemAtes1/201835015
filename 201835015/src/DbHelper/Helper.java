package DbHelper;

import javax.swing.JOptionPane;

public class Helper {
	
	public static void showMsg(String str) {
		String msg;
		
		switch(str) {
		case "fill":
			msg = "Please fill in the blanks.";
			break;
		case "success":
			msg="Operation successful";
			break;
		case "error":
			msg = "An error occured";
			break;
			default:
				msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE );
	}
	
	public static boolean confirm (String str) {
		String msg;
		switch(str) {
		case "sure":
			msg="Do you want to perfom this operation ?";
			break;
			default:
				msg = str;
				break;
		}
		
		int res = JOptionPane.showConfirmDialog(null, msg,"Attention",JOptionPane.YES_NO_OPTION);
		if(res==0) {
			return true;
		}else {
			return false;
		}
		
	}

}
