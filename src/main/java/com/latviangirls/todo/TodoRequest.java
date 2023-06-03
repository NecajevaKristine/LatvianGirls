package com.latviangirls.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoRequest {

    private String toBeDone;
    private LocalDateTime timeLimit;
    private Status status;

}
