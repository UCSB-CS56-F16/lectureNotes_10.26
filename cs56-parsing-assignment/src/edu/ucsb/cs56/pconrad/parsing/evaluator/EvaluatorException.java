package edu.ucsb.cs56.pconrad.parsing.evaluator;

/**
 * Exception thrown when an error occurs during evaluation.
 * For our arithmetic expression language, the only such error is
 * attempted division by zero.
 */
public class EvaluatorException extends Exception {
    public EvaluatorException(final String message) {
	super(message);
    }
}
