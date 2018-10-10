package com.epam.rental.command.comands;


import com.epam.rental.command.Command;
import com.epam.rental.entities.User;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.AbstractUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private AbstractUserService service;

    public LoginCommand(AbstractUserService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
            String result;
            String login = req.getParameter(LOGIN);
            String password = req.getParameter(PASSWORD);
            Optional<User> user;
            try {
                user = service.login(login,password);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            if (user.isPresent()){
                req.setAttribute(USER, user.get());
                result = SUCCESS;
            } else {
                result = ERROR;
            }
            return result;
        }
}
