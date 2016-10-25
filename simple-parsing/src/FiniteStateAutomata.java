public class FiniteStateAutomata {


    private class State {
	private int num;
	private TokenMaker tm=null;

	public State(int num) {
	    this.num = num;
	}

	public State(int num, TokenMaker tm) {
	    this(num);
	}

    }



}
