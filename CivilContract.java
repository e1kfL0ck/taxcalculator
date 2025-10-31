package com.bartoszwalter.students.taxes;

public class CivilContract extends Contract {

    CivilContract(double grossIncome){
        this.grossIncome = grossIncome;
        this.contractType = 'C';
        taxFreeIncome = 0;
    }

    @Override
    protected void setBaseIncomeForTax() {
        baseIncomeForTax = incomeMinusSocialSecurity;
    }

    @Override
    protected void setTaxDeductibleExpenses() {
        taxDeductibleExpenses = (baseIncomeForTax * 20) / 100;
    }
}
