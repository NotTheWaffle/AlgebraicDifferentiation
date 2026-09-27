public class Sum extends Expression{
	public final Expression operandA;
	public final Expression operandB;

	public Sum(Expression operandA, Expression operandB){
		this.operandA = operandA;
		this.operandB = operandB;
	}

	@Override
	public double evaluate(double x){
		return operandA.evaluate(x) + operandB.evaluate(x);
	}
	@Override
	public Expression derivative(){
		return new Sum(operandA.derivative(), operandB.derivative());
	}

	@Override
	public Expression simplified(){
		Expression opA = operandA.simplified();
		Expression opB = operandB.simplified();
		if (opA == opB){
			return new Coefficient(2, opA);
		}
		Double a = null;
		Double b = null;
		if (opA instanceof Constant constA){
			a = constA.value;
		}
		if (opB instanceof Constant constB){
			b = constB.value;
		}
		if (a != null && b != null) return new Constant(a + b);
		if (a != null && a == 0) return opB;
		if (b != null && b == 0) return opA;
		// inverse distribution
		if (opA instanceof Coefficient coeffA && opB instanceof Coefficient coeffB && coeffA.coefficient == coeffB.coefficient){
			return new Coefficient(coeffA.coefficient, new Sum(coeffA.expression, coeffB.expression));
		}
		return new Sum(opA, opB);
	}

	@Override
	public String toString(){
		return "("+operandA+" + "+operandB+")";
	}
}
