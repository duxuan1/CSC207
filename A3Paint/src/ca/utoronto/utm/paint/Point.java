package ca.utoronto.utm.paint;

public class Point implements PaintSaveFileSavable {
	int x, y; // Available to our package
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	@Override
	public String getPaintSaveFileString() {
		return "("+this.x+","+this.y+")";
	}
}
