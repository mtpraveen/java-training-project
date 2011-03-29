package net.epam.java.autobase;

import java.util.ArrayList;

public interface IAdmin {

    public void setUserSuspended(int sid, User user, boolean value);

    public void addUser(int sid, String username, String password, UserType userType) throws AutobaseException;

    public void deleteUser(int sid, User user);

    public ArrayList<User> getUsersList(int sid);

    public void setUserPassword(int sid, User user, String password);

    public User getUserById(int sid, int id);

}
