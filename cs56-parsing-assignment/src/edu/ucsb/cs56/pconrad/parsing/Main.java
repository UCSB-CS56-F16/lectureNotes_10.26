package edu.ucsb.cs56.pconrad.parsing;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;
import edu.ucsb.cs56.pconrad.parsing.tokenizer.*;
import edu.ucsb.cs56.pconrad.parsing.parser.*;

import edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorException;

import java.util.Scanner;

/**
 * A small REPL where users can type in arithmetic expressions, which will subsequently be
 * evaluated.  If a malformed expression is entered, or division by zero occurs, it will
 * alert the user.  Type "q" or "quit" to exit.
 */
public class Main {
    /**
     * Evaluate the given expression down to a value, possibly throwing
     * an exception along the way.
     */
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

    /**
     * Entry point for the REPL
     */
    public static void main(String[] args) {
	final Scanner input = new Scanner(System.in);
	while (input.hasNextLine() &&
               !handleInput(input.nextLine())) {}
    }
}
