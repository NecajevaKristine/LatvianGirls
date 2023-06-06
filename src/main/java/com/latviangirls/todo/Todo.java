package com.latviangirls.todo;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Timer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="todoList")

public class Todo {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String toBeDone;
    private LocalDateTime timeLimit;
    private Status status;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

}
