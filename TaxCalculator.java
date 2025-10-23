package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	private double income;
	private char contractType;

	//Basis values
	public static final double SOC_SECURITY_RATE = 9.76;
	public static final double SOC_HEALTH_SECURITY_RATE = 1.5;
	public static final double SOC_SICK_SECURITY_RATE = 2.45;
	public static final double SOC_HEALTH1_RATE = 9.0;
	public static final double SOC_HEALTH2_RATE = 7.75;
	public static final double ADVANCE_TAX_RATE = 18.0;

	// social security taxes
	public double soc_security; // 9,76% of basis
	public double soc_health_security; // 1,5% of basis
	public double soc_sick_security; // 2,45% of basis

	// health-related taxes
	public double taxDeductibleExpenses = 111.25;


	public double soc_health1 = 0; // of basis up to 9%
	public double soc_health2 = 0; // of basis up to  7,75 %

	// a quoi sert ce truc ?
	public static double advanceTaxPaidadvanceTax = 0; // advance tax 18%

	public double taxFreeIncome; // tax-free income monthly 46,33 PLN


	public static double advanceTaxPaid0 = 0;

	public static double advanceTaxPaid = 0;
	public static double taxPaid = 0;



	// New values
	private double incomeMinusSocialSecurity;
	private double taxableIncome;
	private double advanceTax;
	private double totalTaxes;
	private double netIncome;


	// Functions

	public TaxCalculator(double income, char contractType) {
		this.income = income;
		this.contractType = contractType;
		if (contractType == 'E') {
			taxDeductibleExpenses = 111.25;
			taxFreeIncome = 46.33;
		} else if (contractType == 'C') {
			taxDeductibleExpenses = (income * 20) / 100;
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

		TaxCalculator person1 = new TaxCalculator(income, contractType);

		DecimalFormat df00 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#");

		if (person1.contractType == 'E') {
			System.out.println("EMPLOYMENT");
			System.out.println("Income " + person1.income);

			person1.calculateSecurityHealthTaxes();

			System.out.println("Social security tax "+ df00.format(person1.soc_security));
			System.out.println("Health social security "+ df00.format(person1.soc_health_security));
			System.out.println("Sickness social security tax "+ df00.format(person1.soc_sick_security));
			System.out.println("Income basis for health social security: "+ person1.income);

			System.out.println("Income after social security " + df00.format(person1.incomeMinusSocialSecurity));

			// Set Social Health Taxes 1 and 2
			person1.calculateOtherHealthTaxes();

			//Show Social Health Taxes 1 and 2
			System.out.println("Health social security tax: 9% = "+ df00.format(person1.soc_health1) + " 7,75% = " +
					df00.format(person1.soc_health2));

			System.out.println("Tax deductible expenses "+ person1.taxDeductibleExpenses);

			person1.calculateTaxableIncome();
			System.out.println("Taxable income " + person1.taxableIncome+ " rounded " + df.format(person1.taxableIncome));

			//Calculate advanceTax based on taxable income
			person1.calculateTaxableIncome();
			person1.calculateAdvanceTax();

			System.out.println("Advance tax 18 % = "+ person1.advanceTax);
			System.out.println("Tax free income = " + person1.taxFreeIncome);


			person1.calculateTotalTaxes();
			System.out.println("Total taxes to be paid : "+person1.totalTaxes);

			person1.calculateNetIncome();
			System.out.println("Net income : "+ df00.format(person1.netIncome));


/*
		} else if (contractType == 'C') {
			System.out.println("CIVIL");
			System.out.println("Income " + income);
			double incomeMinusSocialSecurity = calculateIncome(income);

			System.out.println("Social security tax "+ df00.format(soc_security));
			System.out.println("Health social security "+ df00.format(soc_health_security));
			System.out.println("Sickness social security tax "+ df00.format(soc_sick_security));
			System.out.println("Income basis for health social security: "+ income);

			System.out.println("Income after social security " + df00.format(incomeMinusSocialSecurity));

			// Set Social Health Taxes 1 and 2
			calculateOtherHealthTaxes(income);

			System.out.println("Health security tax: 9% = "+ df00.format(soc_health1) + " 7,75% = " + df00.format(soc_health2));
			taxFreeIncome = 0;
			taxDeductibleExpenses  = (income * 20) / 100;
			System.out.println("Tax deductible expenses = "
					+ taxDeductibleExpenses
			);
			double taxedIncome = income - taxDeductibleExpenses ;
			double taxedIncome0 = Double.parseDouble(df.format(taxedIncome));
			System.out.println("income to be taxed = " + taxedIncome
					+ " rounded = " + df.format(taxedIncome0));
			calculateAdvanceTax(taxedIncome0);
			System.out.println("Advance tax 18 % = "
					+ advanceTax);
			double  advanceTax;
			System.out.println("Already paid tax = "
					+ df00.format(taxPaid));
			calculateAdvanceTax();
			advanceTaxPaid0 = Double.parseDouble(df.format(advanceTaxPaid));
			System.out.println("Advance tax  = "
					+ df00.format(advanceTaxPaid) + " rounded = "
					+ df.format(advanceTaxPaid0));
			double netIncome = income
					- ((soc_security + soc_health_security + soc_sick_security) + soc_health1 + advanceTaxPaid0);
			System.out.println();
			System.out
					.println("Net income = "
							+ df00.format(netIncome));*/
		} else {
			System.out.println("Unknown type of contract!");
		}
	}


	public void calculateAdvanceTax() {
		advanceTax = (income * ADVANCE_TAX_RATE) / 100;
	}

	public void calculateOtherHealthTaxes() {
		soc_health1 = (income * SOC_HEALTH1_RATE) / 100;
		soc_health2 = (income * SOC_HEALTH2_RATE) / 100;
	}

	private void calculateSecurityHealthTaxes() {
		soc_security = (income * SOC_SECURITY_RATE) / 100;
		soc_health_security = (income * SOC_HEALTH_SECURITY_RATE) / 100;
		soc_sick_security = (income * SOC_SICK_SECURITY_RATE) / 100;

		incomeMinusSocialSecurity = income - soc_security - soc_health_security - soc_sick_security;
	}

	private void calculateTaxableIncome() {
		taxableIncome = income-taxDeductibleExpenses;
	}

	private void calculateTotalTaxes() {
		totalTaxes = soc_security + soc_health_security + soc_sick_security + soc_health1 + advanceTaxPaid;;
	}

	private void calculateNetIncome() {
		netIncome = income - totalTaxes;
	}
}
