package edu.ucsb.cs56.pconrad.parsing.evaluator;

import edu.ucsb.cs56.pconrad.parsing.DefaultInterpreterInterface;
import edu.ucsb.cs56.pconrad.parsing.syntax.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.Test;

/**
 * Tests the evaluator; that is, the code that takes an
 * AST and evaluates it down to an integer.
 */
public class TestEvaluator {
    // BEGIN INSTANCE VARIABLES
    private final ASTFactory af;
    // END INSTANCE VARIABLES

    public TestEvaluator() {
        af = DefaultASTFactory.DEFAULT;
    }

    /**
     * Convenience method to evaluate the given <code>ast</code>
     * to an integer
     */
    public static int evaluate(final AST ast) throws EvaluatorException {
	return DefaultInterpreterInterface.DEFAULT.evaluate(ast);
    }

    /**
     * Similar to <code>evaluate</code>, except an <code>EvaluatorException</code>
     * triggers a test failure.  This lifts the burden of annotating tests which
     * are supposed to pass with the otherwise superfluous <code>EvaluatorException</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.evaluator.TestEvaluator#evaluate
     * @see edu.ucsb.cs56.pconrad.parsing.evaluator.EvaluatorException
     */
    public static int evaluateNoException(final AST ast) {
	int retval = 0;
	boolean retvalSet = false;
	
	try {
	    retval = evaluate(ast);
	    retvalSet = true;
	} catch (EvaluatorException e) {
	    fail("Unexpected evaluator exception: " + e.toString());
	}

	assert(retvalSet);
	return retval;
    }
    
    @Test
    public void testLiteral() {
	assertEquals(42,
		     evaluateNoException(af.makeLiteral(42)));
    }

    @Test
    public void testPlus() {
	assertEquals(8,
		     evaluateNoException
		     (af.makePlusNode(af.makeLiteral(6),
				      af.makeLiteral(2))));
    }

    @Test
    public void testDivNonZero() {
	assertEquals(10,
		     evaluateNoException(af.makeDivNode(af.makeLiteral(30),
                                                        af.makeLiteral(3))));
    }

    @Test
    public void testUnaryMinusLiteral() {
	assertEquals(-10,
		     evaluateNoException(af.makeUnaryMinusNode(af.makeLiteral(10))));
    }
    
    // BEGIN TESTS INVOLVING DIVISION BY ZERO
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDivDirectZero() throws EvaluatorException {
	thrown.expect(EvaluatorException.class);
	evaluate(af.makeDivNode(af.makeLiteral(14),
                                af.makeLiteral(0)));
    }
} // TestEvaluator

