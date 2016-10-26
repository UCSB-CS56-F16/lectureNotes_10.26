package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class PlusTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new PlusToken();
    }
}
