package expression;

public class Subtract extends Calculate{

    public Subtract(Expressions leftOperator, Expressions rightOperator) {
        super(leftOperator, rightOperator);
    }

    public char getOperation() {return '-';}

    @Override
    public int evaluate(int value) {
        return leftOperator.evaluate(value) - rightOperator.evaluate(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return leftOperator.evaluate(x, y, z) - rightOperator.evaluate(x, y, z);
    }
}
