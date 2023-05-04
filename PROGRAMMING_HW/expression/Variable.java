package expression;


public class Variable implements Expressions {

    String varName;

    public Variable(String varName) {
        this.varName = varName;
    }

    public int evaluate(int value) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (varName) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        return 0;
    }


    public String toString() {
        return varName;
    }


    public int hashCode() {
        return varName.hashCode();
    }


    public boolean equals(Object rightClass) {
        return rightClass instanceof Variable && this.varName.equals(((Variable) rightClass).varName);
    }
}
