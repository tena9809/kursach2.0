package servlets;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class InitDataBaseServlet extends HttpServlet {

    DataBase db;
    DataBase db2;

    public InitDataBaseServlet(DataBase db, DataBase db2) {
        this.db = db;
        this.db2 = db2;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String [] meta2 = {"Name", "Cost", "Count", "Photo", "Category"};
        String[] meta = {"Login", "Password", "isAdmin"};
        db.create(meta);
        db2.create(meta2);
    }
}
