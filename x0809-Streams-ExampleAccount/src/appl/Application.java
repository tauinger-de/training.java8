package appl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static util.Util.mlog;

public class Application {

	public static void main(String[] args) {
		demoSumOfBalancesOldFashion();
		demoSumOfBalancesNewFashion();
		demoPrintAccountsOfCustomerOldFashion();
		demoPrintAccountsOfCustomerNewFashion();
		demoPrintAccountsOfCustomerNewFashionDifficult();
	}

	static final Customer c1 = new Customer("Nowak");
	static final Customer c2 = new Customer("Rueschenpoehler");
	static final List<Account> accounts = new ArrayList<>();
	static {
		accounts.add(new Account(4711, c1, 100));
		accounts.add(new Account(4712, c2, 200));
		accounts.add(new Account(4713, c1, 300));
		accounts.add(new Account(4714, c2, 400));
	}

	static void demoSumOfBalancesOldFashion() {
		mlog();
		int sum = 0;
		for (Account a : accounts) {
			if (a.getCustomer() == c1) {
				sum += a.getBalance();
			}
		}
		System.out.println(sum);
	}

	static void demoSumOfBalancesNewFashion() {
		mlog();
		int sum = accounts.stream()
				.filter(a -> a.getCustomer() == c1)
				.map(a -> a.getBalance())
				.reduce(0, (v1, v2) -> v1 + v2);
		System.out.println(sum);
	}

	static void demoPrintAccountsOfCustomerOldFashion()	{
		mlog();
		for (int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			if (a.getCustomer() == c1) {
				System.out.println(a);
			}
		}
	}

	static void demoPrintAccountsOfCustomerNewFashion() {
		mlog();
		accounts.stream()
			.filter(a -> a.getCustomer() == c1)
			.forEach(System.out::println);
	}

	static void demoPrintAccountsOfCustomerNewFashionDifficult() {
		mlog();
		IntStream.range(0, accounts.size())
			.mapToObj(i -> accounts.get(i))
			.filter(a -> a.getCustomer() == c1)
			.forEach(a -> System.out.println(a));
	}
}



