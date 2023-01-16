package appl;

public class Bar {
	private final int value;

	public Bar(int value) {
		System.out.println("Instantiating Bar with value " + value);
		this.value = value;
	}

	public void dumpValue() {
		System.out.println(this.value);
	}
}
