package expression;

public class Const implements Expressions {

    int constant;
    public Const(int constant) {
        this.constant = constant;
    }

    public int evaluate(int value) {
        return constant;
    }

    public int evaluate(int x, int y, int z) {
        return constant;
    }

    public String toString() {
        return String.valueOf(constant);
    }

    public int hashCode() {
        return constant;
    }

    public boolean equals(Object rightCon) {
        return rightCon instanceof Const && this.constant == ((Const) rightCon).constant;
    }
}