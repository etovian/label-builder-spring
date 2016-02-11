package com.nutracorp.labelbuilder.web.rest;

import com.nutracorp.labelbuilder.Application;
import com.nutracorp.labelbuilder.domain.Constant;
import com.nutracorp.labelbuilder.repository.ConstantRepository;
import com.nutracorp.labelbuilder.service.ConstantService;

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
 * Test class for the ConstantResource REST controller.
 *
 * @see ConstantResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ConstantResourceIntTest {

    private static final String DEFAULT_CONSTANT_NAME = "AAAAA";
    private static final String UPDATED_CONSTANT_NAME = "BBBBB";
    private static final String DEFAULT_CONSTANT_VALUE = "AAAAA";
    private static final String UPDATED_CONSTANT_VALUE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private ConstantRepository constantRepository;

    @Inject
    private ConstantService constantService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restConstantMockMvc;

    private Constant constant;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConstantResource constantResource = new ConstantResource();
        ReflectionTestUtils.setField(constantResource, "constantService", constantService);
        this.restConstantMockMvc = MockMvcBuilders.standaloneSetup(constantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        constant = new Constant();
        constant.setConstantName(DEFAULT_CONSTANT_NAME);
        constant.setConstantValue(DEFAULT_CONSTANT_VALUE);
        constant.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createConstant() throws Exception {
        int databaseSizeBeforeCreate = constantRepository.findAll().size();

        // Create the Constant

        restConstantMockMvc.perform(post("/api/constants")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(constant)))
                .andExpect(status().isCreated());

        // Validate the Constant in the database
        List<Constant> constants = constantRepository.findAll();
        assertThat(constants).hasSize(databaseSizeBeforeCreate + 1);
        Constant testConstant = constants.get(constants.size() - 1);
        assertThat(testConstant.getConstantName()).isEqualTo(DEFAULT_CONSTANT_NAME);
        assertThat(testConstant.getConstantValue()).isEqualTo(DEFAULT_CONSTANT_VALUE);
        assertThat(testConstant.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllConstants() throws Exception {
        // Initialize the database
        constantRepository.saveAndFlush(constant);

        // Get all the constants
        restConstantMockMvc.perform(get("/api/constants?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(constant.getId().intValue())))
                .andExpect(jsonPath("$.[*].constantName").value(hasItem(DEFAULT_CONSTANT_NAME.toString())))
                .andExpect(jsonPath("$.[*].constantValue").value(hasItem(DEFAULT_CONSTANT_VALUE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getConstant() throws Exception {
        // Initialize the database
        constantRepository.saveAndFlush(constant);

        // Get the constant
        restConstantMockMvc.perform(get("/api/constants/{id}", constant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(constant.getId().intValue()))
            .andExpect(jsonPath("$.constantName").value(DEFAULT_CONSTANT_NAME.toString()))
            .andExpect(jsonPath("$.constantValue").value(DEFAULT_CONSTANT_VALUE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConstant() throws Exception {
        // Get the constant
        restConstantMockMvc.perform(get("/api/constants/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConstant() throws Exception {
        // Initialize the database
        constantRepository.saveAndFlush(constant);

		int databaseSizeBeforeUpdate = constantRepository.findAll().size();

        // Update the constant
        constant.setConstantName(UPDATED_CONSTANT_NAME);
        constant.setConstantValue(UPDATED_CONSTANT_VALUE);
        constant.setDescription(UPDATED_DESCRIPTION);

        restConstantMockMvc.perform(put("/api/constants")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(constant)))
                .andExpect(status().isOk());

        // Validate the Constant in the database
        List<Constant> constants = constantRepository.findAll();
        assertThat(constants).hasSize(databaseSizeBeforeUpdate);
        Constant testConstant = constants.get(constants.size() - 1);
        assertThat(testConstant.getConstantName()).isEqualTo(UPDATED_CONSTANT_NAME);
        assertThat(testConstant.getConstantValue()).isEqualTo(UPDATED_CONSTANT_VALUE);
        assertThat(testConstant.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteConstant() throws Exception {
        // Initialize the database
        constantRepository.saveAndFlush(constant);

		int databaseSizeBeforeDelete = constantRepository.findAll().size();

        // Get the constant
        restConstantMockMvc.perform(delete("/api/constants/{id}", constant.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Constant> constants = constantRepository.findAll();
        assertThat(constants).hasSize(databaseSizeBeforeDelete - 1);
    }
}
