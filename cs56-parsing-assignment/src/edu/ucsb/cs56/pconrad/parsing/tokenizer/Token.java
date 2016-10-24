package edu.ucsb.cs56.pconrad.parsing.tokenizer;

/**
 * Interface which all tokens must implement.
 */
public interface Token {
    public boolean equals(Object other);
    public int hashCode();
    public String toString();

    /**
     * The accept method as per the Visitor pattern, allowing us to visit
     * different kinds of tokens.  The meaning of type variables <code>A</code>
     * and <code>T</code> is identical to how they are used in the
     * <code>AST</code> interface's accept method; see that definition for more
     * information.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.AST#accept
     */
    public <A, T extends Throwable> A accept(TokenVisitor<A, T> visitor) throws T;
}
