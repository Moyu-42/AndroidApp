package servlet;

import bean.Database;
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

@WebServlet(name = "deleteServlet", urlPatterns = {"/deleteServlet"})
public class DeleteServlet extends HttpServlet {
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
            if (username.isEmpty()) {
                Person pp = (Person)sc.getAttribute("person");
                username = pp.getUsername();
            }

            PersonService personService = new PersonService(db);
            Person person = new Person();
            person.setUsername(username);
            boolean result = personService.search(person, 1);
            String Result = "";
            if (result) {
                personService.deletePerson(person);
                Result = "注销成功!";
            }else {
                Result = "用户名不存在!";
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
