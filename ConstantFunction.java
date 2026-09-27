public class ConstantFunction extends Function{
	public final double value;

	public ConstantFunction(double value){
		this.value = value;
	}

	@Override
	public double of(double x){
		return value;
	}

	@Override
	public Function derivative(){
		return new ConstantFunction(0);
	}

	@Override
	public String toString(String contents){
		return value+"";
	}
}
