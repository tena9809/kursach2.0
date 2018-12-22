package accounts;
public class UserProfile {
    private final String login;
    private final int pass;
    private final boolean isAdmin;

    public UserProfile(String login, int pass, boolean isAdmin) {
        this.login = login;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }


    public String getLogin() {
        return login;
    }

    public int getPass() {
        return pass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
