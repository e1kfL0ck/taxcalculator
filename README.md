# taxcalculator
TaxCalculator - a kata for a Clean Code exercise

## Overview

## Modifications

### 23/10/2025

- Modify the original class for a more readable program structure.
- Implement the class in OOP style.

### 24/10/2025

- Reimplement the tax algorithm according to the following principle :

>  Compute social contributions from gross pay and subtract them, calculate health insurance on that base (9% total with 7.75% deductible), derive the taxable base as gross minus deductible costs then round it, apply the PIT rate to get tax before relief, subtract the monthly relief and the 7.75% health deduction to obtain the advance tax.

### 28/10/2025

- First tests to validate the behavior of the rewrited code
- The contract has been moved to it's own class for a cleaner code
  - Contract.java

### 31/10/2024
- Two contract types have been created for the differences in calculation, resulting in 2 child classes:
    - EmploymentContract.java
    - CivilContract.java
- The main contract class should not be used to create new objects; therefore, it's abstract
- The methods that need to be reimplemented by each contract type are abstract as well:
    - setBaseIncomeForTax()
    - setTaxDeductibleExpenses()
- A new class to display data has been created:
    - TaxDisplay.java
    - Because both child classes contain exactly the same variables, we don't need to differentiate what will be printed by this class; therefore, using a Contract object to be printed is consistent
- The contract type variable has been removed from the child classes as it is not necessary anymore; the type can be retrieved using instanceof when needed
