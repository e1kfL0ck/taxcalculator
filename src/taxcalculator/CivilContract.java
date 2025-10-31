package taxcalculator;

public class CivilContract extends Contract {

    CivilContract(double grossIncome){
        super(grossIncome);
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
