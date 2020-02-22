import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cheap")
public class Cheap extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String s = JSON.toJSONString(Task.findCheap(from,to));
        response.getWriter().write(s);
    }
}
