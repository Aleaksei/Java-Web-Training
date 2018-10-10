package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;
import com.epam.rental.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginCommand implements Command {


    private static final String WEB_INF_ADMIN_JSP = "/WEB-INF/admin/admin.jsp";
    private static final String ERROR_JSP = "/WEB-INF/error.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        LoginCommand command = new LoginCommand(new AdminService());
        String result = command.execute(req, resp);

        switch (result){
            case "success":
                return WEB_INF_ADMIN_JSP;
            case "error":
            default:
                return ERROR_JSP;
        }

    }

}
