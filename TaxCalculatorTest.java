package com.bartoszwalter.students.taxes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorTest {

    private static final double DELTA = 1e-6;

    private static void runFullPipeline(Contract tc) {
        tc.calculateSecurityHealthTaxes();
        tc.calculateInsuranceHealthTaxes();
        tc.setBaseIncomeForTax();
        tc.setTaxDeductibleExpenses();
        tc.calculateTaxableIncome();
        tc.calculateAdvanceTax();
        tc.calculateReducedAdvanceTax();
        tc.calculateTotalTaxes();
        tc.calculateNetIncome();
    }


    @Test
    @DisplayName("Employment E: gross 1000 → expected components and net")
    void employmentExample() {
        Contract tc = new EmployementContract(1000.00);
        runFullPipeline(tc);

        assertEquals(97.60, tc.socialPensionAmount, DELTA);
        assertEquals(15.00, tc.socialSecurityAmount, DELTA);
        assertEquals(24.50, tc.socialSicknessAmount, DELTA);

        // income minus social security
        double baseHealth = 1000.00 - (97.60 + 15.00 + 24.50);
        assertEquals(baseHealth, tc.incomeMinusSocialSecurity, DELTA);

        // health insurance
        assertEquals(77.661, tc.socialHealthAmount, DELTA);
        assertEquals(66.8747499, tc.deductibleSocialHealthAmount, DELTA);

        // taxable base and tax
        assertEquals(1000.00 - 111.25, tc.taxableIncome, DELTA);
        assertEquals(159.975, tc.advanceTax, DELTA);

        // reduced advance tax and totals
        assertEquals(46.7702501, tc.reducedAdvanceTax, DELTA);
        assertEquals(261.53125, tc.totalTaxes, DELTA);
        assertEquals(738.46875, tc.netIncome, DELTA);
    }

    @Test
    @DisplayName("Civil C: gross 1000 → expected components and net")
    void civilExample() {
        Contract tc = new CivilContract(1000.00);
        runFullPipeline(tc);

        assertEquals(97.60, tc.socialPensionAmount, DELTA);
        assertEquals(15.00, tc.socialSecurityAmount, DELTA);
        assertEquals(24.50, tc.socialSicknessAmount, DELTA);

        double baseHealth = 1000.00 - (97.60 + 15.00 + 24.50);
        assertEquals(baseHealth, tc.incomeMinusSocialSecurity, DELTA);

        assertEquals(77.661, tc.socialHealthAmount, DELTA);
        assertEquals(66.8747499, tc.deductibleSocialHealthAmount, DELTA);

        // tax-deductible expenses = 20% of baseHealth
        assertEquals(690.31999999999, tc.taxableIncome, DELTA);

        // PIT before relief
        assertEquals(124.25759999999998, tc.advanceTax, DELTA);

        // reduced advance tax and net
        assertEquals(124.25759999999998 - 66.8747499, tc.reducedAdvanceTax, DELTA);

        double expectedTotalTaxes = 97.60 + 15.00 + 24.50 + 77.661 + 57.38284999999999;
        assertEquals(expectedTotalTaxes, tc.totalTaxes, DELTA);
        assertEquals(727.856150, tc.netIncome, DELTA);
    }

    @Test
    @DisplayName("Employment E: gross 256846 → matches program outputs")
    void employmentExample_2() {
        Contract tc = new EmployementContract(256846.00);
        runFullPipeline(tc);

        // social contributions
        assertEquals(25068.1696, tc.socialPensionAmount, DELTA);
        assertEquals(3852.69,     tc.socialSecurityAmount, DELTA);
        assertEquals(6292.727000000001, tc.socialSicknessAmount, DELTA);

        // health base
        assertEquals(221632.4134, tc.incomeMinusSocialSecurity, DELTA);

        // health insurance
        assertEquals(19946.917206,  tc.socialHealthAmount, DELTA);      // 9%
        assertEquals(17176.5120385, tc.deductibleSocialHealthAmount, DELTA);      // 7.75%

        // taxable base and PIT
        assertEquals(256734.75, tc.taxableIncome, DELTA);
        assertEquals(46212.255, tc.advanceTax, DELTA);

        // reduced advance tax and totals
        assertEquals(28989.4129615, tc.reducedAdvanceTax, DELTA);
        assertEquals(84149.9167675, tc.totalTaxes, DELTA);
        assertEquals(172696.0832325, tc.netIncome, DELTA);
    }
}
