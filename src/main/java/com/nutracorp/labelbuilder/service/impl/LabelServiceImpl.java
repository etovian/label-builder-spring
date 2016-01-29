package com.nutracorp.labelbuilder.service.impl;

import com.nutracorp.labelbuilder.service.LabelService;
import com.nutracorp.labelbuilder.domain.Label;
import com.nutracorp.labelbuilder.repository.LabelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Label.
 */
@Service
@Transactional
public class LabelServiceImpl implements LabelService{

    private final Logger log = LoggerFactory.getLogger(LabelServiceImpl.class);

    @Inject
    private LabelRepository labelRepository;

    /**
     * Save a label.
     * @return the persisted entity
     */
    public Label save(Label label) {
        log.debug("Request to save Label : {}", label);
        Label result = labelRepository.save(label);
        return result;
    }

    /**
     *  get all the labels.
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Label> findAll() {
        log.debug("Request to get all Labels");
        List<Label> result = labelRepository.findAll();
        return result;
    }

    /**
     *  get one label by id.
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Label findOne(Long id) {
        log.debug("Request to get Label : {}", id);
        Label label = labelRepository.findOne(id);
        return label;
    }

    /**
     *  delete the  label by id.
     */
    public Label delete(Long id) {
        log.debug("Request to delete Label : {}", id);
        Label label = labelRepository.findOne(id);
        labelRepository.delete(id);
        return label;
    }
}
