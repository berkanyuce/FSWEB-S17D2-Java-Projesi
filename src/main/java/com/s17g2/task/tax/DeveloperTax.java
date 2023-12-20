package com.s17g2.task.tax;

public class DeveloperTax implements Taxable{
    @Override
    public double getSimpleTaxRate() {
        return 0.2;
    }

    @Override
    public double getMiddleTaxRate() {
        return 0.5;
    }

    @Override
    public double getUpperTaxRate() {
        return 0.6;
    }
}
