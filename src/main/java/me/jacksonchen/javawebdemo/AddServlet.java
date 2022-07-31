package me.jacksonchen.javawebdemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //under get method
        String fname = req.getParameter("fname");
        //send string back to bytes ISO-8859-1
        byte[] bytes = fname.getBytes("ISO-8859-1");
        //regroup bytes by following encode
        fname = new String(bytes, "UTF-8");


        String fprice = req.getParameter("price");
        Integer price = Integer.parseInt(fprice);
        System.out.println(fname);
        System.out.println(price);

    }
}
