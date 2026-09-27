public class LinearFunction extends Function{
	public final double coefficient;

	public LinearFunction(double coefficient){
		this.coefficient = coefficient;
	}

	@Override
	public double of(double x){
		return coefficient * x;
	}

	@Override
	public Function derivative(){
		return new ConstantFunction(coefficient);
	}

	@Override
	public Function simplified(){
		if (coefficient == 0) return new ConstantFunction(0);
		if (coefficient == 1) return Function.identity;
		return this;
	}

	@Override
	public String toString(String contents){
		return coefficient+"("+contents+")";
	}
}
