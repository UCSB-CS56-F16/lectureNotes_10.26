package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents the division operation (<code>/</code>)
 */
public class Div extends OperatorScaffold {
    // begin constants
    public static final Div DIV = new Div();
    // end constants
    
    public Div() {
        super('/');
    }

    /**
     * Calls <code>visitor</code>'s <code>visitDiv</code> method.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.OperatorVisitor#visitDiv
     */
    public <A, T extends Throwable> A accept(final OperatorVisitor<A, T> visitor) throws T {
	return visitor.visitDiv();
    }
} // Div
