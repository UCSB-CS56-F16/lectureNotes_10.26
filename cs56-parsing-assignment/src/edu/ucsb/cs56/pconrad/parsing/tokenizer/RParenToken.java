package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class RParenToken extends Token {

    @Override
    public String toString() { return "RParenToken"; }
    
    @Override
    public boolean equals(Object obj)    {
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	return true;
    }

}
