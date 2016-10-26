package edu.ucsb.cs56.pconrad.parsing.tokenizer;

public class Token {

    @Override public String toString() {
	return "Token";
    }
    
    @Override
    public int hashCode() {
	String s = this.toString();
	return s.hashCode();
    }

    public <A, T extends Throwable> A accept(TokenVisitor<A, T> visitor) throws T {

	/* TODO: Fix me */
	return null;
    } ;
    
}
