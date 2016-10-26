package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class LParenToken extends Token {

    @Override
    public String toString() { return "LParenToken"; }
    
    @Override
    public boolean equals(Object obj)    {
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	return true;
    }

}
