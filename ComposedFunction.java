public class ComposedFunction extends Function {
	public final Function outerFunction;
	public final Function innerFunction;

	public ComposedFunction(Function outerFunction, Function innerFunction){
		this.outerFunction = outerFunction;
		this.innerFunction = innerFunction;
	}

	@Override
	public double of(double x){
		return outerFunction.of(innerFunction.of(x));
	}

	@Override
	public Function derivative(){
		// f'(x) = g'(h(x)) * h'(x)
		return Function.prod(Function.compose(outerFunction.derivative(), innerFunction), innerFunction.derivative());
	}

	@Override
	public Function simplified(){
		Function outerFunction = this.outerFunction.simplified();
		Function innerFunction = this.innerFunction.simplified();
		if (outerFunction == Function.identity) return innerFunction;
		if (innerFunction == Function.identity) return outerFunction;

		if (outerFunction instanceof ConstantFunction) return outerFunction;
		if (innerFunction instanceof ConstantFunction constant) return new ConstantFunction(outerFunction.of(constant.value));

		// (x^a)^b = x^(a*b)
		if (outerFunction instanceof PowerFunction outerPowerFunction && innerFunction instanceof PowerFunction innerPowerFunction){
			return new PowerFunction(outerPowerFunction.power * innerPowerFunction.power);
		}

		// specific simplifications
		if (outerFunction == Function.ln && innerFunction == Function.exp) return Function.identity;

		return new ComposedFunction(outerFunction, innerFunction);
	}

	@Override
	public String toString(String contents){
		return outerFunction.toString(innerFunction.toString(contents));
	}
}