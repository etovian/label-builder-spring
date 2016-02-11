package com.nutracorp.labelbuilder.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nutracorp.labelbuilder.domain.Constant;
import com.nutracorp.labelbuilder.service.ConstantService;
import com.nutracorp.labelbuilder.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Constant.
 */
@RestController
@RequestMapping("/api")
public class ConstantResource {

    private final Logger log = LoggerFactory.getLogger(ConstantResource.class);

    @Inject
    private ConstantService constantService;

    /**
     * POST  /constants -> Create a new constant.
     */
    @RequestMapping(value = "/constants",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Constant> createConstant(@RequestBody Constant constant) throws URISyntaxException {
        log.debug("REST request to save Constant : {}", constant);
        if (constant.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("constant", "idexists", "A new constant cannot already have an ID")).body(null);
        }
        Constant result = constantService.save(constant);
        return ResponseEntity.created(new URI("/api/constants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("constant", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /constants -> Updates an existing constant.
     */
    @RequestMapping(value = "/constants",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Constant> updateConstant(@RequestBody Constant constant) throws URISyntaxException {
        log.debug("REST request to update Constant : {}", constant);
        if (constant.getId() == null) {
            return createConstant(constant);
        }
        Constant result = constantService.save(constant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("constant", constant.getId().toString()))
            .body(result);
    }

    /**
     * GET  /constants -> get all the constants.
     */
    @RequestMapping(value = "/constants",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Constant> getAllConstants() {
        log.debug("REST request to get all Constants");
        return constantService.findAll();
            }

    /**
     * GET  /constants/:id -> get the "id" constant.
     */
    @RequestMapping(value = "/constants/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Constant> getConstant(@PathVariable Long id) {
        log.debug("REST request to get Constant : {}", id);
        Constant constant = constantService.findOne(id);
        return Optional.ofNullable(constant)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /constants/:id -> delete the "id" constant.
     */
    @RequestMapping(value = "/constants/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteConstant(@PathVariable Long id) {
        log.debug("REST request to delete Constant : {}", id);
        constantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("constant", id.toString())).build();
    }

    /**
     * get a map of constant values, which will be passed to client as a single JSON Object
     */
    @RequestMapping(value = "/constants/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Map<String, Object> getConstantMap() {
        return constantService.getConstantMap();
    }
}
