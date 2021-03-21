package ca.utoronto.utm.paint;

public interface PaintModelVisitor {
	public void visit(RectangleCommand c);
	public void visit(CircleCommand c);
	public void visit(SquiggleCommand c);
	public void visit(PolylineCommand c);
}
