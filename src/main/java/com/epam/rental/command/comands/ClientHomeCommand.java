package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientHomeCommand implements Command {
    private static final String WEB_INF_CLIENT_CLIENT_JSP = "/WEB-INF/client/client.jsp";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return WEB_INF_CLIENT_CLIENT_JSP;
    }
}
