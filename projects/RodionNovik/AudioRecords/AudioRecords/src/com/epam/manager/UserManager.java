package com.epam.manager;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserManager {
		
	public static void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			ServletException, IOException, SQLException {
		String usersLogin = request.getParameterValues("usersLogin")[0];
		String usersPassword = cipherPassword(request.getParameterValues("usersPassword")[0]);
		if (usersLogin!=null && usersPassword!=null
				&& isUserExist(usersLogin, usersPassword)) {
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/myprojectdb", "root", "1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT Type FROM users WHERE Login=\""
				+ usersLogin + "\" AND Password=\"" + usersPassword + "\"");
			String role="";
			while(rs.next()){
				role = rs.getString("Type");
			}
			request.getSession().setAttribute("user", usersLogin);
			request.getSession().setAttribute("role", role);
		} else {
			request.setAttribute("err", "Invalid login and/or password");
		}

	}

	public static void doRegistration(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException {
		String login = request.getParameterValues("usersLogin")[0];
		String password = request.getParameterValues("usersPassword")[0];
		if (password.equals(request.getParameterValues("passwordConfirm")[0])
				&& !password.equals("")) {
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/myprojectdb", "root", "1234");
			Statement st = con.createStatement();
			password = cipherPassword(password);
			st.executeUpdate("INSERT INTO users (Login,Password,Type) VALUES (\""
					+ login + "\",\"" + password + "\"" + ",\"user\")");
			request.setAttribute("err", "Registration was seccsessful!!!");
			request.getRequestDispatcher("\\index.jsp").forward(request,
					response);
		} else {
			request.setAttribute("err", "invalid data, try again");
			System.out.println(" не добавлен");
			request.getRequestDispatcher("\registrationPage").forward(request,
					response);
		}

	}

	public static boolean isUserExist(String usersLogin, String usersPassword)
			throws ClassNotFoundException, SQLException {
		int count = 0;
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/myprojectdb", "root", "1234");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM users WHERE Login=\""
				+ usersLogin + "\" AND Password=\"" + usersPassword + "\"");
		while (rs.next()) {
			count++;
		}
		if (count == 1)
			return true;
		else
			return false;
	}

	public static String cipherPassword(String usersPassword) {
		MessageDigest md5;
		StringBuffer hexString = new StringBuffer();
		try {

			md5 = MessageDigest.getInstance("md5");

			md5.reset();
			md5.update(usersPassword.getBytes());

			byte messageDigest[] = md5.digest();

			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}

		} catch (NoSuchAlgorithmException e) {
			return e.toString();
		}
		return hexString.toString();

	}

	public static void LogOut(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
