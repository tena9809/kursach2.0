package DB;

import java.io.FileNotFoundException;

public interface DataBaseInt {
    public String[] select(String name, String value);
    public boolean delete(String name, String value) throws FileNotFoundException;
    public void insert(String[] str);
    public void create(String[] meta);
    public boolean check();
}
