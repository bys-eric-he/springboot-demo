package com.example.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "httpRequestServlet", urlPatterns = "/api/hello")
public class HttpRequestServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info(">>>>>>>>>HttpRequestServlet.init Function>>>>>>>>>");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(">>>>>>>>>HttpRequestServlet.doGet Function>>>>>>>>>");
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("<<<<<<<<<<<<<<<<HttpRequestServlet.doPost Function<<<<<<<<<<<<");
        super.doPost(request, response);
    }
}
