public class Application extends Expression{
	public final Function function;
	public final Expression argument;

	public Application(Function function, Expression argument){
		this.function = function;
		this.argument = argument;
	}

	@Override
	public double evaluate(double x){
		return function.of(argument.evaluate(x));
	}

	@Override
	public Expression derivative(){
		return new Product(new Application(function.derivative(), argument), argument.derivative());
	}

	@Override
	public Expression simplified(){
		Expression argument = this.argument.simplified();
		if (function == Function.identity) return argument;
		if (function == Function.unit) return new Constant(1);
		if (function instanceof ConstantFunction constant) return new Constant(constant.value);
		// if we know the inside, we know the outside
		if (argument instanceof Constant constant) return new Constant(function.of(constant.value));

		return new Application(function, argument);
	}

	@Override
	public String toString(){
		return function.toString(argument.toString());
	}
}