//package DB;
//
//public class DataBaseTest {
//    public static void main(String[] args) {
//        DataBase dataBase = new DataBase();
//        String tableName = "users.txt";
//        String[] meta = {"Login", "Password"};
//        if(!dataBase.check(tableName)){
//            dataBase.create(meta, tableName);
//        }
//        String[] str = {"root", "12345"};
//        String[] us = {"user", "65276"};
//        dataBase.insert(str, tableName);
//        dataBase.insert(us, tableName);
//        String[] ar = dataBase.select("Login", "root", tableName);
//        boolean b = dataBase.delete("Login", "root", tableName);
//        return;
//    }
//}
