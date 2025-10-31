package taxcalculator;

import static taxcalculator.TaxCalculator.*;

public abstract class Contract {

    protected double grossIncome;
    protected double taxDeductibleExpenses;
    protected double taxFreeIncome; // tax-free income monthly 46,33, seems like it depend on the already paid tax...

    // social security taxes
    protected double socialPensionAmount; // 9,76% of basis
    protected double socialSecurityAmount; // 1,5% of basis
    protected double socialSicknessAmount; // 2,45% of basis

    protected double socialHealthAmount; // of basis up to 9%
    protected double deductibleSocialHealthAmount; // of basis up to  7,75 %

    // New values
    protected double incomeMinusSocialSecurity;
    protected double taxableIncome;
    protected double advanceTax;
    protected double baseIncomeForTax;
    protected double reducedAdvanceTax;
    protected double totalTaxes;
    protected double netIncome;

    Contract(double grossIncome){
        this.grossIncome = grossIncome;
    }

    protected void calculateSecurityHealthTaxes() {
        socialPensionAmount = (grossIncome * SOC_PENSION_RATE) / 100;
        socialSecurityAmount = (grossIncome * SOC_SECURITY_RATE) / 100;
        socialSicknessAmount = (grossIncome * SOC_SICKNESS_RATE) / 100;

        incomeMinusSocialSecurity = grossIncome - socialPensionAmount - socialSecurityAmount - socialSicknessAmount;
    }

    protected abstract void setBaseIncomeForTax();

    protected abstract void setTaxDeductibleExpenses();

    protected void calculateInsuranceHealthTaxes() {
        socialHealthAmount = (incomeMinusSocialSecurity * SOC_HEALTH_RATE) / 100;
        deductibleSocialHealthAmount = (incomeMinusSocialSecurity * SOC_HEALTH_DEDUCTIBLE_RATE) / 100;
    }

    protected void calculateTaxableIncome() {
        taxableIncome = baseIncomeForTax-taxDeductibleExpenses;
    }

    protected void calculateAdvanceTax() {
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