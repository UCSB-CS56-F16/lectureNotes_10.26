package edu.ucsb.cs56.pconrad.parsing.parser;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;
import edu.ucsb.cs56.pconrad.parsing.tokenizer.*;

import java.util.*;

/**
 * Parses a sequence of tokens into an AST.
 * This is specific to our arithmetic expression language.
 */
public class Parser {
    // BEGIN CONSTANTS

    public static final Map<Token, Operator> PLUS_MINUS =
	new HashMap<Token, Operator>() {{
	    put(new PlusToken(), Plus.PLUS);
	    put(new MinusToken(), Minus.MINUS);
	}};
    
    public static final Map<Token, Operator> TIMES_DIV =
	new HashMap<Token, Operator>() {{
	    put(new TimesToken(), Times.TIMES);
	    put(new DivideToken(), Div.DIV);
	}};

    public static final Token LEFT_PAREN_TOKEN = new LParenToken();
    public static final Token RIGHT_PAREN_TOKEN = new RParenToken();
    public static final Token PLUS_TOKEN = new PlusToken();
    public static final Token MINUS_TOKEN = new MinusToken();
    public static final Token TIMES_TOKEN = new TimesToken();
    public static final Token DIV_TOKEN = new DivideToken();
    // END CONSTANTS
    
    // BEGIN INSTANCE VARIABLES
    private final ArrayList<Token> input;
    // END INSTANCE VARIABLES

    /**
     * @param input The tokens to parse.  This is intentionally an <code>ArrayList</code>
     *              to guarantee constant-time random access, which is necessary
     *              for performance.
     */
    public Parser(final ArrayList<Token> input) {
	this.input = input;
    }

    /**
     * <code>TokenVisitor</code> specific for parsing in <code>primary</code> expressions,
     * according to our grammar for arithmetic expressions.  This is per the usual definition
     * of the Visitor pattern.  This is intentionally defined
     * as an inner class within <code>Parser</code>.  This gives the class access to
     * all the methods within <code>Parser</code>, without making those methods <code>public</code>.
     * If this had been defined as a separate class, it would be necessary to make the methods
     * on <code>Parser</code> <code>public</code>, or else <code>PrimaryTokenVisitor</code>
     * would have no way of accessing them.  Considering that the methods are very specific
     * to our particular grammar, and that they reek of implementation detail, it is more
     * appropriate to leave these methods <code>private</code>.
     */
    
    private class PrimaryTokenVisitor implements TokenVisitor<ParseResult<AST>, ParserException> {
	// begin instance variables
	private final int startPos;
	// end instance variables

	public PrimaryTokenVisitor(final int startPos) {
	    this.startPos = startPos;
	}
	
	public ParseResult<AST> visitIntToken(final int value) throws ParserException {
	    return new ParseResult<AST>(new Literal(value), startPos + 1);
	}
	
	public ParseResult<AST> visitLParenToken() throws ParserException {
	    final ParseResult<AST> nestedExp = parseExpression(startPos + 1);
	    final int nextPos = nestedExp.getNextPos();
	    if (tokenAt(nextPos).equals(RIGHT_PAREN_TOKEN)) {
		return new ParseResult<AST>(nestedExp.getResult(),
					    nextPos + 1);
	    } else {
		throw new ParserException("Expected ')'");
	    }
	}

	public ParseResult<AST> visitMinusToken() throws ParserException {
	    final ParseResult<AST> negatedExp = parsePrimary(startPos + 1);
	    return new ParseResult<AST>(new UnaryMinus(negatedExp.getResult()),
					negatedExp.getNextPos());
	}

	public ParseResult<AST> visitCharToken(char value) throws ParserException {
	    /* TODO: FIX ME */
	    return null;
	} 
	
    } // PrimaryTokenVisitor


    /**
     * Parses a <code>primary</code> expression, as per our arithmetic expression grammar.
     *
     * @param pos The position where to start parsing from
     */
    
    private ParseResult<AST> parsePrimary(final int pos) throws ParserException {
	return tokenAt(pos).accept(new PrimaryTokenVisitor(pos));
    } // parsePrimary
    

