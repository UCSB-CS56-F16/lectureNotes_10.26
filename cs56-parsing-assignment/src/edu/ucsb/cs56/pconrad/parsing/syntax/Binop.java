package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents binary operations, e.g., 1+2, 5*7, and so on.
 * The actual operator in play is represented by the separate
 * Operator interface.
 */
public class Binop implements AST {
    // begin instance variables
    private final AST left;
    private final Operator op;
    private final AST right;
    // end instance variables

    /**
     * @param left The left child of this AST node
     * @param op The operator present at this AST node
     * @param right The right child of this AST node
     */
    public Binop(final AST left,
		 final Operator op,
		 final AST right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public boolean equals(final Object other) {
        if (other instanceof Binop) {
            final Binop otherOp = (Binop)other;
            return (otherOp.op.equals(op) &&
                    otherOp.left.equals(left) &&
                    otherOp.right.equals(right));
        } else {
            return false;
        }
    }

    public int hashCode() {
	return (left.hashCode() +
		op.hashCode() +
		right.hashCode());
    }

    /**
     * Returns a string in infix representation.
     * (e.g., "(1 + 2)")
     */
    public String toString() {
	return ("(" + left.toString() +
		" " + op.toString() +
		" " + right.toString() +
		")");
    }

    /**
     * Calls <code>visitor</code>'s <code>visitBinop</code> method, using the parameters
     * passed to this node's constructor as arguments.
     */
    public <A, T extends Throwable> A accept(final ASTVisitor<A, T> visitor) throws T {
	return visitor.visitBinop(left, op, right);
    }
} // Binop
