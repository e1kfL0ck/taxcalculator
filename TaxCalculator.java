package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	//Basis values
	public static final double SOC_PENSION_RATE = 9.76;
	public static final double SOC_SECURITY_RATE = 1.5;
	public static final double SOC_SICKNESS_RATE = 2.45;
	public static final double SOC_HEALTH_RATE = 9.0;
	public static final double SOC_HEALTH_DEDUCTIBLE_RATE = 7.75;
	public static final double ADVANCE_TAX_RATE = 18.0;


	// Functions

	TaxCalculator(Contract contract) {
//		this.grossIncome = contract.grossIncome();
//		this.contractType = contract.contractType();
//		if (contract.contractType() == 'E') {
//			taxFreeIncome = 46.33;
//		} else if (contract.contractType() == 'C') {
//			taxFreeIncome = 0;
//		} else {
//			System.out.println("Unknown type of contract!");
//			System.exit(0);
//		}
	}

	public static void main(String[] args) {
		double income;
		char contractType;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Enter income: ");
			income = Double.parseDouble(br.readLine());

			System.out.print("Contract Type: (E)mployment, (C)ivil: ");
			contractType = br.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println("Incorrect");
			System.err.println(ex);
			return;
		}

		Contract person1;
		person1 = new Contract(income, contractType);

		DecimalFormat df00 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#");

		if (person1.contractType == 'E') {
			System.out.println("EMPLOYMENT");
			System.out.println("Income " + person1.grossIncome);

			person1.calculateSecurityHealthTaxes();

			System.out.println("Social security tax "+ person1.socialPensionAmount);
			System.out.println("Health social security "+ person1.socialSecurityAmount);
			System.out.println("Sickness social security tax "+ person1.socialSicknessAmount);

			System.out.println("Income basis for health insurance social security: "+ person1.incomeMinusSocialSecurity);

			// Set Social Health Taxes 1 and 2
			person1.calculateInsuranceHealthTaxes();
			System.out.println("Health insurance social security tax: 9% = "+ person1.socialHealthAmount + " 7,75% = " +
					person1.deductibleSocialHealthAmount);

			//Set base income for tax (ie income on which is applied taxes)
			person1.setBaseIncomeForTax();
			System.out.println("Income basis for tax: "+ person1.incomeMinusSocialSecurity);

			person1.setTaxDeductiblExpensese();
			System.out.println("Tax deductible expenses "+ person1.taxDeductibleExpenses);

			person1.calculateTaxableIncome();
			System.out.println("Taxable income " + person1.taxableIncome+ " rounded " + df.format(person1.taxableIncome));

			//Calculate advanceTax based on taxable income
			person1.calculateAdvanceTax();
			System.out.println("Advance tax 18 % = "+ person1.advanceTax);
			System.out.println("Tax free income = " + person1.taxFreeIncome);

			person1.calculateReducedAdvanceTax();
			System.out.println("Reduced advance tax = " + person1.reducedAdvanceTax);

			person1.calculateTotalTaxes();
			System.out.println("Total taxes to be paid : "+person1.totalTaxes);

			person1.calculateNetIncome();
			System.out.println("Net income : "+ person1.netIncome);


		} else if (contractType == 'C') {
			System.out.println("CIVIL");
			System.out.println("Income " + person1.grossIncome);

			person1.calculateSecurityHealthTaxes();
			System.out.println("Social security tax "+ df00.format(person1.socialPensionAmount));
			System.out.println("Health social security "+ df00.format(person1.socialSecurityAmount));
			System.out.println("Sickness social security tax "+ df00.format(person1.socialSicknessAmount));
			System.out.println("Income after social security " + df00.format(person1.incomeMinusSocialSecurity));


			// Set Social Health Taxes 1 and 2
			person1.calculateInsuranceHealthTaxes();
			System.out.println("Health insurance social security tax: 9% = "+ df00.format(person1.socialHealthAmount) + " 7,75% = " +
					df00.format(person1.deductibleSocialHealthAmount));

			//Set base income for tax (ie income on which is applied taxes)
			person1.setBaseIncomeForTax();
			System.out.println("Income basis for tax: "+ person1.incomeMinusSocialSecurity);

			person1.setTaxDeductiblExpensese();
			System.out.println("Tax deductible expenses = " + person1.taxDeductibleExpenses);

			person1.calculateTaxableIncome();
			System.out.println("Taxable income " + person1.taxableIncome+ " rounded " + df.format(person1.taxableIncome));

			//Calculate advanceTax based on taxable income
			person1.calculateAdvanceTax();
			System.out.println("Advance tax 18 % = "+ person1.advanceTax);
			System.out.println("Tax free income = " + person1.taxFreeIncome);

			person1.calculateReducedAdvanceTax();
			System.out.println("Reduced advance tax = " + person1.reducedAdvanceTax);

			person1.calculateTotalTaxes();
			System.out.println("Total taxes to be paid : "+person1.totalTaxes);

			person1.calculateNetIncome();
			System.out.println("Net income : "+ person1.netIncome);

		} else {
			System.out.println("Unknown type of contract!");
		}
	}


}
