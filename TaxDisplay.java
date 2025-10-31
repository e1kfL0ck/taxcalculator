package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;

public class TaxDisplay {

    DecimalFormat df;

    TaxDisplay() {
        df = new DecimalFormat("#");
    }

    protected void displayDetails(Contract contract) {

        if(contract instanceof CivilContract) {
            System.out.println("CIVIL");
        } else if (contract instanceof EmployementContract) {
            System.out.println("EMPLOYMENT");
        }

        System.out.println("Income " + contract.grossIncome);
        System.out.println("Social security tax "+ contract.socialPensionAmount);
        System.out.println("Health social security "+ contract.socialSecurityAmount);
        System.out.println("Sickness social security tax "+ contract.socialSicknessAmount);

        System.out.println("Income basis for health insurance social security: "+ contract.incomeMinusSocialSecurity);
        System.out.println("Health insurance social security tax: 9% = "+ contract.socialHealthAmount + " 7,75% = " +
                contract.deductibleSocialHealthAmount);

        System.out.println("Income basis for tax: "+ contract.incomeMinusSocialSecurity);

        System.out.println("Tax deductible expenses "+ contract.taxDeductibleExpenses);

        System.out.println("Taxable income " + contract.taxableIncome+ " rounded " + df.format(contract.taxableIncome));

        System.out.println("Advance tax 18 % = "+ contract.advanceTax);
        System.out.println("Tax free income = " + contract.taxFreeIncome);

        System.out.println("Reduced advance tax = " + contract.reducedAdvanceTax);
        System.out.println("Total taxes to be paid : "+contract.totalTaxes);
        System.out.println("Net income : "+ contract.netIncome);

    }
}
