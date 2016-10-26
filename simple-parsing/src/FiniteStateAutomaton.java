import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;

public class FiniteStateAutomaton {

    private String input = "";
    private int pos = 0;
    
    public void setInput(String input) {
	this.input = input;
	this.pos = 0;
    }

    public String getRemainingInput() {
	if (this.pos >= input.length() || this.pos < 0) {
	    return "";
	}
	return input.substring(pos);	
    }
    
    private class State {
	private int num;
	private TokenMaker tm=null;
	private TreeMap<Character,State> nextState =
	    new TreeMap<Character,State>();

	public State(int num) {
	    this.num = num;
	}

	public State(int num, TokenMaker tm) {
	    this(num);
	}
	
	@Override
	public String toString() {
	    String retVal = "";
	    for(Map.Entry<Character,State> entry : nextState.entrySet()) {
		if (!retVal.equals(""))
		    retVal += ", "; 
		char c = entry.getKey();
		State s = entry.getValue();
		retVal += "'" + c + "'->" + s.num;
	    }
	    String tokenString = "";
	    if ( this.tm != null) {
		Token t = tm.makeToken("0");
		tokenString = " (" + t.toString() + ") ";
	    }
	    return "" + this.num + tokenString  + retVal;
	}
	
	@Override public boolean equals(Object obj) {	
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    State other = (State) obj;
	    return other.toString().equals(obj.toString());
	}
	
	@Override public int hashCode(){ return this.toString().hashCode(); }
	
    } // inner class State

    private TreeMap<Integer,State> states =
	new TreeMap<Integer,State>();
    
    private State getState(int num) {
	State s = states.get(num);
	if (s==null) {
	    s = new State(num);
	    states.put(num,s);
	}
	return s;
    }

    
    public void addState(int num) {
	State s = getState(num);
    }


    public void addState(int num, TokenMaker tm) {
	State s = getState(num);
	s.tm = tm;
    }

    public void addTransition(char c, int from, int to) {
	State fromS = getState(from);
	State toS = getState(to);
	fromS.nextState.put(c,toS);
    }

    @Override
    public String toString() {
	String retVal = "";
	retVal += "FSA: \n";	
	for(Map.Entry<Integer,State> entry : states.entrySet()) {
	    int num = entry.getKey();
	    State s = entry.getValue();
	    retVal += "\t " + num + ": " + s.toString() + "\n"; 
	}
	return retVal;
    }

    @Override public boolean equals(Object obj) {	
	    if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    FiniteStateAutomaton other = (FiniteStateAutomaton) obj;
	    return other.toString().equals(obj.toString());
    }
	
    @Override public int hashCode() {
	return this.toString().hashCode();
    }

    public Token nextToken() {

	if (this.getRemainingInput().equals(""))
	    return null;

	State startState = states.get(0);
	State currState = startState;

	String accumulatedToken = "";
	
	if (currState==null) {
	    throw new IllegalStateException("FSA instance does not have a start state (state 0) defined.");
	}

	State nextState = currState.nextState.get(this.input.charAt(this.pos));

	if (nextState == null) {
	    // No transition for first character.  Emit error token.
	    String s = "" + this.input.charAt(this.pos);
	    Token retVal = new ErrorToken(s);
	    this.pos++;
	    return retVal;			    
	}

	accumulatedToken += this.input.charAt(this.pos);
	
	while (nextState != null && pos < this.input.length()) {
	    currState = nextState;
	    this.pos++;
	    if ( pos < this.input.length() ) {
		nextState = currState.nextState.get(this.input.charAt(this.pos));
	    } else {
		nextState = null;
	    }
	    if (nextState != null) {
		accumulatedToken += this.input.charAt(this.pos);
	    }
	}

	if (currState.tm != null) {
	    return currState.tm.makeToken(accumulatedToken.trim());
	}

	if (!accumulatedToken.trim().equals("")) {
	    return new ErrorToken(accumulatedToken.trim());
	}
	
	return null;
    }
}
