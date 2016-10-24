package edu.ucsb.cs56.pconrad.parsing.evaluator;

import edu.ucsb.cs56.pconrad.parsing.syntax.*;

/**
 * <code>OperatorVisitor</code> specific to the evaluator.
 * This is per the usual definition of the Visitor pattern.
 * Intuitively, this will perform the underlying arithmetic
 * operation stipulated by whichever operator is visited.
 * Note that this only comes into play specific to <code>Binop</code>,
 * which is the only AST node which contains an <code>Operator</code>.
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.Binop
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.Operator
 */
public class EvaluatorOperatorVisitor implements OperatorVisitor<Integer, EvaluatorException> {
    // BEGIN INSTANCE VARIABLES
    private final int left;
    private final int right;
    // END INSTANCE VARIABLES

    /**
     * @param left The evaluated value of the left child of the <code>Binop</code>
     * @param right The evaluated value of the right child of the <code>Binop</code>
     */
    public EvaluatorOperatorVisitor(final int left, final int right) {
	this.left = left;
	this.right = right;
    }

    /**
     * Called when the <code>Plus</code> operation is encountered.
     * Simply returns <code>left</code> and <code>right</code> (the
     * constructor parameters) added together, wrapped in an
     * <code>Integer</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Plus
     */
    public Integer visitPlus() throws EvaluatorException {
	return new Integer(left + right);
    }

    /**
     * Called when the <code>Minus</code> operation is encountered.
     * Simply subtracts <code>right</code> from <code>left</code> (the
     * constructor parameters), and wraps the result in an
     * <code>Integer</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Minus
     */
    public Integer visitMinus() throws EvaluatorException {
	return new Integer(left - right);
    }

    /**
     * Called when the <code>Times</code> operation is encountered.
     * Simply returns <code>left</code> and <code>right</code> (the
     * constructor parameters) multiplied together, wrapped in an
     * <code>Integer</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Times
     */
    public Integer visitTimes() throws EvaluatorException {
	return new Integer(left * right);
    }

    /**
     * Called when the <code>Div</code> operation is encountered.
     * Simply returns <code>left</code> divided by <code>right</code> (the
     * constructor parameters), wrapped in an
     * <code>Integer</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Div
     *
     * @throws EvaluatorException If the value of <code>right</code> is zero
     *         (that is, division by zero is attempted)
     */
    public Integer visitDiv() throws EvaluatorException {
	if (right == 0) {
	    throw new EvaluatorException("Division by zero");
	} else {
	    return new Integer(left / right);
	}
    }
} // EvaluatorOperatorVisitor
