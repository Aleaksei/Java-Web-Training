package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHomeCommand implements Command {
    private static final String WEB_INF_ADMIN_JSP = "/WEB-INF/admin/admin.jsp";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return WEB_INF_ADMIN_JSP;
    }
}
