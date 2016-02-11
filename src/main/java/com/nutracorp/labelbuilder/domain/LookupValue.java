package com.nutracorp.labelbuilder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A LookupValue.
 */
@Entity
@Table(name = "lookup_value")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LookupValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;
    
    @Size(max = 255)
    @Column(name = "display_value", length = 255)
    private String displayValue;
    
    @Column(name = "ordinal_value")
    private Integer ordinalValue;
    
    @ManyToMany(mappedBy = "lookupValuess")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LookupGroup> lookupGroups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayValue() {
        return displayValue;
    }
    
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public Integer getOrdinalValue() {
        return ordinalValue;
    }
    
    public void setOrdinalValue(Integer ordinalValue) {
        this.ordinalValue = ordinalValue;
    }

    public Set<LookupGroup> getLookupGroups() {
        return lookupGroups;
    }

    public void setLookupGroups(Set<LookupGroup> lookupGroups) {
        this.lookupGroups = lookupGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LookupValue lookupValue = (LookupValue) o;
        if(lookupValue.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, lookupValue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LookupValue{" +
            "id=" + id +
            ", code='" + code + "'" +
            ", displayValue='" + displayValue + "'" +
            ", ordinalValue='" + ordinalValue + "'" +
            '}';
    }
}
