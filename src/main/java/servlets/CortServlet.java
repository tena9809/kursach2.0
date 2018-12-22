package servlets;

import DB.DataBase;
import accounts.DataBaseShop;
import accounts.ShopService;
import generate.GeneratePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CortServlet {
    HashMap<String , List<DataBaseShop>> corts;
    ShopService shopService;

    public CortServlet(HashMap<String , List<DataBaseShop>> corts, ShopService shopService) {
        this.corts = corts;
        this.shopService = shopService;
    }





    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        //String cost = request.getParameter("cost");
        String count = request.getParameter("count");
        String nameUser = request.getCookies()[0].getValue();

        if(!corts.containsKey(nameUser)) {
            corts.put(nameUser, new ArrayList<>());
        }
        List<DataBaseShop> list = corts.get(nameUser);
        boolean flag = false;
        int index = 0;
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getNameThing() == name){
                flag = true;
                index = i;
            }
        }

        if(flag){
            list.add(new DataBaseShop(name, shopService.getCostByName(name).getCost(), count, shopService.getCostByName(name).getPhoto(), shopService.getCostByName(name).getCategory()));
        }
        else {
            list.get(index).setCount(list.get(index).getCount() + count);
        }
    }


}
