package edu.ucsb.cs56.pconrad.parsing.evaluator;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;

/**
 * <code>ASTVisitor</code> specific to the evaluator.
 * This is per the usual definition of the Visitor pattern.
 * Intuitively, this will take an AST node and evaluate it down to
 * some integer, in a manner which depends on the particular AST node
 * visited.
 */
public class EvaluatorASTVisitor implements ASTVisitor<Integer, EvaluatorException> {
    // BEGIN CONSTANTS
    // no state, so we can just allocate once
    public static final EvaluatorASTVisitor VISITOR = new EvaluatorASTVisitor();
    // END CONSTANTS

    /**
     * Convenience method to reduce an AST down to an integer.
     * Internally, this uses <code>VISITOR</code>.
     */
    public static int evaluate(final AST expression) throws EvaluatorException {
	return expression.accept(VISITOR);
    }

    /**
     * Called whenever this visits a <code>Literal</code> AST node.
     * This simply wraps the given <code>Literal</code>'s value into
     * an <code>Integer</code> and returns it.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Literal
     */
    public Integer visitLiteral(final int value) throws EvaluatorException {
	return new Integer(value);
    }

    /**
     * Called whenever this visits a <code>Binop</code> AST node.
     * This will execute the appropriate operation depending on the value
     * of <code>operator</code> (e.g., addition, multiplication, etc.).
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Binop
     */
    public Integer visitBinop(final AST left,
			      final Operator operator,
			      final AST right)
	throws EvaluatorException  {
	return operator.accept(new EvaluatorOperatorVisitor(evaluate(left),
							    evaluate(right)));
    }

    /**
     * Called whenever this visits a <code>UnaryMinus</code> AST node.
     * This will simply negate the result of <code>nested</code>, and return
     * this negated result wrapped in an <code>Integer</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.UnaryMinus
     */
    public Integer visitUnaryMinus(final AST nested)
	throws EvaluatorException {
	return new Integer(-evaluate(nested));
    }
} // EvaluatorASTVisitor
