import java.io.*;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

/**
 * This manages the users and their profiles.
 *
 * @author Peter Lin
 * @version 1.9
 */

public class User{
	private static String[] users;
	private static int index;
	private static int n;
	private static String username;
	private static char[] password;

	/**
	 * This reads the users from a data file.
	 */
	public static void read(){
		try{
			BufferedReader br;
			if (new File(System.getProperty("user.home") + "data/users.dat").exists())
				br = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.home") + "data/users.dat")));
			else
				br = new BufferedReader(new InputStreamReader(User.class.getResourceAsStream("data/users.dat")));
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

	/**
	 * Gets the current user as a <code>String</code> delimited with escape characters.
	 *
	 * @return the String containing the profile, or null if no user is logged in.
	 */
	public static String getUser(){
		if (index == 0)
			return null;
		return users[index];
	}

	/**
	 * Gets the username of the current user.
	 *
	 * @return the username of the logged in user, or null if no user is logged in.
	 */
	public static String getUsername(){
		return username;
	}

	/**
	 * Gets the password of the current user.
	 *
	 * @return the password of the logged in user, or null if no user is logged in.
	 */
	public static char[] getPassword(){
		return password;
	}

	/**
	 * Logs out the current user.
	 * User should be redirected to the intro menu afterwards.
	 */
	public static void logout(){
		index = 0;
		read();
	}

	/**
	 * Logs in a user with given credentials.
	 *
	 * @param username the username to log in with
	 * @param password the password to log in with
	 * @return true if the login was successful, otherwise false
	 */
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
			else if (name.compareTo(username) > 0){
				return false;
			}
		}
		return false;
	}

	/**
	 * Registers in a user with given credentials.
	 *
	 * @param username the username to register with
	 * @param password the password to register with
	 * @return true if the registration was successful, otherwise false
	 */

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
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home") + "data/users.dat")));
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