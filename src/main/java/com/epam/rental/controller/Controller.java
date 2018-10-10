package com.epam.rental.controller;


import com.epam.rental.command.Command;
import com.epam.rental.command.CommandFactory;
import com.epam.rental.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger log = LogManager.getLogger(Controller.class);
    private static final String COMMAND = "command";
    private static final String ERROR_JSP = "/WEB-INF/error.jsp";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        work(req, resp);
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        work(req, resp);
    }

    private void work(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lineCommand = req.getParameter(COMMAND);
        Command command;
        String page;
        try {
            command = CommandFactory.create(lineCommand);
            page = command.execute(req, resp);
        } catch (CommandException e) {
            log.error("Error executing command: ", e);
            page = ERROR_JSP;
        }


        RequestDispatcher requestDispatcher = //getServletContext().getRequestDispatcher(page);
                req.getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }
}
