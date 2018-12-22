package servlets;

import DB.DataBase;
import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class RegistServlet extends HttpServlet {
    AccountService accountService;

    public RegistServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        FileInputStream fileInputStream = new FileInputStream("public_html/reg.html");
        Scanner scanner = new Scanner(fileInputStream);
        String s = "";
        while(scanner.hasNext()){
            s += scanner.nextLine();
        }
        response.setContentType("text/html;charset=windows-1251");
        response.getWriter().println(s);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        String codeWord = request.getParameter("codeword");
        String selectValue = request.getParameter("selectparam");
        UserProfile user = new UserProfile(login, password.hashCode(), selectValue.equals("Admin") && codeWord.equals("1234")); //todo: если выбран админ и ключевое слово не совпадает try again
        try {
            accountService.createNewUser(user);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        response.sendRedirect("");
    }
}
