package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class MinusTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new MinusToken();
    }
}
