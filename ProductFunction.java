public class ProductFunction extends Function{
	public final Function function1;
	public final Function function2;

	public ProductFunction(Function function1, Function function2){
		this.function1 = function1;
		this.function2 = function2;
	}

	@Override
	public double of(double x){
		return function1.of(x) * function2.of(x);
	}

	@Override
	public Function derivative(){
		// f'(x) = g'(x) * h(x) + g(x) * h'(x)
		return Function.sum(Function.prod(function1.derivative(), function2), Function.prod(function1, function2.derivative()));
	}

	@Override
	public Function simplified(){
		Function function1 = this.function1.simplified();
		Function function2 = this.function2.simplified();
		if (function1 == function2){
			return new PowerFunction(2).chain(function1);
		}
		Double a = null;
		Double b = null;
		if (function1 instanceof ConstantFunction const1){
			a = const1.value;
		}
		if (function2 instanceof ConstantFunction const2){
			b = const2.value;
		}
		if (a != null && b != null) return new ConstantFunction(a * b);
		if (a != null && a == 0) return new ConstantFunction(0);
		if (b != null && b == 0) return new ConstantFunction(0);
		if (a != null && a == 1) return function2;
		if (b != null && b == 1) return function1;
		if (a != null) return new CoefficientFunction(a, function2);
		if (b != null) return new CoefficientFunction(b, function1);

		if (function1 instanceof PowerFunction powerFunction1 && function2 instanceof PowerFunction powerFunction2){
			return new PowerFunction(powerFunction1.power + powerFunction2.power);
		}

		return new ProductFunction(function1, function2);
	}

	@Override
	public String toString(String contents){
		return "("+function1.toString(contents) + " * " + function2.toString(contents)+")";
	}
}
