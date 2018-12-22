package servlets;

import DB.DataBase;
import generate.GeneratePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllRequestsServlet extends HttpServlet {

    DataBase dataBase;
    DataBase dataBase2;

    public AllRequestsServlet(DataBase dataBase, DataBase dataBase2) {
        this.dataBase = dataBase;
        this.dataBase2 = dataBase2;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String[] categ = {"forFace", "forHair", "forBody", "makeUp"};
        State state = State.ROOT;
        if(request.getCookies()!= null) {

            String userName = request.getCookies()[0].getValue();
            boolean isAdmin = Boolean.valueOf(dataBase2.select("Login", userName)[0].split(";")[2]);
            if(isAdmin == true) {
                state = State.ADMIN;
            }
            if(isAdmin == false){
                state = State.USER;
            }
        }
        else{
            state = State.ROOT;
        }


        Map<String, Object> pageVariables = createPageVariablesMap(request, categ, state);
      //  pageVariables.put("message", "");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(GeneratePage.instance().getPage("catalog.html", pageVariables));
       // response.setContentType("text/html;charset=windows-1251");

        response.setStatus(HttpServletResponse.SC_OK);

    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, String[] category, State state ) {

        Map<String, Object> pageVariables = new HashMap<>();
        for(int j=0; j<category.length; j++) {
        StringBuilder html = new StringBuilder();
        StringBuilder htmlRoot = new StringBuilder();

           if(state == State.ROOT){
                htmlRoot.append("<form action=\"auth\" method=\"get\">");
                htmlRoot.append("<input style=\"background-color: #2b2301; color: #fff; display: inline-block; padding: 3px 10px; font-weight: bold; border-radius: 5px;\" type=\"submit\" value=\"Войти\" />");
                htmlRoot.append("</form>");
                htmlRoot.append("<form action=\"reg\" method=\"get\">");
                htmlRoot.append("<input style=\"background-color: #2b2301; color: #fff; display: inline-block; padding: 3px 10px; font-weight: bold; border-radius: 5px;\" type=\"submit\" value=\"Зарегестрироваться\" />");
                htmlRoot.append("</form>");
            }

            else {
                htmlRoot.append("<p></p>");
            }

            pageVariables.put("buttons", html.toString());

            //html = new StringBuilder();

            html.append("<table style=\"margin: auto;\" border=\"1\">");
            html.append( "<tbody>");
            html.append( "<tr>");
            html.append("<th>Наименование товара</th>");
            html.append("<th>Количество</th>");
            html.append("<th>Цена</th>");
            html.append("<th>Фото товара</th>");
            html.append("<th>Категория товара</th>");
            html.append("<th>Подробнее</th>");
            if(state == State.ADMIN){
                html.append("<th>Изменить</th>");
            }

            html.append(" </tr>");



            int i = 0;
            String[] all = dataBase.select("Category", category[j]);
            while (i != all.length) {
                html.append("<tr>");
                String[] all2 = all[i].split(";");
                html.append("<td style=\"width: 300px;\">" + all2[0] + "</td>");
                html.append("<td style=\"width: 150px;\">" + all2[1] + "</td>");
                html.append("<td style=\"width: 80px;\">" + all2[2] + "</td>");
                html.append("<td style=\"width: 150px;\">" + all2[3] + "</td>");
                html.append("<td style=\"width: 150px;\">" + "<>" + "</td>");
                if(state == State.ADMIN){
                    html.append("<td style=\"width: 150px;\">" + "" + "</td>");
                }
                //  html.append("<td style=\"width: 150px;\">" + all2[4] + "</td>");
                html.append("</tr>");

                i++;
            }
            html.append(" </tbody>");
            html.append("</table>");

            StringBuilder htmlAdd = new StringBuilder();

            if(state == State.ADMIN) {
                htmlAdd.append("<form action=\"add\" method=\"get\"><input style=\"background-color: #2b2301; color: #fff; display: inline-block; padding: 3px 10px; font-weight: bold; border-radius: 5px;\" type=\"submit\" value=\"Добавить товар\" /></form>");
            }

            pageVariables.put("add", htmlAdd.toString());
            //html.append( "</body>" );

            //html.append( "</html>" );

            //html.toString();
            pageVariables.put(category[j], html.toString());
        }

       /* pageVariables.put("method", "<table style=\"margin: auto;\" border=\"1\">\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <th>Наименование товара</th>\n" +
                "                <th>Количество</th>\n" +
                "                <th>Цена</th>\n" +
                "                <th>Подробнее</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td style=\"width: 300px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 80px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td style=\"width: 300px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 80px;\">&nbsp;</td>\n" +
                "                <td style=\"width: 150px;\">&nbsp;</td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>");*/
      //  pageVariables.put("URL", request.getRequestURL().toString());
     //   pageVariables.put("pathInfo", request.getPathInfo());
       // pageVariables.put("sessionId", request.getSession().getId());
      //  pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
