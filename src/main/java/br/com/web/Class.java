package br.com.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Class {

    private int id;
    private String name;
    private String shift;
    private List<Student> studentList;
}
