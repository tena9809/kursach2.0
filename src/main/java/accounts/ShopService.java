package accounts;

import DB.DataBase;

public class ShopService {
    private final DataBase db;

    public ShopService(DataBase db) {
        this.db = db;
    }

    public void createNewThing(DataBaseShop dataBaseShop) throws Exception {
        if(db.select("Name", dataBaseShop.getNameThing()).length == 0){

            String data[] = {dataBaseShop.getNameThing(), dataBaseShop.getCost(), dataBaseShop.getCount(), dataBaseShop.getPhoto(), dataBaseShop.getCategory()};
            db.insert(data);
        }
        else{
            throw new Exception("name already exist");
        }
    }

    public DataBaseShop getCostByName(String name){
        String[] datab = db.select("Name", name)[0].split(";");
        return new DataBaseShop(datab[0], datab[1], datab[2], datab[3], datab[4]);
    }
}
