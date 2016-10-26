# lectureNotes_10.26

Lecture notes for 10.26

In the directory cs56-parsing-assignment, we have some "legacy code" for a parser.  

It parses and evaluates arithmetic expressions.

We'll do our best to try to understand this code.

Here is the Finite State Automaton that corresponds to this code:


<img src="https://docs.google.com/drawings/d/1d1p1M9JS6Xkx7Ug0p86N3arTUZvk-NgIQo6QIj8ZJS0/pub?w=1212&amp;h=690">



# Notes on the Sample Arithmetic Expression Interpreter

(NOTE: Some of the material below was adapted from a project description originally by Kyle Dewey.)

The starter code you'll be given consists of a tokenizer, parser, and interpreter for a simple arithmetic expression language.

In this language, there is only one type, namely `integer`, and there are only constants, operators, and parentheses.

The language has the following features:

- Addition (`+`)
- Subtraction (`-`)
- Multiplication (`*`)
- Division (`/`).
  Division by zero is considered an error condition which is handled specially.
- Parenthesized expressions
- Unary minus (e.g., `-3`)

Some examples of programs in this language follow, along with their expected results:

| Expresssion             | Result                          |
|-------------------------|---------------------------------|
| `2+4`                   |  6                              |
| `3+4/5-6*2+3/7`         | -9                              |
| `3+(4/5)-(6*2)+(3/7)`   | -9                              |
| `((3+4)/(5-6))*(2+3)/7` | -5                              |      
| `2+`                    | error (parsing)                 |
| `)5(`                   | error (parsing)                 |
| `((2+3)`                | error (parsing)                 |
| `@2`                    | error (tokenization)            |
| `1/0`                   | error (interpretation)          |

The provided starter code collectively handles the features listed above.   The assignment you'll be given will involve extending this set of features in some way.  Your assignment description will explain exactly what features you are required to add.

While some tests are included, you are encouraged to write your own tests.
There may be bugs in the provided code, and indeed some bugs may have been *intentionally placed* into the code.

Further description of each of the provided components follows.


## Provided Parser ##


The parser is spread across the `.java` files in `src/edu/ucsb/cs56/pconrad/parsing/parser`.
While this parser is relatively simple compared to the one for Java, this still contains a fair amount of complexity.
Describing how this parser works is fairly difficult to do purely in English.
Fortunately, there are standardized ways of describing how a parser should work, and we will use one of them here.
The specific technique we will use is that of [Extended Backus-Naur Form (EBNF)](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_Form), which traces its history back to the development of the programming language [ALGOL](https://en.wikipedia.org/wiki/ALGOL) in the 1950s.
This technique, along with its particular application to our arithmetic expression language, is subsequently described.

In EBNF, languages are broken up into **terminals** and **non-terminals**.

* **terminals** correspond to actual symbols that appear in the language being described
* **non-terminals** are symbols that represent larger units in the language, for example an entire expression or part of an expression.

A relatively simple EBNF description of a parser that could handle `1+2` is shown below:

```
simple ::= INTEGER '+' INTEGER
```

The above description roughly states that `simple` is a non-terminal, which consists of a terminal `INTEGER` (which is understood to mean integers like `1`, `734`, `28`, and so on), followed by the `+` token, followed by another `INTEGER` token.

In general, grammars in EBNF consist of a series of *productions*, each of which has:
* a left-hand side consisting of a non-terminal
* the symbol `::=` which is read as "produces"
* a right-hand side, which consists of a sequence that may include both terminals and non-terminals

In our example with `simple`, there is only one production, namely `simple` itself.
The `simple` example is actually too simple for our purposes.
While it will handle inputs like `1+2`, `3+4`, and so on, it does not understand other operations like `-` (e.g., it does not handle `1-2`).
Moreover, it cannot handle nested inputs, such as `1+2+3`.
In order to handle all these other desirable features, we need to add some additional productions to this, and overall make some major changes.
Exactly what changes need to be made and why we make them is beyond the scope of this assignment and even the course overall; this is a topic better suited for [CMPSC 160](http://www.cs.ucsb.edu/education/courses/cmpsc-160) (Compilers).
As such, we will merely provide the final EBNF description of our language's parser, shown below:

```
expression ::= additive-expression
additive-expression ::= multiplicative-expression ( ( '+' | '-' ) multiplicative-expression ) *
multiplicative-expression ::= primary ( ( '*' | '/' ) primary ) *
primary ::= '(' expression ')' | INTEGER | '-' primary
```

This grammar adds in some additional complexity, along with some features of EBNF which have not yet been discussed.
Here is how to make sense of these four productions:

*   The non-terminals in the above grammar are 
    `expression`, `additive-expression`, `multiplicative-expression`, and `primary`.  
*   The terminals are `'+'`, `'-'`, `'*'`, `'/'`, `'('`, `')'`, and `INTEGER`, where `INTEGER` is a stand-in 
    for non-negative integers (e.g., `1`, `17`, `254`, and so on)
*   When parentheses appear without quotes around them (e.g. `( )` as opposed to `'('` and `')'`), 
    they are used for grouping.

* The vertical bar (`|`) signifies "or".  For example `( '*' | '/' )` means "either the star or the slash symbol appears here".
* The star, when not in quotes, signifies "zero or more repetitions of".  
  For example `primary ( ( '*' | '/' ) primary ) *` means "a instance of `primary`" followed by zero or more instances of "a star or a slash followed by another `primary`."

This EBNF description is a simplified version of the example from the Wikipedia page for [Operator-Precedence Parser](https://en.wikipedia.org/wiki/Operator-precedence_parser).
Of special note is the fact that the token `-` appears in two distinct places in the above productions.
One case handles binary minus, as with `1-2`, and another case handles unary minus, as with `-7`.
Both cases still deal with the same token, namely `-`.
The provided parser should correctly handle this description, modulo any bugs.

## Provided Interpreter ##


The interpreter is spread across the `.java` files in `src/edu/ucsb/cs56/pconrad/parsing/parser/evaluator`.
The interpreter is arguably the simplest component in all of the provided code; for the most part, it merely executes whatever the user specified using the [same mechanism previously described](#interpreter_general_description).
The only exeception to this is if division by zero occurs, in which case it will throw an `EvaluatorException`.

# Your next steps

Now that you have read through this tutorial, your next step should probably be to read through the actual assignment that you'll be working on in this course.    Your instructor will let you know where to find it.

