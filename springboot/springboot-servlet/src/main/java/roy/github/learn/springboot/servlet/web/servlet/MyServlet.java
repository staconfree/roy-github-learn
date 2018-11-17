package roy.github.learn.springboot.servlet.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/11/17 0017.
 */
@WebServlet(urlPatterns = "/web/servlet", asyncSupported = true)
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一种情况，同步输出
//        resp.getWriter().write("hello world");
        // 第二种情况，异步输出，同时上面的WebServlet 需要增加属性asyncSupported = true
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(()->{
            try {
                resp.getWriter().write("hello world");
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
