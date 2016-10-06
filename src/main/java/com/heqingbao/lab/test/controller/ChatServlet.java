package com.heqingbao.lab.test.controller;

import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heqingbao on 16/10/6.
 */
public class ChatServlet extends HttpServlet {

    private static Map<String, List<String>> _chat = new HashMap<String, List<String>>();

    public static Map<String, List<String>> getChatMap() {
        return _chat;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("send".equals(action)) {
            String msg = req.getParameter("msg");
            String uid = (String) req.getSession().getAttribute("UID");
            for (String s : _chat.keySet()) {
                if (!s.equals(uid)) {
                    synchronized (_chat.get(s)) {
                        _chat.get(s).add(uid + " said: " + msg);
                    }
                }
            }
        } else if ("get".equals(action)) {
            String uid = (String) req.getSession().getAttribute("UID");
            if (uid == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            List<String> l = _chat.get(uid);
            synchronized (l) {
                if (l.size() > 0) {
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter out = resp.getWriter();
                    JSONArray jsna = new JSONArray();
                    while (l.size() > 0) {
                        jsna.add(l.remove(0));
                    }

                    out.println(jsna);
                    out.close();
                }
            }
        }
    }
}
