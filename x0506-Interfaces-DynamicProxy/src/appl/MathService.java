package appl;

public interface MathService {
	public abstract double sum(double x, double y);
	public default double diff(double x, double y) {
		return this.sum(x, -y);
	}
}
