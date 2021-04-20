package com.crucosoft.calculator;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator(5);

        System.out.println("Welcome to my frivolous calculator");
        calculator.printHelpPage();

        while(true){
            Calculator.Operators operator = Calculator.Operators.DEFAULT;
            String num1 = "0";
            String num2 = "0";

            System.out.print("command: ");
            String command = scanner.next();

            if(command.equals("quit")) break;
            if(command.equals("help")) {
                calculator.printHelpPage();
                continue;
            }

            try {
            operator = Calculator.Operators.find(command);
            } catch (IllegalArgumentException ignored){
                System.out.println("Unknown command");
                continue;
            }

            if(operator.getValue()>0) {
                System.out.print("Enter first number: ");
                num1 = scanner.next();
            }

            if(operator.getValue()>1) {
                System.out.print("Enter second number: ");
                num2 = scanner.next();
            }

            System.out.println("Answer: " + calculator.calculate(num1, num2, operator));
        }
    }
}
