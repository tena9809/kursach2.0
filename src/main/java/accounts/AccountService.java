package accounts;

import DB.DataBase;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final DataBase db;

    public AccountService(DataBase db) {
       this.db = db;
    }

    public void createNewUser(UserProfile userProfile) throws Exception {
        if(db.select("Login", userProfile.getLogin()).length == 0) {

            String data[] = {userProfile.getLogin(), Integer.toString(userProfile.getPass()), Boolean.toString(userProfile.isAdmin())};
            db.insert(data);
        }
        else{
            throw new Exception("login already exist");
        }
    }

    /*public String getPass(String login){
        db.select("Login", login);

    }*/

    public UserProfile getUserByLogin(String login){
        String[] datab = db.select("Login", login)[0].split(";");
        return new UserProfile(datab[0], Integer.valueOf(datab[1]), Boolean.valueOf(datab[2]));
    }
}
