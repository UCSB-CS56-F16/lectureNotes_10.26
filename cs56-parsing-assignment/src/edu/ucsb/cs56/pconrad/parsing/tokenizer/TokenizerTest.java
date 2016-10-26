package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TokenizerTest {

    @Test
    public void testOneErrorToken() {
        assertArrayEquals(new Token[] { new ErrorToken("$") },
                          Tokenizer.tokenizeToArray("$"));
    }

    @Test
    public void testSingleDigitIntToken() {
        assertArrayEquals(new Token[] { new IntToken("0") },
                          Tokenizer.tokenizeToArray("0"));
    }


    @Test
    public void testTwoDigitIntToken() {
        assertArrayEquals(new Token[] { new IntToken("12") },
                          Tokenizer.tokenizeToArray("12"));
    }

    @Test
    public void testTwoPlusTwo() {
        assertArrayEquals(new Token[] {
		new IntToken("2"),
		new PlusToken(),
		new IntToken("2")
	    },
	    Tokenizer.tokenizeToArray("2+2"));
    }


    @Test
    public void testTwoPlusTwoWithWhiteSpace() {
        assertArrayEquals(new Token[] {
		new IntToken("2"),
		new PlusToken(),
		new IntToken("2")
	    },
	    Tokenizer.tokenizeToArray(" 2 + 2 "));
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
	    Tokenizer.tokenizeToArray(" 12  + 34*56 -78/90 "));
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
	    Tokenizer.tokenizeToArray("1234+*-5678/()3333"));
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
	    Tokenizer.tokenizeToArray(" 12 34 + *  -   5678  / ( )  3333  "));
    }



}
