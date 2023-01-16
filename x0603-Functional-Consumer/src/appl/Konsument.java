package appl;

@FunctionalInterface
//interface Konsument {
//	public abstract void accept(int v);
//	public default Konsument andThen(Konsument after) {
//		return new Konsument() {
//			public void accept(int v) {
//				Konsument.this.accept(v);
//				after.accept(v);
//			}
//		};
//	}
//}
interface Konsument {
	public abstract void accept(int v);
	public default Konsument andThen(Konsument after) {
		return v -> { this.accept(v); after.accept(v); };
	}
}
