package com.crucosoft.calculator;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {

    // private static final int DEFAULT_SCALE = 5;

    private final int scale;

    Calculator(int scale) {
        this.scale = scale;
    }

    public void printHelpPage() {
        System.out.println("You can use following operator:");
        for(Operators operator: Operators.values()){
            System.out.println(operator.key + ": " + operator.name());
        }
        System.out.println();
        System.out.println("To get help type 'help'");
        System.out.println("To quit type 'quit'");
    }

    // Actually it is not the best practise to use such a long unnecessary method, but it is for task...
    public String calculate(String num1, String num2, @NotNull Operators operator) {
        BigDecimal answer;
        BigDecimal firstNumber;
        BigDecimal secondNumber;

        try {
            firstNumber = new BigDecimal(num1);
            secondNumber = new BigDecimal(num2);

            switch (operator) {
                case ADDITION:
                    answer = firstNumber.add(secondNumber);
                    break;
                case SUBTRACTION:
                    answer = firstNumber.subtract(secondNumber);
                    break;
                case DIVISION:
                    answer = firstNumber.divide(secondNumber, this.scale, RoundingMode.HALF_UP);
                    break;
                case MULTIPLICATION:
                    answer = firstNumber.multiply(secondNumber);
                    break;
                case POW:
                    answer = firstNumber.pow(Integer.parseInt(secondNumber.stripTrailingZeros().toString()));
                    break;
                case SQRT:
                        answer = firstNumber.sqrt(MathContext.UNLIMITED);
                    break;
                default:
                    return "Error: Unknown Operation";
            }
        } catch (NumberFormatException ex) {
            return ex.toString();
        } catch (ArithmeticException ex) {
            return ex.getMessage();
        }

        return answer.stripTrailingZeros().toString();
    }

    enum Operators {
        ADDITION("add", 2),
        SUBTRACTION("subtract", 2),
        DIVISION("divide", 2),
        MULTIPLICATION("multiply", 2),
        POW("pow", 2),
        SQRT("sqrt", 1),
        DEFAULT;

        final String key;
        final Integer value;

        Operators() {
            key = "default";
            value = 0;
        }

        Operators(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        static Operators find(String key) {
            for (Operators operator : Operators.values()) {
                if (operator.getKey().equals(key)) return operator;
            }
            return DEFAULT;
        }

        private String getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }
    }
}
