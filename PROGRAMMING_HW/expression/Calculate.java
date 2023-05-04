package expression;

public class Calculate implements Expressions {

    protected Expressions leftOperator;
    protected Expressions rightOperator;

    public Calculate(Expressions leftOperator, Expressions rightOperator) {
        this.leftOperator = leftOperator;
        this.rightOperator = rightOperator;
    }

    public char getOperation() {
        return 0;
    }

    public int evaluate(int value) {
        return 0;
    }

    public int evaluate(int x, int y, int z) {
        return 0;
    }

    @Override
    public boolean equals(Object rightClass) {
        Calculate leftClass = this;
        return rightClass instanceof Calculate &&
                leftClass.leftOperator.equals(((Calculate) rightClass).leftOperator)
                && leftClass.rightOperator.equals(((Calculate) rightClass).rightOperator) &&
                leftClass.getOperation() == ((Calculate) rightClass).getOperation();
    }

    @Override
    public int hashCode() {
        String op = getOperation() + "";
        return (((leftOperator.hashCode() * 3 + rightOperator.hashCode()) * 3) + op.hashCode()) * 3;
    }

    public String toString() {
        StringBuilder expression = new StringBuilder();
        expression.append("(").append(leftOperator.toString()).append(" ").append(getOperation()).append(" ").
                append(rightOperator.toString()).append(")");
        return expression.toString();

    }
}