package com.s17g2.task.model;

public class SeniorDeveloper extends Developer{
    public SeniorDeveloper(int id, String name, double salary, Experience experience) {
        super(id, name, salary, Experience.SENIOR);
    }
}
