import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TokenizerTest {

    // begin instance variables
    private final TokenFactory tf;
    // end instance variables

    public TokenizerTest() {
        tf = new TokenFactory();
    }

    @Test
    public void testOneToken() {
        assertArrayEquals(new Token[] { tf.makePlusToken() },
                          Tokenizer.tokenize("+"));
    }

    @Test
    public void testTwoToken() {
        assertArrayEquals(new Token[] {
		tf.makePlusToken(),
		tf.makePlusToken()
	    },
	    Tokenizer.tokenize("++"));
    }

   @Test
    public void testThreeTokens() {
        assertArrayEquals(new Token[] {
		tf.makePlusToken(),
		tf.makeIntToken(42),		
		tf.makePlusToken()
	    },
	    Tokenizer.tokenize("+42+"));
    }

    
}
