package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final String INDEX_JSP = "index.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
       HttpSession session = req.getSession();
            session.invalidate();
            return INDEX_JSP;
    }
}
