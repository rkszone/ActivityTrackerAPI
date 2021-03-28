package com.activity.tracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Record has all the fields of Record Entity
 */
@Data
@Entity
@Table(name = "RECORD")
@EqualsAndHashCode
@ToString(includeFieldNames = true, exclude = {"activity"})
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "update RECORD set is_deleted=true where id = ?")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id",nullable = false)
    @JsonProperty("id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @JsonProperty("record_def")
    @Column( name = "record_def",nullable = true)
    private String recordDef;

    @JsonProperty("time")
    @Column( name = "time",nullable = false)
    private Date time;

    @JsonProperty("distance")
    @Column( name = "distance",nullable = false)
    private int distance;

    @JsonProperty("power")
    @Column( name = "power",nullable = false)
    private int power;

    @JsonProperty("cadence")
    @Column( name = "cadence",nullable = false)
    private int cadence;

    @Column( name = "created_date",nullable = false)
    @CreationTimestamp
    @JsonIgnore
    private Date created;

    @JsonIgnore
    @Column(name = "is_deleted",nullable = true)
    private Boolean is_deleted = false;



}
