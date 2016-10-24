package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Interface which all AST nodes must implement.
 */
public interface AST {
    public boolean equals(Object other);
    public int hashCode();
    public String toString();

    /**
     * The accept method as per the Visitor pattern, allowing us to visit
     * different kinds of AST nodes.
     *
     * Type variable <code>A</code> stands for whatever the visitor returns
     * on this AST node (see <code>ASTVisitor</code> for details).
     * The overall annotation on <code>accept</code> thus states
     * that this will return whatever the visitor returns.
     *
     * Type variable <code>T</code> stands for whatever kind of exception
     * the visitor returns.  The clause <code>extends Throwable</code> is necessary
     * to indicate that <code>T</code> must be an exception of some kind; without
     * this clause, we could not say <code>throws T</code> without having a type error.
     * By parameterizing <code>T</code> (as opposed to saying this throws, say,
     * a <code>TokenizerException</code>), <code>accept</code> can throw whatever
     * exception is most appropriate for the provided <code>visitor</code>.
     * The parser takes advantage of this by using this to throw <code>ParserException</code>,
     * which <code>AST</code> need not know about.
     *
     * @param visitor The visitor to accept, as per the visitor pattern
     * @return Whatever the visitor dictates to return (see the <code>ASTVisitor</code> definition)
     * @throws T Whatever the visitor has declared to throw, under conditions which are specific
     *           to the particular visitor being used.
     */
    public <A, T extends Throwable> A accept(ASTVisitor<A, T> visitor) throws T;
}
