package com.github.gof.decorator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

public class DecoratorLambda {

    public static class DefaultSalaryCalculator implements DoubleUnaryOperator {
        public double applyAsDouble(double grossAnnual) {
            return grossAnnual / 12;
        }
    }

    public static double calculate(double grossSalary, DoubleUnaryOperator... functions) {
        return Stream.of(functions)
                .reduce(DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen)
                .applyAsDouble(grossSalary); //0.0 - identity function
    }

    public static void main(String[] args) {
        System.out.println(
                //now the order of taxes is in the right direction - from top to down (NOT inside down as DecoratorGof)
                new DefaultSalaryCalculator()
                        .andThen(Taxes::generalTax)
                        .andThen(Taxes::regionalTax)
                        .andThen(Taxes::healthInsurance)
                        .applyAsDouble(30_000.00)
        );

        System.out.println(
                calculate(30_000.00,
                        new DefaultSalaryCalculator(),
                        Taxes::generalTax,
                        Taxes::regionalTax,
                        Taxes::healthInsurance)
        );
    }
}
