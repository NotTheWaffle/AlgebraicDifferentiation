public class Product extends Expression {

	public final Expression operandA;
	public final Expression operandB;

	public Product(Expression operandA, Expression operandB){
		this.operandA = operandA;
		this.operandB = operandB;
	}

	@Override
	public double evaluate(double x){
		return operandA.evaluate(x) + operandB.evaluate(x);
	}

	@Override
	public Expression derivative(){
		return new Sum(new Product(operandA.derivative(), operandB), new Product(operandA, operandB.derivative()));
	}

	@Override
	public Expression simplified(){
		Expression opA = operandA.simplified();
		Expression opB = operandB.simplified();
		if (opA == opB){
			return new PowerFunction(2).of(opA);
		}
		Double a = null;
		Double b = null;
		if (opA instanceof Constant constA){
			a = constA.value;
		}
		if (opB instanceof Constant constB){
			b = constB.value;
		}
		if (a != null && b != null) return new Constant(a * b);
		if (a != null && a == 0) return new Constant(0);
		if (b != null && b == 0) return new Constant(0);
		if (a != null && a == 1) return opB;
		if (b != null && b == 1) return opA;
		if (a != null) return new Coefficient(a, opB);
		if (b != null) return new Coefficient(b, opA);
		return new Product(opA, opB);
	}

	@Override
	public String toString(){
		return "("+operandA+" * "+operandB+")";
	}
}
