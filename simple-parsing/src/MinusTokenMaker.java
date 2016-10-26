public class MinusTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new MinusToken();
    }
}
