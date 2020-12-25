package servlet;

import bean.Database;
import bean.MD5;
import bean.Person;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import service.PersonService;
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

@WebServlet(name = "registerServlet", urlPatterns = {"/registerServlet"})
public class RegisterServlet extends HttpServlet {
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
            MD5 md5 = new MD5();
            passwd = md5.stringToMD5(passwd);
            String name = req.getParameter("name").trim();
            String age_ = req.getParameter("age").trim();
            String teleno = req.getParameter("teleno").trim();
            Integer age;
            if (age_.isEmpty()) {
                age = null;
            }else age = Integer.parseInt(age_);

            UserService userService = new UserService(db);
            PersonService personService = new PersonService(db);
            User user = new User(username, passwd);
            Person person = new Person(username, name, age, teleno);

            Boolean username_exist = userService.search(user);
            Boolean name_exist = personService.search(person, 0);

            String Result = new String();
            if (username_exist) {
                Result= "用户名已存在";
            }else if (name_exist) {
                Result = "该昵称已存在";
            }else {
                userService.addUser(user);
                personService.addPerson(person);
                Result = "注册成功!";
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
