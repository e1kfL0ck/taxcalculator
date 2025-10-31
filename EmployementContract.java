package com.bartoszwalter.students.taxes;

public class EmployementContract extends Contract {

    EmployementContract(double grossIncome){
        //this.grossIncome = grossIncome;
        super(grossIncome);
        this.contractType = 'E';
        taxFreeIncome = 46.33;
    }

    @Override
    protected void setBaseIncomeForTax() {
        baseIncomeForTax = grossIncome;
    }

    @Override
    protected void setTaxDeductibleExpenses() {
        taxDeductibleExpenses = 111.25;
    }
}
