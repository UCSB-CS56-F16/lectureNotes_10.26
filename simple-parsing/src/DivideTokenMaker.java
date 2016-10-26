public class DivideTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new DivideToken();
    }
}
