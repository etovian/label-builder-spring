package com.nutracorp.labelbuilder.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutracorp.labelbuilder.service.LabelService;
import com.nutracorp.labelbuilder.domain.Label;
import com.nutracorp.labelbuilder.repository.LabelRepository;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    /**
     * import json data
     */
    @Transactional
    public void importJsonData() throws IOException {
        log.debug("importing json label data");
        String path = "/Users/michaelgreen/dev/clients/nutraceutical/label-builder/src/dev-data/existing.labels.summary.json";
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);
        Map<String, Object> map = mapper.readValue(file, Map.class);
        List<Label> labels = buildEntitiesFrom(map);
        Iterator<Label> iterator = labels.iterator();
        int i = 0;
        while(iterator.hasNext() && (i < 600)) {
            i++;
            Label label = iterator.next();
            try {
                labelRepository.saveAndFlush(label);
            } catch (Exception ex) {
                log.debug("Could not save label " + label.getProductId().toString(), ex);
            }
        }
    }

    public Label findByProductId(String productId) {
        return labelRepository.findByProductId(productId);
    }

    public Label createByProductId(String productId) {

        Label label = new Label();
        label.setProductId(productId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        label.setProductName("New Label Created By " + userName);
        return labelRepository.save(label);
    }

    public List<Label> buildEntitiesFrom(Map<String, Object> map) {

        List<Label> labels = new ArrayList<>();
        Map<String, Object> existingLabels = (Map) map.get("existing-labels");
        List<Map<String, Object>> labelMaps = (List) existingLabels.get("label");
        Iterator<Map<String, Object>> iterator = labelMaps.iterator();
        while(iterator.hasNext()) {
            Map<String, Object> labelMap = iterator.next();
            Label entity = buildEntityFrom(labelMap);
            labels.add(entity);
        }

        return labels;
    }

    public Label buildEntityFrom(Map<String, Object> map) {

        Label label = new Label();
        label.setProductId(map.get("itemCode").toString());
        label.setVersionMajor(map.get("versionMajor").toString());
        label.setVersionMinor(map.get("versionMinor").toString());
        label.setProductName(map.get("productName").toString());

        return label;
    }
}
