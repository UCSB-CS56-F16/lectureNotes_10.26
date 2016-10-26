package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class IntTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new IntToken(s);
    }
}
