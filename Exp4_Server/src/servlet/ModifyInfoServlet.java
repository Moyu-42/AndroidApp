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

@WebServlet(name = "modifyInfoServlet", urlPatterns = {"/modifyInfoServlet"})
// 只能修改name, age, teleno
// 密码的修改发送到changePasswdServlet
public class ModifyInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/jsp; charset=utf-8");

        HttpSession sc = req.getSession();
        Database db;
        if (sc.getAttribute("database") == null) {
            db = new Database();
        } else db = (Database)sc.getAttribute("database");
        try(PrintWriter out = resp.getWriter()) {
            String username;
            String origin_name;

            String name = req.getParameter("name").trim();
            String age_ = req.getParameter("age").trim();
            String teleno = req.getParameter("teleno").trim();
            Person pp = new Person();

            if (sc.getAttribute("person") == null) {
                username = req.getParameter("username").trim();
                origin_name = req.getParameter("origin_name").trim();
            }else {
                pp = (Person)sc.getAttribute("person");
                username = pp.getUsername();
                origin_name = pp.getName();
            }

            Integer age;
            if (age_.isEmpty()) {
                age = null;
            }else age =Integer.parseInt(age_);
            if (name.isEmpty()) {
                name = origin_name;
            }

            PersonService personService = new PersonService(db);
            UserService userService = new UserService(db);
            Person person = new Person(username, name, age, teleno);

            JSONObject jsonObject = new JSONObject();
            // name已经存在 不能修改
            Boolean flag =personService.search(person, 0);
            String Result = new String();
            if (flag && !name.equals(origin_name)) {
                Result = "Name已经存在";
            }else {
                // 读出username对应的信息
                Person person_origin = new Person();
                person_origin.setUsername(username);
                person_origin.setName(origin_name);
                // name相同，直接insert
                if (person_origin.getName().equals(person.getName())) {
                    personService.addPerson(person);
                }
                // name不同，先删除再添加
                else {
                    User user = new User();
                    List<User> userList = userService.getQuery();
                    for (User u: userList) {
                        if (u.getUsername().equals(username)) {
                            user = u;
                        }
                    }
                    personService.deletePerson(person_origin);
                    userService.addUser(user);
                    personService.addPerson(person);
                }
                Result = "修改信息成功!";
            }
            sc.setAttribute("person", person);
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