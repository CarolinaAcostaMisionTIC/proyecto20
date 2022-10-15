package com.proyecto20.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Status {
    private int completed;
    private int cancelled;
}
