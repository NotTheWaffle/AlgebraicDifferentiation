public class Coefficient extends Expression{
	public final double coefficient;
	public final Expression expression;

	public Coefficient(double coefficient, Expression expression){
		this.coefficient = coefficient;
		this.expression = expression;
	}

	@Override
	public double evaluate(double x){
		return coefficient * expression.evaluate(x);
	}

	@Override
	public Expression derivative(){
		return new Coefficient(coefficient, expression.derivative());
	}

	@Override
	public Expression simplified(){
		Expression expression = this.expression.simplified();
		if (expression instanceof Constant constant){
			return new Constant(coefficient * constant.value);
		} else if (expression instanceof Coefficient coefficient1){
			return new Coefficient(coefficient * coefficient1.coefficient, coefficient1.expression);
		}

		if (coefficient == 0) return new Constant(0);
		if (coefficient == 1) return expression;


		return new Coefficient(coefficient, expression);
	}

	@Override
	public String toString(){
		if (coefficient == -1) return "-" + expression.toString();
		return coefficient + expression.toString();
	}
}
