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


    @Test
    public void testTwoPlusTwoWithWhiteSpace() {
        assertArrayEquals(new Token[] {
		new IntToken("2"),
		new PlusToken(),
		new IntToken("2")
	    },
	    Tokenizer.tokenize(" 2 + 2 "));
    }

    @Test
    public void twoDigitArithmeticMixed() {
        assertArrayEquals(new Token[] {
		new IntToken("12"),
		new PlusToken(),
		new IntToken("34"),
		new TimesToken(),
		new IntToken("56"),
		new MinusToken(),
		new IntToken("78"),
		new DivideToken(),
		new IntToken("90"),
	    },
	    Tokenizer.tokenize(" 12  + 34*56 -78/90 "));
    }
    
    @Test
    public void fullSetNoWhiteSpace() {
        assertArrayEquals(new Token[] {
		new IntToken("1234"),
		new PlusToken(),
		new TimesToken(),
		new MinusToken(),
		new IntToken("5678"),		
		new DivideToken(),
		new LParenToken(),
		new RParenToken(),
		new IntToken("3333"),		
	    },
	    Tokenizer.tokenize("1234+*-5678/()3333"));
    }

    @Test
    public void fullSetLotsOfWhiteSpace() {
        assertArrayEquals(new Token[] {
		new IntToken("12"),
		new IntToken("34"),
		new PlusToken(),
		new TimesToken(),
		new MinusToken(),
		new IntToken("5678"),		
		new DivideToken(),
		new LParenToken(),
		new RParenToken(),
		new IntToken("3333"),		
	    },
	    Tokenizer.tokenize(" 12 34 + *  -   5678  / ( )  3333  "));
    }



}
