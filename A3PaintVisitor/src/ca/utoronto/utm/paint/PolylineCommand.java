package ca.utoronto.utm.paint;
import javafx.scene.canvas.GraphicsContext;

import java.io.PrintWriter;
import java.util.ArrayList;

public class PolylineCommand extends PaintCommand  implements PaintModelVisitable {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	public void add(Point p){ 
		this.points.add(p); 
		this.setChanged();
		this.notifyObservers();
	}
	
	public void replaceLast(Point p){ 
		this.points.remove(this.points.size()-1);
		this.points.add(p); 
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Point> getPoints(){ return this.points; }


	public void accept(PaintModelVisitor v) {
		v.visit(this);
	}
}
