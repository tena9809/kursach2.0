package accounts;

public class DataBaseShop {
    private String nameThing;
    private String cost;
    private String  count;
    private String photo;
    private String category;
    private String about;

    public DataBaseShop(String nameThing, String cost, String count, String photo,String category) {
        this.nameThing = nameThing;
        this.cost = cost;
        this.count = count;
        this.photo = photo;
        this.category = category;
    }

    public String getNameThing(){
        return nameThing;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCost() {
        return cost;
    }

    public String getPhoto(){
        return photo;
    }

    public String getCategory(){return category;}
}
