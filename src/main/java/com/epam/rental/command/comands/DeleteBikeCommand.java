package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.BikeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteBikeCommand implements Command {


    private static final String ID = "id";
    private BikeService service;

    public DeleteBikeCommand(BikeService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            service.deleteBike(Long.valueOf(req.getParameter(ID)));
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        GetBikeCommand command = new GetBikeCommand(service);
        return command.execute(req, resp);
    }
}
