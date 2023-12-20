package com.s17g2.task.validation;

import com.s17g2.task.model.Developer;

import java.util.Map;

public class DeveloperValidation {
    public static boolean isDeveloperExist(Map<Integer, Developer> developers, int id) {
        return developers.containsKey(id);
    }
}
