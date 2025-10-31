package com.bartoszwalter.students.taxes;

public class EmployementContract extends Contract {

    EmployementContract(double grossIncome, char contractType){
        this.grossIncome = grossIncome;
        this.contractType = contractType;
        taxDeductibleExpenses = 111.25;
        taxFreeIncome = 46.33;
    }

    @Override
    protected void setBaseIncomeForTax() {
        baseIncomeForTax = grossIncome;
    }

}
