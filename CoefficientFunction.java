public class CoefficientFunction extends Function{
	public final double coefficient;
	public final Function function;

	public CoefficientFunction(double coefficient, Function function){
		this.coefficient = coefficient;
		this.function = function;
	}

	@Override
	public double of(double x){
		return coefficient * function.of(x);
	}

	@Override
	public Function derivative(){
		return new CoefficientFunction(coefficient, function.derivative());
	}

	@Override
	public Function simplified(){
		if (coefficient == 0) return new ConstantFunction(0);

		Function function = this.function.simplified();
		if (function instanceof ConstantFunction constant){
			return new ConstantFunction(coefficient * constant.value);
		} else if (function instanceof CoefficientFunction coefficient1){
			return new CoefficientFunction(coefficient * coefficient1.coefficient, coefficient1.function);
		}
		if (coefficient == 1) return function;



		return new CoefficientFunction(coefficient, function);
	}

	@Override
	public String toString(){
		if (coefficient == -1) return "-" + function.toString();
		return coefficient + function.toString();
	}
}
