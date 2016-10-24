package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents unary minus, e.g., -4, -10, and so on.
 */
public class UnaryMinus implements AST {
    // begin instance variables
    private final AST nested;
    // end instance variables

    /**
     * @param nested The child node underneath this unary minus
     */
    public UnaryMinus(final AST nested) {
	this.nested = nested;
    }

    public boolean equals(final Object other) {
	return (other instanceof UnaryMinus &&
		((UnaryMinus)other).nested.equals(nested));
    }

    public int hashCode() {
	return 1 + nested.hashCode();
    }

    public String toString() {
	return "-" + nested.toString();
    }

    /**
     * Calls <code>visitor</code>'s <code>visitUnaryMinus</code> method, passing
     * along the child node of this unary minus (which itself was provided in
     * the constructor for <code>UnaryMinus</code>).
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.ASTVisitor#visitUnaryMinus
     */
    public <A, T extends Throwable> A accept(final ASTVisitor<A, T> visitor) throws T {
	return visitor.visitUnaryMinus(nested);
    }
} // UnaryMinus

