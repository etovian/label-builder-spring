package com.nutracorp.labelbuilder.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nutracorp.labelbuilder.domain.Label;
import com.nutracorp.labelbuilder.security.AuthoritiesConstants;
import com.nutracorp.labelbuilder.service.LabelService;
import com.nutracorp.labelbuilder.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Label.
 */
@RestController
@RequestMapping("/api")
public class LabelResource {

    private final Logger log = LoggerFactory.getLogger(LabelResource.class);

    @Inject
    private LabelService labelService;

    /**
     * POST  /labels -> Create a new label.
     */
    @RequestMapping(value = "/labels",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Label> createLabel(@RequestBody Label label) throws URISyntaxException {
        log.debug("REST request to save Label : {}", label);
        if (label.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("label", "idexists", "A new label cannot already have an ID")).body(null);
        }
        Label result = labelService.save(label);
        return ResponseEntity.created(new URI("/api/labels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("label", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /labels -> Updates an existing label.
     */
    @RequestMapping(value = "/labels",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Label> updateLabel(@RequestBody Label label) throws URISyntaxException {
        log.debug("REST request to update Label : {}", label);
        if (label.getId() == null) {
            return createLabel(label);
        }
        Label result = labelService.save(label);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("label", label.getId().toString()))
            .body(result);
    }

    /**
     * GET  /labels -> get all the labels.
     */
    @RequestMapping(value = "/labels",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Label> getAllLabels(@RequestParam(value = "approved", required = false) Boolean approved, @RequestParam(value = "type", required = false) String type) {
        log.debug("REST request to get all Labels");
        return labelService.findAll();
    }

    /**
     * GET  /labels/:id -> get the "id" label.
     */
    @RequestMapping(value = "/labels/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Label> getLabel(@PathVariable Long id) {
        log.debug("REST request to get Label : {}", id);
        Label label = labelService.findOne(id);
        return Optional.ofNullable(label)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /labels/:id -> delete the "id" label.
     */
    @RequestMapping(value = "/labels/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        log.debug("REST request to delete Label : {}", id);
        Label label = labelService.delete(id);
        String param = label.getProductName() + ", Product ID " + label.getProductId().toString();
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("label", param)).build();
    }

    /**
     * GET /labels/json
     */
    @RequestMapping(value = "/labels/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured((AuthoritiesConstants.ADMIN))
    public ResponseEntity<Void> importJson() {
        log.debug("importing json label data");
        try {
            labelService.importJsonData();
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().headers(HeaderUtil.createFailureAlert("label", "errorKey", "Could not import data")).build();
        }
    }

    /**
     * GET /labels/productId/{productId}
     */
    @RequestMapping(value = "/labels/productId/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<Label> getLabelByProductId(@PathVariable String productId) {
        log.debug("REST request to get label by productId : {}", productId);
        Label label = labelService.findByProductId(productId);
        if(label == null) {
            return ResponseEntity.ok().body(new Label());
        } else {
            return Optional.of(label)
                .map(result -> new ResponseEntity<>(
                    result,
                    HttpStatus.OK))
                .orElse(ResponseEntity.ok(new Label()));
        }
    }

    /**
     * POST /labels/productId/{productId}
     */
    @RequestMapping(value = "/labels/productId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<Label> createByProductId(@RequestBody String productId) throws URISyntaxException {
        Label result = labelService.createByProductId(productId);
        return ResponseEntity.created(new URI("/api/labels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("label", result.getId().toString()))
            .body(result);
    }
}
