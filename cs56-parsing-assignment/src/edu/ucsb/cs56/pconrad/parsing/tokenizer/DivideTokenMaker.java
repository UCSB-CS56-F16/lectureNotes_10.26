package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class DivideTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new DivideToken();
    }
}
