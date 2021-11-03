package com.example.moudle.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@TableName("")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpEntityDto{
    private String status;
    private String taskName;
    private String message;
}
