package edu.ucsb.cs56.pconrad.parsing;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;
import edu.ucsb.cs56.pconrad.parsing.tokenizer.*;
import edu.ucsb.cs56.pconrad.parsing.parser.*;
import edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorException;

import java.util.ArrayList;

/**
 * Defines an interface around the main components of the interpreter.
 * Note that this is an interface in the more general sense as opposed to
 * the Java-specific meaning; this is specified as a Java abstract class
 * as opposed to a Java interface, on account of the fact that there are
 * non-abstract methods present.
 */
public abstract class InterpreterInterface {
    public abstract ArrayList<Token> tokenize(String input) throws TokenizerException;
    public abstract AST parse(ArrayList<Token> tokens) throws ParserException;
    public abstract int evaluate(AST expression) throws EvaluatorException;
    
    public AST tokenizeAndParse(final String input)
	throws TokenizerException, ParserException {
	return parse(tokenize(input));
    }

    public int tokenizeParseAndEvaluate(final String input)
	throws TokenizerException, ParserException, EvaluatorException {
	return evaluate(parse(tokenize(input)));
    }
}
