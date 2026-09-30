public class Power extends Expression {

	public final double power;

	public Power(double power){
		this.power = power;
	}

	@Override
	public double evaluate(double x){
		return Math.pow(x, power);
	}

	@Override
	public Expression derivative(){
		return new Power(power-1).mul(power);
	}

	@Override
	public Expression simplified(){
		if (power == 1) return Function.identity;
		if (power == 0) return new Constant(1);
		return this;
	}

	@Override
	public String toString(String contents){
		return "("+contents+")^"+Double.toString(power);
	}
}
