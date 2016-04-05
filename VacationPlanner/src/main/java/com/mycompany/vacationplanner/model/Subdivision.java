/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author ��������
 */
@Entity
@Table(name = "subdivision")
@NamedQueries({
    @NamedQuery(name = "Subdivision.findAll",
            query = "SELECT s FROM Subdivision s")})
public class Subdivision implements Serializable {

    public static final String ID_PROPERTY = "id";
    public static final String NAME_PROPERTY = "name";

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private int version;

    @JsonProperty(NAME_PROPERTY)
    private String name;

    @JsonIgnore
    private Set<Employee> employees = new HashSet<Employee>();

    public Subdivision() {
    }

    public Subdivision(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "subdivision", cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subdivision)) {
            return false;
        }
        Subdivision other = (Subdivision) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subdivision - Id: " + id + ", Name: " + name;
    }

    public void addEmployee(Employee employee) {
        employee.setSubdivision(this);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

}
