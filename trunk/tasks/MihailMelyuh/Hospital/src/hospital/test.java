package hospital;

import java.io.IOException;
import java.util.ArrayList;

public class test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		User user=new User("Mihail","12345");
		user.setAccessRights(AccessRights.Admin);
		Hospital hospital=new Hospital();
		ArrayList<User> users=hospital.getUsers();
		users.add(user);
		hospital.saveUsers("Users.bin");

	}

}
