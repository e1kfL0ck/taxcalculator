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

- First tests to check the consitstency of the new program
- The contract has been moved to it's own class for a cleaner code

### 31/10/2025
- Two types contract have been created for the differences in calculation
- The main contract class should not be used to create new object, therefore it's abstract
- Some of the methods that need to be reimplemented by each type of contract are abstract as well
