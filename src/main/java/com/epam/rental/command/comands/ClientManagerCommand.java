package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.entities.User;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientManagerCommand implements Command {
    private final static String WEB_INF_ADMIN_CLIENT_MANAGER_JSP = "/WEB-INF/admin/clientManager.jsp";
    private static final String CLIENTS = "clients";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        List<User> clients;
        AdminService service = new AdminService();
        try {
            clients = service.getAllClient();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        req.setAttribute(CLIENTS, clients);
        return WEB_INF_ADMIN_CLIENT_MANAGER_JSP;
    }
}
