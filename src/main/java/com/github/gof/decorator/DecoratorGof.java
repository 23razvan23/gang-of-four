package com.github.gof.decorator;

public class DecoratorGof {

    //Flexible salary calculator
    interface SalaryCalculator {
        double calculate(double grossAnnual);
    }

    //Default implementation of the SalaryCalculator interface (just dividing gross salary / 12)
    public static class DefaultSalaryCalculator implements SalaryCalculator {
        @Override
        public double calculate(double grossAnnual) {
            return grossAnnual / 12;
        }
    }

    public static abstract class AbstractTaxDecorator implements SalaryCalculator {
        private final SalaryCalculator salaryCalculator;

        protected AbstractTaxDecorator(SalaryCalculator salaryCalculator) {
            this.salaryCalculator = salaryCalculator;
        }

        protected abstract double applyTax(double salary);

        @Override
        public double calculate(double grossAnnual) {
            double salary = salaryCalculator.calculate(grossAnnual);
            return applyTax(salary);
        }
    }

    public static class GeneralTaxDecorator extends AbstractTaxDecorator {

        protected GeneralTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return Taxes.generalTax(salary);
        }
    }

    public static class RegionalTaxDecorator extends AbstractTaxDecorator {

        protected RegionalTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return Taxes.regionalTax(salary);
        }
    }

    public static class HealthInsuranceTaxDecorator extends AbstractTaxDecorator {

        protected HealthInsuranceTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return Taxes.healthInsurance(salary);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new HealthInsuranceTaxDecorator(
                        new RegionalTaxDecorator(
                                new GeneralTaxDecorator(
                                        new DefaultSalaryCalculator()
                                )
                        )
                ).calculate(30_000.00)
        );
    }
}
