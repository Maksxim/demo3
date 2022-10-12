package com.example.demo3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "Course", urlPatterns = "/course/*")
public class Course extends HttpServlet {

    private List<User> list = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/course.jsp");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        if(requestUri.equals("/course")) {
            if(!validateData(name, phone, email)) {
                String error = "Wrong data";
                req.setAttribute("error", error);
                reqDispatcher.include(req, resp);
                return;
            }
            list.add(new User(name, phone, email));
            req.setAttribute("list", list);
            reqDispatcher.forward(req, resp);
        }
        else if(requestUri.startsWith("/course/edit/")){
            if(!validateData(name, phone, email)) {
                String error = "Wrong data";
                req.setAttribute("error", error);
                reqDispatcher.include(req, resp);
                return;
            }
            String editName = requestUri.replace("/course/edit/", "");
            User user = list.stream().filter(u -> u.getName().equals(editName)).findFirst().orElse(null);
            if(user == null) {
                String error = "User name=" + editName + " not found";
                req.setAttribute("error", error);
                reqDispatcher.include(req, resp);
                return;
            }
            user.setPhone(phone);
            user.setEmail(email);

            req.setAttribute("list",list);
            reqDispatcher.forward(req,resp);
        } else if(requestUri.startsWith("/course/delete/")){
            String deleteName = requestUri.replace("/course/delete/", "");
            User user = list.stream().filter(u -> u.getName().equals(deleteName)).findFirst().orElse(null);
            if(user == null) {
                String error = "User name=" + deleteName + " not found";
                req.setAttribute("error", error);
                reqDispatcher.include(req, resp);
                return;
            }
            list.remove(user);

            req.setAttribute("list",list);
            reqDispatcher.forward(req,resp);
        }

    }

    private boolean validateData(String name, String phone, String email) {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().equals("/course")) {
            RequestDispatcher reqDispatcher = req.getRequestDispatcher("/course.jsp");
            req.setAttribute("list", list);
            reqDispatcher.forward(req, resp);
        } else if (req.getRequestURI().startsWith("/course/edit/")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/edit.jsp");
            String requestURI = req.getRequestURI();
            String name = requestURI.substring(13);
            User user = list.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
            req.setAttribute("user",user);
            requestDispatcher.forward(req,resp);
        }
    }
}
