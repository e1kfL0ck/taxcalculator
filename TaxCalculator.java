package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	protected double grossIncome;
	protected char contractType;
	protected double baseIncomeForTax;

	//Basis values
	public static final double SOC_SECURITY_RATE = 9.76;
	public static final double SOC_HEALTH_SECURITY_RATE = 1.5;
	public static final double SOC_SICK_SECURITY_RATE = 2.45;
	public static final double SOC_HEALTH1_RATE = 9.0;
	public static final double SOC_HEALTH2_RATE = 7.75;
	public static final double ADVANCE_TAX_RATE = 18.0;

	// social security taxes
	public double socialPensionAmount; // 9,76% of basis
	public double socialHealthAmount; // 1,5% of basis
	public double socialSicknessAmount; // 2,45% of basis

	// health-related taxes
	public double taxDeductibleExpenses = 111.25;


	public double soc_health1 = 0; // of basis up to 9%
	public double soc_health2 = 0; // of basis up to  7,75 %

	// a quoi sert ce truc ?
	public static double advanceTaxPaidadvanceTax = 0; // advance tax 18%

	public double taxFreeIncome; // tax-free income monthly 46,33, seems like it depend on the already paid tax...


	public static double advanceTaxPaid0 = 0;

	protected double reducedAdvanceTax;

	// New values
	protected double incomeMinusSocialSecurity;
	protected double taxableIncome;
	protected double advanceTax;
	protected double totalTaxes;
	protected double netIncome;


	// Functions

	TaxCalculator(double grossIncome, char contractType) {
		this.grossIncome = grossIncome;
		this.contractType = contractType;
		if (contractType == 'E') {
			taxFreeIncome = 46.33;
		} else if (contractType == 'C') {
			taxFreeIncome = 0;
		} else {
			System.out.println("Unknown type of contract!");
			System.exit(0);
		}
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

		TaxCalculator person1;
		person1 = new TaxCalculator(income, contractType);

		DecimalFormat df00 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#");

		if (person1.contractType == 'E') {
			System.out.println("EMPLOYMENT");
			System.out.println("Income " + person1.grossIncome);

			person1.calculateSecurityHealthTaxes();

			System.out.println("Social security tax "+ person1.socialPensionAmount);
			System.out.println("Health social security "+ person1.socialHealthAmount);
			System.out.println("Sickness social security tax "+ person1.socialSicknessAmount);

			System.out.println("Income basis for health insurance social security: "+ person1.incomeMinusSocialSecurity);

			// Set Social Health Taxes 1 and 2
			person1.calculateInsuranceHealthTaxes();
			System.out.println("Health insurance social security tax: 9% = "+ person1.soc_health1 + " 7,75% = " +
					person1.soc_health2);

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
			System.out.println("Health social security "+ df00.format(person1.socialHealthAmount));
			System.out.println("Sickness social security tax "+ df00.format(person1.socialSicknessAmount));
			System.out.println("Income after social security " + df00.format(person1.incomeMinusSocialSecurity));


			// Set Social Health Taxes 1 and 2
			person1.calculateInsuranceHealthTaxes();
			System.out.println("Health insurance social security tax: 9% = "+ df00.format(person1.soc_health1) + " 7,75% = " +
					df00.format(person1.soc_health2));

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

	protected void calculateSecurityHealthTaxes() {
		socialPensionAmount = (grossIncome * SOC_SECURITY_RATE) / 100;
		socialHealthAmount = (grossIncome * SOC_HEALTH_SECURITY_RATE) / 100;
		socialSicknessAmount = (grossIncome * SOC_SICK_SECURITY_RATE) / 100;

		incomeMinusSocialSecurity = grossIncome - socialPensionAmount - socialHealthAmount - socialSicknessAmount;
	}

	protected void setBaseIncomeForTax() {
		if (this.contractType == 'E') {
			baseIncomeForTax = grossIncome;
		} else if (this.contractType == 'C') {
			baseIncomeForTax = incomeMinusSocialSecurity;
		}
	}

	public void calculateInsuranceHealthTaxes() {
		soc_health1 = (incomeMinusSocialSecurity * SOC_HEALTH1_RATE) / 100;
		soc_health2 = (incomeMinusSocialSecurity * SOC_HEALTH2_RATE) / 100;
	}

	protected void setTaxDeductiblExpensese() {
		if (this.contractType == 'E') {
			taxDeductibleExpenses = 111.25;
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
		reducedAdvanceTax = advanceTax - taxFreeIncome - soc_health2;
	}

	protected void calculateTotalTaxes() {
		totalTaxes = socialPensionAmount + socialHealthAmount + socialSicknessAmount + soc_health1 + reducedAdvanceTax;
	}

	//TODO: sert a qq chose ?
	protected void calculateNetIncome() {
		netIncome = grossIncome - totalTaxes;
	}
}
