package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;
import com.epam.rental.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientLoginCommand implements Command {


    private static final String WEB_INF_CLIENT_CLIENT_JSP = "/WEB-INF/client/client.jsp";
    private static final String ERROR_JSP = "/WEB-INF/error.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
//        LoginCommand command = new LoginCommand(new ClientService());
//        String result = command.execute(req, resp);
//        switch (result){
//            case "success":
//                return WEB_INF_HELLO_PAGE_JSP;
//            case "error":
//            default:
//                return ERROR_JSP;
//        }
        return WEB_INF_CLIENT_CLIENT_JSP;
    }

}
