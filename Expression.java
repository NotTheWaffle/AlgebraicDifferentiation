

public abstract class Expression {
	public static final Expression X = new Expression() {
		@Override
		public double evaluate(double x){
			return x;
		}

		@Override
		public Expression derivative(){
			return new Constant(1);
		}

		@Override
		public String toString(){
			return "x";
		}
	};


	public abstract double evaluate(double x);
	public abstract Expression derivative();

	// by default, an expression isn't simplifiable
	public Expression simplified(){
		return this;
	}

	public Expression add(Expression expression){
		return new Sum(this, expression);
	}
	public Expression mul(Expression expression){
		return new Product(this, expression);
	}
	public Expression mul(double value){
		return new Coefficient(value, this);
	}
	public Expression toThe(double power){
		return new Application(Function.power(power), this);
	}
}