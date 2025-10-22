package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaxesCalculator {

    // Social security taxes, based on full income
    private final double SOC_SECURITY = 9.76;
    private final double SOC_HEALTH_SECURITY = 1.5;
    private final double SOCIAL_HEALTH_1 = 9.00;
    private final double SOCIAL_HEALTH_2 = 7.75;

    private final double SOC_SICKNESS_SECURITY = 2.45;


    private final double ADVANCE_TAX = 18.00;

    private double income;
    private char contractType;

    private double taxDeductibleExpenses;
    private double taxFreeIncome;
    private double securityHealthTaxes;
    private double taxableIncome;
    private double advanceTaxAmount;
    private double reduceTaxAmount;
    private double netIncome;
    private double netIncomeOfTaxes;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    TaxesCalculator(double income, char contractType){
        this.income = income;
        this.contractType = contractType;
        switch (contractType) {
            case 'E':
                taxDeductibleExpenses = 111.25;
                taxFreeIncome = 46.33;
                break;
            case 'C':
                taxDeductibleExpenses = income*0.2;
                taxFreeIncome = 0;
                break;
            default:
                System.out.println("Unknown type of contract!");
                System.exit(0);
        }
    }

    private void showSecurityHealthDetails() {
        System.out.println("Income basis for health social security : " + income);
        System.out.println("Social security tax : " + SOC_SECURITY + "%");
        System.out.println("Health social security tax : " + SOC_HEALTH_SECURITY + "%");
        System.out.println("Sickness social security tax : " + SOC_SICKNESS_SECURITY + "%");
        System.out.println("Social health tax : " + SOCIAL_HEALTH_1 + "%");
    }

    private void calculateSecurityHealthDetails() {
        securityHealthTaxes = income * (SOCIAL_HEALTH_1+SOC_SECURITY+SOC_SICKNESS_SECURITY+SOC_HEALTH_SECURITY) / 100;
        System.out.println("Security health total taxes : " + securityHealthTaxes);
    }

    private void calculateTaxableIncome() {
        System.out.println("Tax deductible expenses : " + taxDeductibleExpenses);
        taxableIncome = income-taxDeductibleExpenses;
        System.out.println("Taxable Income : " + taxableIncome);
    }

    private void calculateAdvanceTax() {
        System.out.println("Advance tax : " + ADVANCE_TAX + "%");
        advanceTaxAmount = taxableIncome*ADVANCE_TAX/100;
        System.out.println("Advance tax amount : " + advanceTaxAmount);
    }

    private void calculateReducedTax() {
        reduceTaxAmount = advanceTaxAmount-taxFreeIncome;
        System.out.println("Reduced tax : " + reduceTaxAmount);
    }
    
    private void calculateNetIncome() {
        netIncome = income - securityHealthTaxes;
        System.out.println("Net income : " + netIncome);
    }

    private void calculateNetIncomeOfTaxes() {
        netIncomeOfTaxes = netIncome - reduceTaxAmount ;
        System.out.println("Net income of taxes : " + netIncomeOfTaxes);
    }

    private void calculateAllTaxes() {
        this.showSecurityHealthDetails();
        calculateSecurityHealthDetails();
        calculateTaxableIncome();
        calculateAdvanceTax();
        calculateReducedTax();
        calculateNetIncome();
        calculateNetIncomeOfTaxes();
    }

    public static void main(String[] args) {
        System.out.println("Taxes Calculator");

        double income;
        char contractType;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter income: ");
            income = Double.parseDouble(br.readLine());

            System.out.print("Contract Type: (E)mployment, (C)ivil: ");
            contractType = br.readLine().charAt(0);

        } catch (Exception ex) {
            System.out.println("Incorrect");
            System.err.println(ex);
            return;
        }

        TaxesCalculator p1 = new TaxesCalculator(income, contractType);
        p1.calculateAllTaxes();
    }

}
