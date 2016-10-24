# lectureNotes_10.24

Lecture notes for 10.24

In the directory cs56-parsing-assignment, we have some "legacy code" for a parser.  

It parses and evaluates arithmetic expressions.

We'll do our best to try to understand this code.


# For next time....

Simple represntation of a finite state automaton

```java
public class State {

       private int num; // 0, for s0, 1 for s1, etc.

       private boolean final; // accepting state?

       // TODO: need some way to know what kind of token
       // to emit.	  

       // nextState maps a character to the next state
       private HashTable<Character,State> nextState;

}
```

```java

public class FiniteAutomaton {

       // each state has a hashtable of the transitions
       // to the next state
       private ArrayList<State> states;
       

}
