package edu.ucsb.cs56.pconrad.parsing;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;
import edu.ucsb.cs56.pconrad.parsing.tokenizer.*;
import edu.ucsb.cs56.pconrad.parsing.parser.*;
import edu.ucsb.cs56.pconrad.parsing.evaluator.*;

import java.util.ArrayList;

/**
 * A default implementation for InterpreterInterface.
 * This is used in both the test suite and the REPL, and is defined in
 * terms of the components in edu.ucsb.cs56.pconrad.parsing.tokenizer,
 * edu.ucsb.cs56.pconrad.parsing.parser, and
 * edu.ucsb.cs56.pconrad.parsing.evaluator.
 */
public class DefaultInterpreterInterface extends InterpreterInterface {
    
    public static final DefaultInterpreterInterface DEFAULT_INTERPRETER_INTERFACE =
	new DefaultInterpreterInterface();
    
    public ArrayList<Token> tokenize(final String input) throws TokenizerException {
	return new Tokenizer(input).tokenize();
    }
    
    public AST parse(final ArrayList<Token> tokens) throws ParserException {
	return new Parser(tokens).parse();
    }

    public int evaluate(final AST expression) throws EvaluatorException {
	return EvaluatorASTVisitor.evaluate(expression);
    }
} // DefaultInterpreterInterface

