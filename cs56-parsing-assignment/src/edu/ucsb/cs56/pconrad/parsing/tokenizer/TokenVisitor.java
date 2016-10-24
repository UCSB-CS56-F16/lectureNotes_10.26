package edu.ucsb.cs56.pconrad.parsing.tokenizer;

/**
 * Interface which visitors of tokens must implement, as per the Visitor pattern.
 * Type variables <code>A</code> and <code>T</code> are setup the same way as in
 * the <code>ASTVisitor</code> interface; see the documentation for that interface for
 * more information regarding what these type variables mean and how they are used.
 * @see edu.ucsb.cs56.pconrad.parsing.syntax.ASTVisitor
 */
public interface TokenVisitor<A, T extends Throwable> {
    /**
     * Called whenever this visitor visits an <code>IntToken</code> token.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.IntToken
     *
     * @param value The integer held in the <code>IntToken</code> being visited
     */
    public A visitIntToken(int value) throws T;

    /**
     * Called whenever this visitor visits a <code>CharToken</code> token.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.CharToken
     *
     * @param value The character held in the <code>CharToken> being visited
     */
    public A visitCharToken(char value) throws T;
}
