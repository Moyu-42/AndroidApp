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
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "downloadServlet", urlPatterns = {"/downloadServlet"})
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = "Moyu's World.apk";
        //设置文件MIME类型
        resp.setContentType(getServletContext().getMimeType(filename));
        //设置Content-Disposition
        resp.setHeader("Content-Disposition", "attachment;filename="+filename);
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = getServletContext().getRealPath("/download/" + filename);
        //System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = resp.getOutputStream();

        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }

        in.close();
        out.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
