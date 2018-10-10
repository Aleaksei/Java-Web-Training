package com.epam.rental.DAO.api;


import com.epam.rental.entities.Identifiable;
import com.epam.rental.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface DAO <T extends Identifiable> {

    Optional<T> getById(Long id);

    List<T> getAll();

    void save (T item) throws DaoException;

    void remove (Long id);
}
