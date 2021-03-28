package com.activity.tracker.entities;

import com.activity.tracker.model.ActivityModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Activity has all required fields of Activity Entity
 */
@Data
@Entity
@Table(name = "ACTIVITY")
@EqualsAndHashCode
@ToString(includeFieldNames = true)
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "update ACTIVITY set is_deleted=true where id = ?")
public class Activity extends ActivityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id",nullable = false)
    @JsonProperty("id")
    private long id;

    @JsonProperty("activty_def")
    @Column( name = "description",nullable = true)
    private String activityDef;

    @JsonProperty("name")
    @Column( name = "name",nullable = false)
    private String name;

    @JsonProperty("type")
    @Column( name = "type",nullable = false)
    private String activityType;

    @JsonProperty("start_time")
    @Column( name = "start_time",nullable = false)
    private Date startTime;

    @Column( name = "created_date",nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Date created;

    @JsonIgnore
    @Column(name = "is_deleted",nullable = true)
    private Boolean is_deleted = false;

    @OneToMany(mappedBy = "activity",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Record> recordList = new ArrayList<>();

}
