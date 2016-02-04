package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.Label;

import java.io.IOException;
import java.util.List;

/**
 * Service Interface for managing Label.
 */
public interface LabelService {

    /**
     * Save a label.
     * @return the persisted entity
     */
    Label save(Label label);

    /**
     *  get all the labels.
     *  @return the list of entities
     */
    List<Label> findAll();

    /**
     *  get the "id" label.
     *  @return the entity
     */
    Label findOne(Long id);

    /**
     *  delete the "id" label.
     */
    Label delete(Long id);

    /**
     * import json data
     */
    void importJsonData() throws IOException;

    /**
     *  get a label by product id
     */
    Label findByProductId(String productId);

    /**
     * create a label from a product id
     */
    Label createByProductId(String productId);
}
