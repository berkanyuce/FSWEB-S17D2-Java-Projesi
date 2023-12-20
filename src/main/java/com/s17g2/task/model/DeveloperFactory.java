package com.s17g2.task.model;

import com.s17g2.task.tax.Taxable;

public class DeveloperFactory {
    public static Developer createDeveloper(Developer developer, Taxable taxable) {
        Developer createdDeveloper = null;
        if(developer.getExperience().name().equalsIgnoreCase("junior")){
            createdDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getSimpleTaxRate()));
        }
        else if(developer.getExperience().name().equalsIgnoreCase("mid")){
            createdDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getMiddleTaxRate()));
        }
        else if(developer.getExperience().name().equalsIgnoreCase("senior")){
            createdDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getUpperTaxRate()));
        }

        return createdDeveloper;
    }
}
