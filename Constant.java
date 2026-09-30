public class Constant extends Expression {

	public final double value;

	public Constant(double value){
		this.value = value;
	}

	@Override
	public double evaluate(double x){
		return value;
	}

	@Override
	public Expression derivative(){
		return new Constant(0);
	}

	@Override
	public String toString(String contents){
		return Double.toString(value);
	}
	@Override
	public String toString(){
		return Double.toString(value);
	}
}
