package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaxesCalculator {

    // Social security taxes, based on full income
    private final double SOC_SECURITY = 9.76;
    private final double SOC_HEALTH_SECURITY = 1.5;
    private final double SOC_SICKNESS_SECURITY = 2.25;
    private final double SOCIAL_HEALTH

    private double income;
    private char contractType;

    private double taxDeductibleExpenses;
    private double taxFreeIncome;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    TaxesCalculator(double income, char contractType){
        this.income = income;
        this.contractType = contractType;
        taxDeductibleExpenses = 111.25;
        taxFreeIncome = 46.33;
    }

    private void showSecurityHealthDetails() {
        System.out.println("Income basis for health social security : " + income);
        System.out.println("Social security tax : "+SOC_SECURITY);
        System.out.println("Health social security tax : " + SOC_HEALTH_SECURITY);
        System.out.println("Sickness social security tax : " + SOC_SICKNESS_SECURITY);
    }


}
