package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.BikeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class SaveBikeCommand implements Command {

    private BikeService service;

    public SaveBikeCommand(BikeService service){
        this.service = service;
    }


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", req.getParameter("id"));
        map.put("name", req.getParameter("name"));
        map.put("cost", req.getParameter("cost"));

        map.put("typeId", req.getParameter("bikeTypeName"));
        map.put("rentalPlaceId", req.getParameter("rentalPlaceId"));

        try {
            service.save(map);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        GetBikeCommand command = new GetBikeCommand(service);
        return command.execute(req, resp);
    }
}
