public class Application extends Expression {

	public final Expression function;
	public final Expression argument;

	public Application(Expression function, Expression argument){
		this.function = function;
		this.argument = argument;
	}

	@Override
	public double evaluate(double x){
		return function.evaluate(argument.evaluate(x));
	}

	@Override
	public Expression derivative(){
		return new Product(new Application(function.derivative(), argument), argument.derivative());
	}

	@Override
	public Expression simplified(){
		Expression argument = this.argument.simplified();
		Expression function = this.function.simplified();

		// if it is the unit
		if (function == Function.identity) return argument;
		// or if it is a 0er
		if (function instanceof Constant constant) return new Constant(constant.value);

		// unit
		if (argument == Function.identity) return function;
		// nullifier
		if (argument instanceof Constant constant) return new Constant(function.evaluate(constant.value));

		return new Application(function, argument);
	}

	@Override
	public String toString(String contents){
		return function.toString(argument.toString(contents));
	}
}