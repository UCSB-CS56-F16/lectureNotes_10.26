package edu.ucsb.cs56.pconrad.parsing;

import edu.ucsb.cs56.pconrad.parsing.tokenizer.TokenizerException;
import edu.ucsb.cs56.pconrad.parsing.parser.ParserException;
import edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorException;

import java.util.Scanner;

/* 
   Class with a default main routine that takes expressions from standard input
   and interprets them, until uses presses q to quit.

   @author Kyle Dewey, Phill Conrad
   
*/


public class Main {
    public static int evaluate(final String input)
	throws TokenizerException, ParserException, EvaluatorException {
	return DefaultInterpreterInterface.DEFAULT.tokenizeParseAndEvaluate(input);
    }

    /**
     * Returns true if it should exit
     */
    public static boolean shouldExit(final String input) {
        final String trimmed = input.trim();
        return trimmed.equals("q") || trimmed.equals("quit");
    }

    /**
     * Returns true if it should exit
     */
    public static boolean handleInput(final String input) {
        if (shouldExit(input)) {
            return true;
        } else {
            try {
                System.out.println(evaluate(input));
            } catch (TokenizerException e) {
                System.out.println("Failed to tokenize: " + e.getMessage());
            } catch (ParserException e) {
                System.out.println("Failed to parse: " + e.getMessage());
            } catch (EvaluatorException e) {
                System.out.println("Failed to evaluate: " + e.getMessage());
            }
            return false;
        }
    }
    
    public static void main(String[] args) {
	final Scanner input = new Scanner(System.in);
	// FiniteStateAutomaton.debug = true;
	System.out.println("Enter expressions, or q to quit.");
	while (input.hasNextLine() &&
               !handleInput(input.nextLine())) {}
    }
}
