package ex3;

public interface AccountService {

    // default Methoden neu in Java 8
    default Account createAccount(String name) {
        return of(name);
    }

    // statische Methoden mit Body -- neu in Java 8
    static Account of(String firstName, String lastName, int balance) {
        String fullName = lastName + ", " + firstName;
        return of(firstName);
    }

    static Account of(String fullName) {
        return new Account(fullName);
    }

    void deleteAccount(Account account);

    class Account {
        private final String name;

        public Account(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Account{" + "name='" + name + '\'' + '}';
        }
    }

}
