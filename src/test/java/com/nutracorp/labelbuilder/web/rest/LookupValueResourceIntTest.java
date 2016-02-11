package com.nutracorp.labelbuilder.web.rest;

import com.nutracorp.labelbuilder.Application;
import com.nutracorp.labelbuilder.domain.LookupValue;
import com.nutracorp.labelbuilder.repository.LookupValueRepository;
import com.nutracorp.labelbuilder.service.LookupValueService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the LookupValueResource REST controller.
 *
 * @see LookupValueResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class LookupValueResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAA";
    private static final String UPDATED_CODE = "BBBBB";
    private static final String DEFAULT_DISPLAY_VALUE = "AAAAA";
    private static final String UPDATED_DISPLAY_VALUE = "BBBBB";

    private static final Integer DEFAULT_ORDINAL_VALUE = 1;
    private static final Integer UPDATED_ORDINAL_VALUE = 2;

    @Inject
    private LookupValueRepository lookupValueRepository;

    @Inject
    private LookupValueService lookupValueService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restLookupValueMockMvc;

    private LookupValue lookupValue;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LookupValueResource lookupValueResource = new LookupValueResource();
        ReflectionTestUtils.setField(lookupValueResource, "lookupValueService", lookupValueService);
        this.restLookupValueMockMvc = MockMvcBuilders.standaloneSetup(lookupValueResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        lookupValue = new LookupValue();
        lookupValue.setCode(DEFAULT_CODE);
        lookupValue.setDisplayValue(DEFAULT_DISPLAY_VALUE);
        lookupValue.setOrdinalValue(DEFAULT_ORDINAL_VALUE);
    }

    @Test
    @Transactional
    public void createLookupValue() throws Exception {
        int databaseSizeBeforeCreate = lookupValueRepository.findAll().size();

        // Create the LookupValue

        restLookupValueMockMvc.perform(post("/api/lookupValues")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(lookupValue)))
                .andExpect(status().isCreated());

        // Validate the LookupValue in the database
        List<LookupValue> lookupValues = lookupValueRepository.findAll();
        assertThat(lookupValues).hasSize(databaseSizeBeforeCreate + 1);
        LookupValue testLookupValue = lookupValues.get(lookupValues.size() - 1);
        assertThat(testLookupValue.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testLookupValue.getDisplayValue()).isEqualTo(DEFAULT_DISPLAY_VALUE);
        assertThat(testLookupValue.getOrdinalValue()).isEqualTo(DEFAULT_ORDINAL_VALUE);
    }

    @Test
    @Transactional
    public void getAllLookupValues() throws Exception {
        // Initialize the database
        lookupValueRepository.saveAndFlush(lookupValue);

        // Get all the lookupValues
        restLookupValueMockMvc.perform(get("/api/lookupValues?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(lookupValue.getId().intValue())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].displayValue").value(hasItem(DEFAULT_DISPLAY_VALUE.toString())))
                .andExpect(jsonPath("$.[*].ordinalValue").value(hasItem(DEFAULT_ORDINAL_VALUE)));
    }

    @Test
    @Transactional
    public void getLookupValue() throws Exception {
        // Initialize the database
        lookupValueRepository.saveAndFlush(lookupValue);

        // Get the lookupValue
        restLookupValueMockMvc.perform(get("/api/lookupValues/{id}", lookupValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(lookupValue.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.displayValue").value(DEFAULT_DISPLAY_VALUE.toString()))
            .andExpect(jsonPath("$.ordinalValue").value(DEFAULT_ORDINAL_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingLookupValue() throws Exception {
        // Get the lookupValue
        restLookupValueMockMvc.perform(get("/api/lookupValues/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLookupValue() throws Exception {
        // Initialize the database
        lookupValueRepository.saveAndFlush(lookupValue);

		int databaseSizeBeforeUpdate = lookupValueRepository.findAll().size();

        // Update the lookupValue
        lookupValue.setCode(UPDATED_CODE);
        lookupValue.setDisplayValue(UPDATED_DISPLAY_VALUE);
        lookupValue.setOrdinalValue(UPDATED_ORDINAL_VALUE);

        restLookupValueMockMvc.perform(put("/api/lookupValues")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(lookupValue)))
                .andExpect(status().isOk());

        // Validate the LookupValue in the database
        List<LookupValue> lookupValues = lookupValueRepository.findAll();
        assertThat(lookupValues).hasSize(databaseSizeBeforeUpdate);
        LookupValue testLookupValue = lookupValues.get(lookupValues.size() - 1);
        assertThat(testLookupValue.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testLookupValue.getDisplayValue()).isEqualTo(UPDATED_DISPLAY_VALUE);
        assertThat(testLookupValue.getOrdinalValue()).isEqualTo(UPDATED_ORDINAL_VALUE);
    }

    @Test
    @Transactional
    public void deleteLookupValue() throws Exception {
        // Initialize the database
        lookupValueRepository.saveAndFlush(lookupValue);

		int databaseSizeBeforeDelete = lookupValueRepository.findAll().size();

        // Get the lookupValue
        restLookupValueMockMvc.perform(delete("/api/lookupValues/{id}", lookupValue.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<LookupValue> lookupValues = lookupValueRepository.findAll();
        assertThat(lookupValues).hasSize(databaseSizeBeforeDelete - 1);
    }
}
