package expression;

public interface Expressions extends Expression, TripleExpression {
    int evaluate(int value);
    boolean equals(Object o);

    String toString();

    int evaluate(int x, int y, int z);
}
