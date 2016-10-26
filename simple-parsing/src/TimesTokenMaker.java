public class TimesTokenMaker implements TokenMaker {
    public Token makeToken(String s) {
	return new TimesToken();
    }
}
