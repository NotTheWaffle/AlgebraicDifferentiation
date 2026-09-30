public class Function {

	public static final Expression identity = new Expression() {
		@Override
		public double evaluate(double x){
			return x;
		}

		@Override
		public Expression derivative(){
			return new Constant(1.0);
		}

		@Override
		public String toString(String contents){
			return contents;
		}
	};

	public static final Expression exp = new Expression() {
		@Override
		public double evaluate(double x){
			return Math.exp(x);
		}
		@Override
		public Expression derivative(){
			return Function.exp;
		}
		@Override
		public String toString(){
			return "exp";
		}
	};
	public static final Expression ln = new Expression() {
		@Override
		public double evaluate(double x){
			return Math.log(x);
		}
		@Override
		public Expression derivative(){
			return new Power(-1);
		}
		@Override
		public String toString(){
			return "ln";
		}
	};

	public static final Expression sin = new Expression() {
		@Override
		public double evaluate(double x){
			return Math.sin(x);
		}
		@Override
		public Expression derivative(){
			return Function.cos;
		}
		@Override
		public String toString(){
			return "sin";
		}
	};
	public static final Expression cos = new Expression() {
		@Override
		public double evaluate(double x){
			return Math.cos(x);
		}
		@Override
		public Expression derivative(){
			return Function.sin.mul(-1);
		}
		@Override
		public String toString(){
			return "cos";
		}
	};
	public static final Expression tan = new Expression() {
		@Override
		public double evaluate(double x){
			return Math.tan(x);
		}
		@Override
		public Expression derivative(){
			return new Power(2).of(Function.sec);
		}
		@Override
		public String toString(){
			return "tan";
		}
	};

	public static final Expression csc = new Expression() {
		@Override
		public double evaluate(double x){
			return 1/Math.sin(x);
		}
		@Override
		public Expression derivative(){
			return new Product(Function.csc, Function.cot).mul(-1);
		}
		@Override
		public String toString(){
			return "csc";
		}
	};
	public static final Expression sec = new Expression() {
		@Override
		public double evaluate(double x){
			return 1/Math.cos(x);
		}
		@Override
		public Expression derivative(){
			return new Product(Function.sec, Function.tan);
		}
		@Override
		public String toString(){
			return "sec";
		}
	};
	public static final Expression cot = new Expression() {
		@Override
		public double evaluate(double x){
			return Math.cos(x) / Math.sin(x);
		}
		@Override
		public Expression derivative(){
			return new Power(2).of(Function.csc).mul(-1);
		}
		@Override
		public String toString(){
			return "cot";
		}
	};

	private Function(){}

}