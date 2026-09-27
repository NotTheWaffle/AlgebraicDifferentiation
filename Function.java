
public abstract class Function {
	// f(x) = x
	public static final Function identity = new Function() {
		@Override
		public double of(double x){
			return x;
		}

		@Override
		public Function derivative(){
			return new ConstantFunction(1);
		}

		@Override
		public String toString(String contents){
			return contents;
		}
	};
	// f(x) = 1
	public static final Function unit = new Function(){
		@Override
		public double of(double x){
			return 1;
		}
		@Override
		public Function derivative(){
			return new ConstantFunction(0);
		}
		@Override
		public String toString(String contents){
			return "1";
		}
	};

	public static final Function exp = new Function() {
		@Override
		public double of(double x){
			return Math.exp(x);
		}

		@Override
		public Function derivative(){
			return Function.exp;
		}

		@Override
		public String toString(){
			return "exp";
		}
	};
	public static final Function ln = new Function() {
		@Override
		public double of(double x){
			return Math.log(x);
		}

		@Override
		public Function derivative(){
			return Function.power(-1);
		}

		@Override
		public String toString(){
			return "ln";
		}
	};

	public static final Function sin = new Function() {
		@Override
		public double of(double x){
			return Math.sin(x);
		}
		@Override
		public Function derivative(){
			return Function.cos;
		}
		@Override
		public String toString(){
			return "sin";
		}
	};
	public static final Function cos = new Function() {
		@Override
		public double of(double x){
			return Math.cos(x);
		}
		@Override
		public Function derivative(){
			return Function.sin.times(-1);
		}
		@Override
		public String toString(){
			return "cos";
		}
	};
	public static final Function tan = new Function() {
		@Override
		public double of(double x){
			return Math.tan(x);
		}
		@Override
		public Function derivative(){
			return Function.compose(Function.power(2), Function.sec);
		}
		@Override
		public String toString(){
			return "tan";
		}
	};

	public static final Function csc = new Function() {
		@Override
		public double of(double x){
			return 1/Math.sin(x);
		}
		@Override
		public Function derivative(){
			return Function.prod(Function.csc, Function.cot).times(-1);
		}
		@Override
		public String toString(){
			return "csc";
		}
	};
	public static final Function sec = new Function() {
		@Override
		public double of(double x){
			return 1/Math.cos(x);
		}
		@Override
		public Function derivative(){
			return Function.prod(Function.sec, Function.tan);
		}
		@Override
		public String toString(){
			return "sec";
		}
	};
	public static final Function cot = new Function() {
		@Override
		public double of(double x){
			return Math.cos(x) / Math.sin(x);
		}
		@Override
		public Function derivative(){
			return Function.compose(Function.power(2), Function.csc).times(-1);
		}
		@Override
		public String toString(){
			return "cot";
		}
	};

	// f(x) = x^p
	public static Function power(double power){
		return new PowerFunction(power);
	}

	// f(x) = g(x) + h(x)
	public final static Function sum(Function function1, Function function2){
		return new SumFunction(function1, function2);
	}
	public final Function add(Function function){
		return Function.sum(this, function);
	}

	// f(x) = g(x) * h(x)
	public final static Function prod(Function function1, Function function2){
		return new ProductFunction(function1, function2);
	}
	public final Function times(Function function){
		return Function.prod(this, function);
	}

	// f(x) = k * g(x)
	public final static Function coefficient(Function function, double coefficient){
		return new Function(){
			@Override
			public double of(double x){
				return coefficient * function.of(x);
			}

			@Override
			public Function derivative(){
				return Function.coefficient(function.derivative(), coefficient);
			}

			@Override
			public String toString(String contents){
				if (coefficient == -1) return "-"+function.toString(contents);
				return coefficient + function.toString(contents);
			}
		};
	}
	public final Function times(double coefficient){
		return Function.coefficient(this, coefficient);
	}

	// f(x) = g(h(x))
	public final static Function compose(Function outerFunction, Function innerFunction){
		return new ComposedFunction(outerFunction, innerFunction);
	}
	public final Function chain(Function function){
		return Function.compose(this, function);
	}

	public final Expression of(Expression arugment){
		return new Application(this, arugment);
	}

	public abstract double of(double x);
	public abstract Function derivative();

	// by default, functions are simplifiable
	public Function simplified(){
		return this;
	}

	// default, should be overwritten in stuff like x^2
	public String toString(String contents){
		return this+"("+contents+")";
	}
}