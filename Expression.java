

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
		public String toString(String contents){
			return "x";
		}
		@Override
		public String toString(){
			return "x";
		}
	};


	public abstract double evaluate(double x);
	public abstract Expression derivative();

	public String toString(String contents){
		return toString()+"("+contents+")";
	}
	@Override
	public String toString(){
		return toString("x");
	}

	// by default, an expression isn't simplifiable
	public Expression simplified(){
		return this;
	}

	public final Expression add(Expression expression){
		return new Sum(this, expression);
	}
	public final Expression mul(Expression expression){
		return new Product(this, expression);
	}
	public final Expression mul(double value){
		return new Product(new Constant(value), this);
	}
	public final Expression of(Expression expression){
		return new Application(this, expression);
	}
}