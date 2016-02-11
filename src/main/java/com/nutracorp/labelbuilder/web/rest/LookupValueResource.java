package com.nutracorp.labelbuilder.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nutracorp.labelbuilder.domain.LookupValue;
import com.nutracorp.labelbuilder.service.LookupValueService;
import com.nutracorp.labelbuilder.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LookupValue.
 */
@RestController
@RequestMapping("/api")
public class LookupValueResource {

    private final Logger log = LoggerFactory.getLogger(LookupValueResource.class);
        
    @Inject
    private LookupValueService lookupValueService;
    
    /**
     * POST  /lookupValues -> Create a new lookupValue.
     */
    @RequestMapping(value = "/lookupValues",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LookupValue> createLookupValue(@Valid @RequestBody LookupValue lookupValue) throws URISyntaxException {
        log.debug("REST request to save LookupValue : {}", lookupValue);
        if (lookupValue.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("lookupValue", "idexists", "A new lookupValue cannot already have an ID")).body(null);
        }
        LookupValue result = lookupValueService.save(lookupValue);
        return ResponseEntity.created(new URI("/api/lookupValues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("lookupValue", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lookupValues -> Updates an existing lookupValue.
     */
    @RequestMapping(value = "/lookupValues",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LookupValue> updateLookupValue(@Valid @RequestBody LookupValue lookupValue) throws URISyntaxException {
        log.debug("REST request to update LookupValue : {}", lookupValue);
        if (lookupValue.getId() == null) {
            return createLookupValue(lookupValue);
        }
        LookupValue result = lookupValueService.save(lookupValue);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("lookupValue", lookupValue.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lookupValues -> get all the lookupValues.
     */
    @RequestMapping(value = "/lookupValues",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<LookupValue> getAllLookupValues() {
        log.debug("REST request to get all LookupValues");
        return lookupValueService.findAll();
            }

    /**
     * GET  /lookupValues/:id -> get the "id" lookupValue.
     */
    @RequestMapping(value = "/lookupValues/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LookupValue> getLookupValue(@PathVariable Long id) {
        log.debug("REST request to get LookupValue : {}", id);
        LookupValue lookupValue = lookupValueService.findOne(id);
        return Optional.ofNullable(lookupValue)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /lookupValues/:id -> delete the "id" lookupValue.
     */
    @RequestMapping(value = "/lookupValues/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLookupValue(@PathVariable Long id) {
        log.debug("REST request to delete LookupValue : {}", id);
        lookupValueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("lookupValue", id.toString())).build();
    }
}
