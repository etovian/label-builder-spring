package com.nutracorp.labelbuilder.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Constant.
 */
@Entity
@Table(name = "constant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Constant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "constant_name")
    private String constant_name;
    
    @Column(name = "constant_value")
    private String constant_value;
    
    @Column(name = "description")
    private String description;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConstant_name() {
        return constant_name;
    }
    
    public void setConstant_name(String constant_name) {
        this.constant_name = constant_name;
    }

    public String getConstant_value() {
        return constant_value;
    }
    
    public void setConstant_value(String constant_value) {
        this.constant_value = constant_value;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Constant constant = (Constant) o;
        if(constant.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, constant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Constant{" +
            "id=" + id +
            ", constant_name='" + constant_name + "'" +
            ", constant_value='" + constant_value + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
