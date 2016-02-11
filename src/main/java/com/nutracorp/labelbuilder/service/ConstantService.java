package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.Constant;

import java.util.List;
import java.util.Map;

/**
 * Service Interface for managing Constant.
 */
public interface ConstantService {

    /**
     * Save a constant.
     * @return the persisted entity
     */
    Constant save(Constant constant);

    /**
     *  get all the constants.
     *  @return the list of entities
     */
    List<Constant> findAll();

    /**
     *  get the "id" constant.
     *  @return the entity
     */
    Constant findOne(Long id);

    /**
     *  delete the "id" constant.
     */
    void delete(Long id);

    /**
     * get a map of constant values
     */
    Map<String, Object> getConstantMap();
}
