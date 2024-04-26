package com.bintics.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoModel implements Serializable {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column
    private String name;

    @Column
    private Boolean done;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
