package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents the multiplication operation (<code>*</code>)
 */
public class Times extends OperatorScaffold {
    // begin constants
    public static final Times TIMES = new Times();
    // end constants
    
    public Times() {
        super('*');
    }

    /**
     * Calls <code>visitor</code>'s <code>visitTimes</code> method.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.OperatorVisitor#visitTimes
     */
    public <A, T extends Throwable> A accept(final OperatorVisitor<A, T> visitor) throws T {
	return visitor.visitTimes();
    }
} // Times