    private ParseResult<Operator> parsePlusMinus(final int pos) throws ParserException {
	final Token tokenHere = tokenAt(pos);
	if (tokenHere.equals(PLUS_TOKEN)) {
	    return new ParseResult<Operator>(Plus.PLUS, pos + 1);
	} else if (tokenHere.equals(MINUS_TOKEN)) {
	    return new ParseResult<Operator>(Minus.MINUS, pos + 1);
	} else {
	    throw new ParserException("Expected + or - operator ");
	}

    }

    private ParseResult<Operator> parseTimesDiv(final int pos) throws ParserException {
	final Token tokenHere = tokenAt(pos);
	if (tokenHere.equals(TIMES_TOKEN)) {
	    return new ParseResult<Operator>(Times.TIMES, pos + 1);
	} else if (tokenHere.equals(DIV_TOKEN)) {
	    return new ParseResult<Operator>(Div.DIV, pos + 1);
	} else {
	    throw new ParserException("Expected * or / operator ");
	}
    }

    // BEGIN CODE FOR MULTIPLICATIVE AND ADDITIVE EXPRESSIONS
    /**
     * As with <code>PrimaryTokenVisitor</code>, this is defined as an inner class
     * to get access to all the methods on <code>Parser</code>, without (innapropriately)
     * making those methods <code>public</code>.
     */
    private class ParseAdditive extends ParseAdditiveOrMultiplicative {

	public ParseResult<AST> parseBase(final int pos) throws ParserException {
	    return parseMultiplicativeExpression(pos);
	}
	public ParseResult<Operator> parseOp(final int pos) throws ParserException {
	    return parsePlusMinus(pos);
	}
    }

    /**
     * As with <code>PrimaryTokenVisitor</code>, this is defined as an inner class
     * to get access to all the methods on <code>Parser</code>, without (innapropriately)
     * making those methods <code>public</code>.
     */
    private class ParseMultiplicative extends ParseAdditiveOrMultiplicative {
	public ParseResult<AST> parseBase(final int pos) throws ParserException {
	    return parsePrimary(pos);
	}
	public ParseResult<Operator> parseOp(final int pos) throws ParserException {
	    return parseTimesDiv(pos);
	}
    }

    // because the above two classes hold no state and don't have useful
    // constructors, we can simply allocate these ahead of time and use
    // them throughout
    
    private final ParseAdditive PARSE_ADDITIVE = new ParseAdditive();
    private final ParseMultiplicative PARSE_MULTIPLICATIVE = new ParseMultiplicative();

    private ParseResult<AST> parseMultiplicativeExpression(final int pos)
	throws ParserException {
	return PARSE_MULTIPLICATIVE.parseExp(pos);
    }

    private ParseResult<AST> parseAdditiveExpression(final int pos)
	throws ParserException {
	return PARSE_ADDITIVE.parseExp(pos);
    }
    // END CODE FOR MULIPLICATIVE AND ADDITIVE EXPRESSIONS
    
    private ParseResult<AST> parseExpression(final int pos) throws ParserException {
	return parseAdditiveExpression(pos);
    }

    /**
     * Used to get access to underlying tokens.
     * This should <b>always</b> be used instead of directly accessing the underlying
     * list of tokens.  While running off of the end of a list usually indicates a bug,
     * when it comes to parsing running off the end of the list simply means the input
     * wasn't valid (which isn't a bug, but an error in user input).
     *
     * @param pos The position where to get the token
     * @return The token at <code>pos</code>
     * @throws ParserException if <code>pos</code> is out of range; that is, there
     *         is no token at <code>pos</code>.
     */
    private Token tokenAt(final int pos) throws ParserException {
	if (pos < 0 || pos >= input.size()) {
	    throw new ParserException("Attempted to get token out of position");
	} else {
	    return input.get(pos);
	}
    }

    /**
     * Parses the list of tokens provided in the constructor.
     *
     * @return The AST resulting from parsing
     * @throws ParserException If the tokens could not be parsed, e.g., with the
     *         input <code>)3(</code>.
     */
    public AST parse() throws ParserException {
	final ParseResult<AST> rawResult = parseExpression(0);
	if (rawResult.getNextPos() != input.size()) {
	    throw new ParserException("Extra tokens at the end");
	} else {
	    return rawResult.getResult();
	}
    }
} // Parser
