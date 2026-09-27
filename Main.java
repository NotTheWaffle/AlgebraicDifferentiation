public class Main {
	public static void main(String[] args) {
		Expression x = Expression.X;
		Expression expr = x.mul(Function.identity.of(x));
		// i should restructure functions IDK
		System.out.println(expr);
		System.out.println(expr.simplified());
		System.out.println("\nd/dx\n");
		System.out.println(expr.derivative());
		System.out.println(expr.derivative().simplified());

	}
}