package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents a literal in the AST, which are simply integers for our case.
 */
public class Literal implements AST {
    // begin instance variables
    private final int value;
    // end instance variables

    /**
     * @param value The integer for this AST node to hold
     */
    public Literal(final int value) {
        this.value = value;
    }

    public boolean equals(final Object other) {
        return (other instanceof Literal &&
                ((Literal)other).value == value);
    }

    public int hashCode() {
	return value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Calls <code>visitor</code>'s <code>visitLiteral</code> method, passing
     * along the integer value of this AST node (which was provided in the
     * constructor).
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.ASTVisitor#visitLiteral
     */
    public <A, T extends Throwable> A accept(final ASTVisitor<A, T> visitor) throws T {
	return visitor.visitLiteral(value);
    }
} // Literal
