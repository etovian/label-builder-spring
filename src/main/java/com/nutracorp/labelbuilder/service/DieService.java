package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.Die;

import java.util.List;

/**
 * Service Interface for managing Die.
 */
public interface DieService {

    /**
     * Save a die.
     * @return the persisted entity
     */
    public Die save(Die die);

    /**
     *  get all the dies.
     *  @return the list of entities
     */
    public List<Die> findAll();

    /**
     *  get the "id" die.
     *  @return the entity
     */
    public Die findOne(Long id);

    /**
     *  delete the "id" die.
     */
    public void delete(Long id);
}
