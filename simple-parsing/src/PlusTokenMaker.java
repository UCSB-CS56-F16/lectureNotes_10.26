public class PlusTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new PlusToken();
    }
}
