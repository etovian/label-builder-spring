package com.nutracorp.labelbuilder.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A LookupGroup.
 */
@Entity
@Table(name = "lookup_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LookupGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_name")
    private String groupName;
    
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "lookup_group_lookup_values",
               joinColumns = @JoinColumn(name="lookup_groups_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="lookup_valuess_id", referencedColumnName="ID"))
    private Set<LookupValue> lookupValuess = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<LookupValue> getLookupValuess() {
        return lookupValuess;
    }

    public void setLookupValuess(Set<LookupValue> lookupValues) {
        this.lookupValuess = lookupValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LookupGroup lookupGroup = (LookupGroup) o;
        if(lookupGroup.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, lookupGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LookupGroup{" +
            "id=" + id +
            ", groupName='" + groupName + "'" +
            '}';
    }
}
