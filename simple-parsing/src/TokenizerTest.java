import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TokenizerTest {

    @Test
    public void testOneErrorToken() {
        assertArrayEquals(new Token[] { new ErrorToken("$") },
                          Tokenizer.tokenize("$"));
    }

    @Test
    public void testSingleDigitIntToken() {
        assertArrayEquals(new Token[] { new IntToken("0") },
                          Tokenizer.tokenize("0"));
    }


    @Test
    public void testTwoDigitIntToken() {
        assertArrayEquals(new Token[] { new IntToken("12") },
                          Tokenizer.tokenize("12"));
    }

    @Test
    public void testTwoPlusTwo() {
        assertArrayEquals(new Token[] {
		new IntToken("2"),
		new PlusToken(),
		new IntToken("2")
	    },
	    Tokenizer.tokenize("2+2"));
    }
    
}
