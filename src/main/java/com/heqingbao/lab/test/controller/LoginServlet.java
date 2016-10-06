package com.heqingbao.lab.test.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by heqingbao on 16/10/6.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String newUid = uid;
        int i = 2;
        Map<String, List<String>> chat = ChatServlet.getChatMap();
        synchronized (chat) {
            if ("you".equalsIgnoreCase(newUid)) {
                newUid = uid + i++;
            }
            while (chat.containsKey(newUid)) {
                newUid = uid + i++;
            }
            uid = newUid;
            chat.put(uid, new ArrayList<String>());
        }

        req.getSession().setAttribute("UID", uid);
        resp.sendRedirect("chat.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
