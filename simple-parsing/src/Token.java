public class Token {

    @Override public String toString() {
	return "Token";
    }
    
    @Override
    public int hashCode() {
	String s = this.toString();
	return s.hashCode();
    }
	
}
