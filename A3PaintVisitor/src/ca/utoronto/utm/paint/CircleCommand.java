package ca.utoronto.utm.paint;
import java.io.PrintWriter;

import javafx.scene.canvas.GraphicsContext;

public class CircleCommand extends PaintCommand implements PaintModelVisitable {
	private Point centre;
	private int radius;
	
	public CircleCommand(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
	}
	public Point getCentre() { return centre; }
	public void setCentre(Point centre) { 
		this.centre = centre; 
		this.setChanged();
		this.notifyObservers();
	}
	public int getRadius() { return radius; }
	public void setRadius(int radius) { 
		this.radius = radius; 
		this.setChanged();
		this.notifyObservers();
	}
	
	
	public void accept(PaintModelVisitor v) {
		v.visit(this);
	}
}
