package appl;

import java.util.ArrayList;
import java.util.List;

import util.PerformanceRunner;

public class Application {

	public static void main(String[] args) {

		class Var {
			public int value;
		}

		final int times = 1000;
		final int size = 100000;

		final Customer c1 = new Customer("Nowak");
		final Customer c2 = new Customer("Rueschenpoehler");
		final List<Account> accounts = new ArrayList<>();
		for (int i = 0; i < size; i++)
			accounts.add(new Account(i, i % 2 == 0 ? c1 : c2, i % 2 == 0 ? 100 : 200));
		
		Var sum1 = new Var();
		new PerformanceRunner().run("oldFashion", times, () -> {
			for (Account a : accounts) {
				if (a.getCustomer() == c1) {
					sum1.value += getBalance(a);
				}
			}
		});
		System.out.println(sum1.value);

		Var sum2 = new Var();
		new PerformanceRunner().run("newFashion", times, () -> {
			sum2.value += accounts.stream()
					.filter(a -> a.getCustomer() == c1)
					.map(a -> getBalance(a))
					.reduce(0, (v1, v2) -> v1 + v2);
		});
		System.out.println(sum2.value);
	}
	
	static int getBalance(Account account) {
		// return account.getBalance();
		return Integer.parseInt(String.valueOf(account.getBalance()));
	}
}
