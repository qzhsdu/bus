import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fast")
public class FastQ extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        int interval  = Integer.valueOf(request.getParameter("interval"));
        String s = JSON.toJSONString(Task.findFast(from,to,interval));
        response.getWriter().write(s);
    }
}
