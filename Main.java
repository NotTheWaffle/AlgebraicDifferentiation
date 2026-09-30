public class Main {
	public static void main(String[] args) {
		Expression x = Expression.X;
		Expression expr = x.mul(x);

		System.out.println(expr);

		System.out.println("\nd/dx\n");

		System.out.println(expr.derivative());
		System.out.println(expr.derivative().simplified());

		System.out.println("\n        Simplified        \n");

		System.out.println(expr.simplified());

		System.out.println("\nd/dx\n");

		System.out.println(expr.simplified().derivative());
		System.out.println(expr.simplified().derivative().simplified());
	}
}