package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.Constant;

import java.util.List;

/**
 * Service Interface for managing Constant.
 */
public interface ConstantService {

    /**
     * Save a constant.
     * @return the persisted entity
     */
    public Constant save(Constant constant);

    /**
     *  get all the constants.
     *  @return the list of entities
     */
    public List<Constant> findAll();

    /**
     *  get the "id" constant.
     *  @return the entity
     */
    public Constant findOne(Long id);

    /**
     *  delete the "id" constant.
     */
    public void delete(Long id);
}
