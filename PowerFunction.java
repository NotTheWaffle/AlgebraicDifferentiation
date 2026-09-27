public class PowerFunction extends Function{
	public final double power;
	public PowerFunction(double power){
		this.power = power;
	}

	@Override
	public double of(double x){
		return Math.pow(x, power);
	}

	@Override
	public Function derivative(){
		// preemptive simplification
		if (power == 2) return Function.identity;
		if (power == 1) return Function.unit;

		return Function.power(power - 1).times(power);
	}

	@Override
	public Function simplified(){
		if (power == 1) return Function.identity;
		if (power == 0) return Function.unit;
		return this;
	}

	@Override
	public String toString(String contents){
		return "("+contents+")^"+power;
	}
}
