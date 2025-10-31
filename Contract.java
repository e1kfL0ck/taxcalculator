package com.bartoszwalter.students.taxes;

import static com.bartoszwalter.students.taxes.TaxCalculator.*;

public class Contract {

    protected double grossIncome;
    protected char contractType;
    public double taxDeductibleExpenses;
    public double taxFreeIncome; // tax-free income monthly 46,33, seems like it depend on the already paid tax...

    // social security taxes
    public double socialPensionAmount; // 9,76% of basis
    public double socialSecurityAmount; // 1,5% of basis
    public double socialSicknessAmount; // 2,45% of basis

    public double socialHealthAmount; // of basis up to 9%
    public double deductibleSocialHealthAmount; // of basis up to  7,75 %

    // New values
    protected double incomeMinusSocialSecurity;
    protected double taxableIncome;
    protected double advanceTax;
    protected double baseIncomeForTax;
    protected double reducedAdvanceTax;
    protected double totalTaxes;
    protected double netIncome;

    Contract(double grossIncome, char contractType){
        this.grossIncome = grossIncome;
        this.contractType = contractType;
    }

    Contract(){}

    protected void calculateSecurityHealthTaxes() {
        socialPensionAmount = (grossIncome * SOC_PENSION_RATE) / 100;
        socialSecurityAmount = (grossIncome * SOC_SECURITY_RATE) / 100;
        socialSicknessAmount = (grossIncome * SOC_SICKNESS_RATE) / 100;

        incomeMinusSocialSecurity = grossIncome - socialPensionAmount - socialSecurityAmount - socialSicknessAmount;
    }

    protected void setBaseIncomeForTax() {}

    protected void setTaxDeductibleExpenses() {}

    public void calculateInsuranceHealthTaxes() {
        socialHealthAmount = (incomeMinusSocialSecurity * SOC_HEALTH_RATE) / 100;
        deductibleSocialHealthAmount = (incomeMinusSocialSecurity * SOC_HEALTH_DEDUCTIBLE_RATE) / 100;
    }

    protected void setTaxDeductiblExpensese() {
        if (this.contractType == 'E') {
            taxDeductibleExpenses = 111.25;
            taxFreeIncome = 46.33;
        } else if (this.contractType == 'C') {
            taxDeductibleExpenses = (baseIncomeForTax * 20) / 100;
        }
    }

    protected void calculateTaxableIncome() {
        taxableIncome = baseIncomeForTax-taxDeductibleExpenses;
    }

    public void calculateAdvanceTax() {
        advanceTax = (taxableIncome * ADVANCE_TAX_RATE) / 100;
    }

    protected void calculateReducedAdvanceTax() {
        reducedAdvanceTax = advanceTax - taxFreeIncome - deductibleSocialHealthAmount;
    }

    protected void calculateTotalTaxes() {
        totalTaxes = socialPensionAmount + socialSecurityAmount + socialSicknessAmount + socialHealthAmount + reducedAdvanceTax;
    }

    protected void calculateNetIncome() {
        netIncome = grossIncome - totalTaxes;
    }
    
    protected void caculateTaxes() {
        calculateSecurityHealthTaxes();
        calculateInsuranceHealthTaxes();
        setBaseIncomeForTax();
        setTaxDeductibleExpenses();
        calculateTaxableIncome();
        calculateAdvanceTax();
        calculateReducedAdvanceTax();
        calculateTotalTaxes();
        calculateNetIncome();
    }

}