import java.io.*;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

public class User{
	private static String[] users;
	private static int index;
	private static int n;
	private static String username;
	private static char[] password;
	public static void read(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/users.dat")));
			n = Integer.parseInt(br.readLine());
			users = new String[n];
			for (int i = 0; i < n; i ++){
				users[i] = br.readLine();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	public static String getUser(){
		if (index == 0)
			return null;
		return users[index];
	}
	public static String getUsername(){
		return username;
	}

	public static char[] getPassword(){
		return password;
	}

	public static void logout(){
		index = 0;
	}

	public static boolean login(String username, char[] password){
		for (int i = 0; i < users.length; i ++){ // check for taken usernames
			StringTokenizer st = new StringTokenizer(users[i], "\u001b");
			String name = st.nextToken();
			if (name.compareTo(username) == 0){
				if (st.nextToken().equals(new String(password))){
					index = i;
					User.username = name;
					User.password = password;
					return true;
				}
				return false;
			}
			if (name.compareTo(username) > 0){
				return false;
			}
		}
		return false;
	}
	public static boolean register(String username, char[] password){
		if (username.length() == 0){
			JOptionPane.showMessageDialog(Main.frame, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (password.length == 0){
			JOptionPane.showMessageDialog(Main.frame, "Please enter a password.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (username.charAt(0) == ' ' || username.charAt(username.length() - 1) == ' '){ // check for leading or trailing spaces
			JOptionPane.showMessageDialog(Main.frame, "Please do not enter leading or trailing spaces.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (username.length() == 0){ // check for empty username
			JOptionPane.showMessageDialog(Main.frame, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (username.length() > 15){ // check for long username
			JOptionPane.showMessageDialog(Main.frame, "Your username is too long.", "Error", JOptionPane.ERROR_MESSAGE);
			return false; //username is too long
		}
		for (int i = 0; i < username.length(); i ++){ // check for weird characters
			if (username.charAt(i) < ' '){
				JOptionPane.showMessageDialog(Main.frame, "Your username contains invalid characters.", "Error", JOptionPane.ERROR_MESSAGE);
				return false; // contains invalid characters
			}
		}
		for (int i = 0; i < users.length; i ++){ // check for taken usernames
			String name = users[i].substring(0, users[i].indexOf('\u001b'));
			if (name.equals(username)){
				JOptionPane.showMessageDialog(Main.frame, "Sorry, this username is taken.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else if (name.compareTo(username) > 0){
				index = i + 1;
				User.username = username;
				User.password = password;
				try{
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("data/users.dat")));
					pw.println(n + 1);
					for (int j = 0; j < i; j ++){
						pw.println(users[j]);
					}
					pw.println(username + "\u001b" + new String(password));
					while (i < users.length){
						pw.println(users[i++]);
					}
					pw.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
				return true;
			}
		}
		return true;
	}
}