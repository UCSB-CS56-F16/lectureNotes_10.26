package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents the subtraction operation (<code>-</code>)
 */
public class Minus extends OperatorScaffold {
    // begin constants
    public static final Minus MINUS = new Minus();
    // end constants
    
    public Minus() {
        super('-');
    }

    /**
     * Calls <code>visitor</code>'s <code>visitMinus</code> method.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.OperatorVisitor#visitMinus
     */
    public <A, T extends Throwable> A accept(final OperatorVisitor<A, T> visitor) throws T {
	return visitor.visitMinus();
    }
} // Minus
