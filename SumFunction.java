public class SumFunction extends Function{
	public final Function function1;
	public final Function function2;
	public SumFunction(Function function1, Function function2){
		this.function1 = function1;
		this.function2 = function2;
	}

	@Override
	public double of(double x){
		return function1.of(x) + function2.of(x);
	}

	@Override
	public Function derivative(){
		// f'(x) = g'(x) + h'(x)
		return Function.sum(function1.derivative(), function2.derivative());
	}

	@Override
	public Function simplified(){
		Function opA = function1.simplified();
		Function opB = function2.simplified();
		if (opA == opB){
			return new CoefficientFunction(2, opA);
		}
		Double a = null;
		Double b = null;
		if (opA instanceof ConstantFunction constA){
			a = constA.value;
		}
		if (opB instanceof ConstantFunction constB){
			b = constB.value;
		}
		if (a != null && b != null) return new ConstantFunction(a + b);
		if (a != null && a == 0) return opB;
		if (b != null && b == 0) return opA;
		// inverse distribution
		if (opA instanceof CoefficientFunction coeffA && opB instanceof CoefficientFunction coeffB && coeffA.coefficient == coeffB.coefficient){
			return new CoefficientFunction(coeffA.coefficient, new SumFunction(coeffA.function, coeffB.function));
		}
		return new SumFunction(opA, opB);
	}

	@Override
	public String toString(String contents){
		return "("+function1.toString(contents)+" + "+function2.toString(contents)+")";
	}
}
