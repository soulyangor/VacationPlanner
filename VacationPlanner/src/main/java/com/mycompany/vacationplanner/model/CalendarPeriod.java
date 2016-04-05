/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vacationplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author ֲÿקוסכאג
 */
@Entity
@Table(name = "calendarPeriod")
@NamedQueries({
    @NamedQuery(name = "CalendarPeriod.findAll", query = "SELECT c FROM CalendarPeriod c"),
    @NamedQuery(name = "CalendarPeriod.findById",
            query = "SELECT DISTINCT c FROM CalendarPeriod c LEFT JOIN FETCH "
            + "c.celebrations e LEFT JOIN FETCH c.vacations v "
            + "WHERE c.id = :id")})
public class CalendarPeriod implements Serializable {

    public static final String ID_PROPERTY = "id";
    public static final String NAME_PROPERTY = "name";
    public static final String START_DAY_PROPERTY = "startday";
    public static final String END_DAY_PROPERTY = "endday";

    @JsonProperty(ID_PROPERTY)
    private Long id;

    @JsonIgnore
    private int version;

    @JsonProperty(NAME_PROPERTY)
    private String name;

    @JsonProperty(START_DAY_PROPERTY)
    private Date startDate;

    @JsonProperty(END_DAY_PROPERTY)
    private Date endDate;

    @JsonIgnore
    private Set<Celebration> celebrations;

    @JsonIgnore
    private Set<Vacation> vacations;

    public CalendarPeriod() {
    }

    public CalendarPeriod(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToMany(mappedBy = "calendarPeriod", cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Celebration> getCelebrations() {
        return celebrations;
    }

    public void setCelebrations(Set<Celebration> celebrations) {
        this.celebrations = celebrations;
    }

    @OneToMany(mappedBy = "calendarPeriod", cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(Set<Vacation> vacations) {
        this.vacations = vacations;
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
        if (!(object instanceof CalendarPeriod)) {
            return false;
        }
        CalendarPeriod other = (CalendarPeriod) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CalendarPeriod - Id: " + id + ", Start day: " + startDate
                + ", End day: " + endDate;
    }

    public void addCelebration(Celebration celebration) {
        celebration.setCalendarPeriod(this);
        celebrations.add(celebration);
    }

    public void removeCelebration(Celebration celebration) {
        celebrations.remove(celebration);
    }

    public void addVacation(Vacation vacation) {
        vacation.setCalendarPeriod(this);
        vacations.add(vacation);
    }

    public void removeVacation(Vacation vacation) {
        vacations.remove(vacation);
    }
}
