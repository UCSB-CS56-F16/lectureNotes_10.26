package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Interface which visitors of ASTs must implement, as per the Visitor
 * pattern.  Type variable <code>A</code> represents the return type
 * of the visitor.  All methods return <code>A</code>, which means that
 * all methods must return something of the same type (e.g., we cannot have
 * <code>visitLiteral</code> return an integer while <code>visitBinop</code>
 * returns a <code>String</code>).  All methods throw something of type
 * <code>T</code>, which is required to extend <code>Throwable</code> via the
 * <code>extends Throwable</code> clause.  This clause is necessary because
 * we can only throw exceptions, and exceptions are all of type <code>Throwable</code>;
 * without this clause, the code would fail to compile with a  type error.
 * Again, all methods throw <code>T</code>, so it isn't possible to have two distinct
 * exceptions thrown by two different methods here.
 */
public interface ASTVisitor<A, T extends Throwable> {
    /**
     * Called whenever this visitor visits a <code>Literal</code> AST node.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Literal
     *
     * @param value The integer value held in the literal AST node visited
     */
    public A visitLiteral(int value) throws T;

    /**
     * Called whenever this visitor visits a <code>Binop</code> AST node.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.Binop
     *
     * @param left The left child of the <code>Binop</code> AST node being visited
     * @param op The operator present at the <code>Binop</code> AST node being visited
     * @param right The right child of the <code>Binop</code> AST node being visited
     */
    public A visitBinop(AST left, Operator op, AST right) throws T;

    /**
     * Called whenever this visitor visits a <code>UnaryMinus</code> AST node.
     * This is per the usual definition from the Visitor pattern.
     * @see edu.ucsb.cs56.pconrad.parsing.syntax.UnaryMinus
     *
     * @param nested The child node underneath the <code>UnaryMinus</code> AST node being visited
     */
    public A visitUnaryMinus(AST nested) throws T;
}
