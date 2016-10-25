public class MinusToken extends Token {

    @Override
    public String toString() { return "MinusToken"; }

    @Override
    public boolean equals(Object obj)    {
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	return true;
    }

}
