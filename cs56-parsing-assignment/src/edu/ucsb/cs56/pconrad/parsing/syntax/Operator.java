package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Represents an operation, like addition (<code>+</code>) or multiplication (<code>*</code>).
 */
public interface Operator {
    public boolean equals(Object other);
    public int hashCode();
    public String toString();

    /**
     * The <code>accept</code> method, as per the usual definition of the visitor
     * pattern.  The type variables <code>A</code> and <code>T</code> behave
     * similarly to those as in <code>AST</code>'s <code>accept</code> method.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.AST#accept
     */
    public <A, T extends Throwable> A accept(OperatorVisitor<A, T> visitor) throws T;
}
