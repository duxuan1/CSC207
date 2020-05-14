package ca.utoronto.utm.paint;

public interface PaintModelVisitable {
	public void accept(PaintModelVisitor v);
}
