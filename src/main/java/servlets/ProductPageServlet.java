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

public class ProductPageServlet extends HttpServlet {
    DataBase dataBase;
    String name;

    public ProductPageServlet(DataBase dataBase) {
        this.dataBase = dataBase;
    }





    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name");
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        //  pageVariables.put("message", "");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(GeneratePage.instance().getPage("productPage.html", pageVariables));
        // response.setContentType("text/html;charset=windows-1251");

        response.setStatus(HttpServletResponse.SC_OK);

    }

   /* public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=windows-1251");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(GeneratePage.instance().getPage("catalog.html", pageVariables));
    }*/

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        StringBuilder html = new StringBuilder();
      //  html.append( "<!doctype html>" );
      //   html.append( "<html lang='en'>" );

      //  html.append( "<head>" );
      //  html.append( "<meta charset='utf-8'>" );
       // html.append( "<title>Report of Reports</title>" );
       // html.append( "</head>" );

       // html.append( "<body>" );

        String[] product = dataBase.select("Name", name);
        String[] all = product[0].split(" ");

       // html.append( "<tr>");
        html.append(all[0]);
        html.append(all[1]);
      //  html.append("<th>Категория товара</th>");
       // html.append("<th>Подробнее</th>");
       // html.append(" </tr>");
       // html.append( "</body>" );

       // html.append( "</html>" );

        //html.toString();
        pageVariables.put("page", html.toString() );

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

