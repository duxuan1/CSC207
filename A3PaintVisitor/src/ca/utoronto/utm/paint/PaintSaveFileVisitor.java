package ca.utoronto.utm.paint;

/**
 * Accumulate the string representing the PaintSaveFile format of the model 
 * @author arnold
 *
 */
public class PaintSaveFileVisitor implements PaintModelVisitor {
	private String accumulator=""; // representing the model visited so far;
	
	public String getPaintSaveFileString() {
		return "Paint Save File Version 1.0\n"+
				this.accumulator +
				"End Paint Save File";
	}
	
	/**
	 * Get the string corresponding to a Point
	 * @param p
	 * @return
	 */
	private String getPaintSaveFileString(Point p) {
		return "("+p.x+","+p.y+")";
	}
	/**
	 * Return the common parts of each command (color and filled
	 * @param c
	 * @return
	 */
	private String getPaintSaveFileString(PaintCommand c) {
		int r = (int)Math.round(255*c.getColor().getRed());
		int g = (int)Math.round(255*c.getColor().getGreen());
		int b = (int)Math.round(255*c.getColor().getBlue());
		
		String s = "";
		s+="\tcolor:"+r+","+g+","+b+"\n";
		s+="\tfilled:"+c.isFill()+"\n";
		return s;
	}

	
	@Override
	public void visit(RectangleCommand c) {
			accumulator+="Rectangle\n";			
			accumulator+=this.getPaintSaveFileString(c);
			accumulator+="\tp1:"+this.getPaintSaveFileString(c.getP1())+"\n";
			accumulator+="\tp2:"+this.getPaintSaveFileString(c.getP2())+"\n";
			accumulator+="End Rectangle\n";
	}

	@Override
	public void visit(CircleCommand c) {
			accumulator+="Circle\n";
			accumulator+=this.getPaintSaveFileString(c);
			accumulator+="\tcenter:"+this.getPaintSaveFileString(c.getCentre())+"\n";
			accumulator+="\tradius:"+c.getRadius()+"\n";
			accumulator+="End Circle\n";
	}

	@Override
	public void visit(SquiggleCommand c) {
		accumulator+="Squiggle\n";
		accumulator+=this.getPaintSaveFileString(c);
		accumulator+="\tpoints\n";
		for(Point p: c.getPoints()){
			accumulator+="\t\tpoint:"+this.getPaintSaveFileString(p)+"\n";
		}
		accumulator+="\tend points\n";
		accumulator+="End Squiggle\n";
	}

	@Override
	public void visit(PolylineCommand c) {
		accumulator+="Polyline\n";
		accumulator+=this.getPaintSaveFileString(c);
		accumulator+="\tpoints\n";
		for(Point p: c.getPoints()){
			accumulator+="\t\tpoint:"+this.getPaintSaveFileString(p)+"\n";
		}
		accumulator+="\tend points\n";
		accumulator+="End Polyline\n";
	}
}
