package net.epam.java.autobase;


public interface IUser {

    public void changePassword(int sid, String password);

    public void setSuspended(int sid, boolean value);

    public int logIn(String username, String password) throws AuthorizationException;
    
    public void logOut(int sid);

}
