package servlet;

import bean.Database;
import bean.Person;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "changePasswdServlet", urlPatterns = {"/changePasswdServlet"})
public class ChangePasswdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/jsp; charset=utf-8");

        HttpSession sc = req.getSession();
        Database db;
        if (sc.getAttribute("database") == null) {
            db = new Database();
        }
        else db = (Database)sc.getAttribute("database");
        try(PrintWriter out = resp.getWriter()) {
            String username = req.getParameter("username").trim();
            String passwd = req.getParameter("password").trim();
            if (username.isEmpty()) {
                Person pp = (Person)sc.getAttribute("person");
                username = pp.getUsername();
            }

            UserService userService = new UserService(db);
            User user = new User(username, passwd);
            int result = userService.login_varify(user);
            String Result = new String();

            if (result == 0) {
                Result = "新密码不能与原密码相同!";
            }else if (result == 1) {
                Result = "用户名不存在!";
            } else {
                userService.addUser(user);
                Result = "修改成功!";
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Result", Result);
            out.write(jsonObject.toString());
        }
        if (sc.getAttribute("database") == null) {
            db.close();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
