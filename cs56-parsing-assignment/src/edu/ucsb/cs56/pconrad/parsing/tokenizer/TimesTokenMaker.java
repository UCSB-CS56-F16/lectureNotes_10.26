package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class TimesTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new TimesToken();
    }
}
