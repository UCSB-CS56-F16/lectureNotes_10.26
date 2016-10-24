package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Interface which visitors of operators must implement, as per th Visitor pattern.
 * Type variables <code>A</code> and <code>T</code> are setup the same way as in
 * the <code>ASTVisitor</code> interface; see the documentation for that interface for
 * more information regarding what these type variables mean and how they are used.
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.ASTVisitor
 */
public interface OperatorVisitor<A, T extends Throwable> {
    /**
     * Called whenever this visitor visits a <code>Plus</code> operator.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Plus
     */
    public A visitPlus() throws T;

    /**
     * Called whenever this visitor visits a <code>Minus</code> operator.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Minus
     */
    public A visitMinus() throws T;

    /**
     * Called whenever this visitor visits a <code>Times</code> operator.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Times
     */
    public A visitTimes() throws T;

    /**
     * Called whenever this visitor visits a <code>Div</code> operator.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Div
     */
    public A visitDiv() throws T;
}
