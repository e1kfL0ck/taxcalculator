package com.bartoszwalter.students.taxes;

public class CivilContract extends Contract {

    CivilContract(double grossIncome, char contractType){
        this.grossIncome = grossIncome;
        this.contractType = contractType;
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
