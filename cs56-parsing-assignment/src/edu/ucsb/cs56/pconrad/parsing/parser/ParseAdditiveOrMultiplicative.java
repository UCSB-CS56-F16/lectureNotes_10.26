package edu.ucsb.cs56.pconrad.parsing.parser;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;

/**
 * Observation: the only differences between <code>parseMultiplicateExpression</code>
 * and <code>parseAdditiveExpression</code> (both <code>private</code> to
 * <code>Parser</code>) are:
 * <p><ol>
 * <li>which method is called internally to parse the nested expressions
 * <li>which method is called internally to parse the operator in play
 * </ol><p>
 * As such, we factor out this commonality into a class that
 * treats these above two methods as abstract, and implements
 * the functionality in terms of these abstract methods.
 * @see edu.ucsb.cs56.pconrad.parsing.parser.Parser
 */
public abstract class ParseAdditiveOrMultiplicative {
    // BEGIN ABSTRACT METHODS
    /**
     * The "base" thing to parse, that is, the component that parses nested expressions.
     *
     * @param pos The position in the input where we start parsing
     * @return a result encapsulating the parsed in AST
     */
    public abstract ParseResult<AST> parseBase(int pos) throws ParserException;

    /**
     * Parser for the operator in play.
     *
     * @param pos The position in the input where we start parsing
     * @return a result encapsulating the operator parsed in
     */
    public abstract ParseResult<Operator> parseOp(int pos) throws ParserException;
    // END ABSTRACT METHODS

    /**
     * Actually parses in the expression, using <code>parseBase</code> and
     * <code>parseOp</code> in the process.
     *
     * @param pos The position in the input where we start parsing
     * @return a result encapsulating the overall AST parsed in
     */
    public ParseResult<AST> parseExp(final int pos) throws ParserException {
	ParseResult<AST> curResult = parseBase(pos);
	boolean shouldRun = true;

	while (shouldRun) {
	    try {
		final ParseResult<Operator> opResult = parseOp(curResult.getNextPos());
		final ParseResult<AST> nextBaseResult = parseBase(opResult.getNextPos());
		curResult = new ParseResult<AST>(new Binop(curResult.getResult(),
							   opResult.getResult(),
							   nextBaseResult.getResult()),
						 nextBaseResult.getNextPos());
	    } catch (ParserException e) {
		shouldRun = false;
	    }
	}

	return curResult;
    }
}
