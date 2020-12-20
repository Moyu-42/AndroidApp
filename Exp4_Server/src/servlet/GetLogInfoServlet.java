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
import java.util.List;
import java.util.Map;

@WebServlet(name = "getLogInfoServlet", urlPatterns = {"/getLogInfoServlet"})
public class GetLogInfoServlet extends HttpServlet {
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

            UserService userService = new UserService(db);
            User user = new User();
            user.setUsername(username);

            Person person = new Person();
            PersonService personService = new PersonService(db);
            List<Person> personList = personService.getQuery();

            for (Person p: personList) {
                if (p.getUsername().equals(user.getUsername())) {
                    person = p;
                    break;
                }
            }
            if (person.getUsername().isEmpty()) {
                person.setUsername(username);
            }

            JSONObject jsonObject = new JSONObject();
            String age;
            if (person.getAge() == null) {
                age = null;
            }else age = person.getAge().toString();
            jsonObject.put("name", person.getName());
            jsonObject.put("age", age);
            jsonObject.put("teleno", person.getTeleno());
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