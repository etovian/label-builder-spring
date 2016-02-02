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
    public Label save(Label label);

    /**
     *  get all the labels.
     *  @return the list of entities
     */
    public List<Label> findAll();

    /**
     *  get the "id" label.
     *  @return the entity
     */
    public Label findOne(Long id);

    /**
     *  delete the "id" label.
     */
    public Label delete(Long id);

    /**
     * import json data
     */
    public void importJsonData() throws IOException;
}
