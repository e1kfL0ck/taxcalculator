package taxcalculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {

	//TODO : dans quelle classe mettre ces valeurs ?
	public static final double SOC_PENSION_RATE = 9.76;
	public static final double SOC_SECURITY_RATE = 1.5;
	public static final double SOC_SICKNESS_RATE = 2.45;
	public static final double SOC_HEALTH_RATE = 9.0;
	public static final double SOC_HEALTH_DEDUCTIBLE_RATE = 7.75;
	public static final double ADVANCE_TAX_RATE = 18.0;

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

		Contract person1 = null;

		switch (contractType) {
			case 'E':
				person1 = new EmployementContract(income);
				break;
			case 'C':
				person1 = new CivilContract(income);
				break;
			default:
				System.out.println("Unknown type of contract!");
				System.exit(1);
		}

		person1.caculateTaxes();

		TaxDisplay td = new TaxDisplay();
		td.displayDetails(person1);

	}

}
