package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawScreenVisitor implements PaintModelVisitor {
	private GraphicsContext g;
	public DrawScreenVisitor(GraphicsContext g) {
		this.g=g;
	}
	
	@Override
	public void visit(RectangleCommand c) {
		Point topLeft = c.getTopLeft();
		Point dimensions = c.getDimensions();
		if(c.isFill()){
			this.g.setFill(c.getColor());
			this.g.fillRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		} else {
			this.g.setStroke(c.getColor());
			this.g.strokeRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		}
	}

	@Override
	public void visit(CircleCommand c) {
		int x = c.getCentre().x;
		int y = c.getCentre().y;
		int radius = c.getRadius();
		if(c.isFill()){
			this.g.setFill(c.getColor());
			this.g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		} else {
			this.g.setStroke(c.getColor());
			this.g.strokeOval(x-radius, y-radius, 2*radius, 2*radius);
		}
	}

	@Override
	public void visit(SquiggleCommand c) {
		ArrayList<Point> points = c.getPoints();
		this.g.setStroke(c.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			this.g.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}			
	}

	@Override
	public void visit(PolylineCommand c) {
		ArrayList<Point> points = c.getPoints();
		this.g.setStroke(c.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			this.g.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}		
	}
}
