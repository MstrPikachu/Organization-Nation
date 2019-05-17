import javax.swing.SwingUtilities;
import java.util.TreeMap;
import java.io.*;

public class Main implements Runnable{
	public static final Frame frame = new Frame();
	public static String[] users;
	static int usernameIndex;
	public void run(){
		frame.intro();
	}
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/users.dat")));
		int n = Integer.parseInt(br.readLine());
		users = new String[n];
		for (int i = 0; i < n; i ++){
			users[i] = br.readLine();
		}

		SwingUtilities.invokeLater(new Main());
	}
}