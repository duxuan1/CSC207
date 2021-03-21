package ca.utoronto.utm.paint;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.paint.Color;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author 
 *
 */
public class PaintFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private PaintModel paintModel; 
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$");

	private String pointRegex="\\((-?\\d+),(-?\\d+)\\)";

	
	private Pattern pColor=Pattern.compile("^color:(\\d+),(\\d+),(\\d+)$");
	private Pattern pFilled=Pattern.compile("^filled:(true|false)$");	
	
	private Pattern pCircleStart=Pattern.compile("^Circle$");
	private Pattern pCircleCenter=Pattern.compile("^center:"+pointRegex+"$");
	private Pattern pCircleRadius=Pattern.compile("^radius:(\\d+)$");
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$");
	
	private Pattern pRectangleStart=Pattern.compile("^Rectangle$");
	private Pattern pRectangleP1=Pattern.compile("^p1:"+pointRegex+"$");
	private Pattern pRectangleP2=Pattern.compile("^p2:"+pointRegex+"$");
	private Pattern pRectangleEnd=Pattern.compile("^EndRectangle$");
	
	private Pattern pSquiggleStart=Pattern.compile("^Squiggle$");
	private Pattern pSquigglePoints=Pattern.compile("^points$");
	private Pattern pSquigglePoint=Pattern.compile("^point:"+pointRegex+"$");
	private Pattern pSquiggleEndPoints=Pattern.compile("^endpoints$");
	private Pattern pSquiggleEnd=Pattern.compile("^EndSquiggle$");
	
	private Pattern pPolylineStart=Pattern.compile("^Polyline$");
	private Pattern pPolylinePoints=Pattern.compile("^points$");
	private Pattern pPolylinePoint=Pattern.compile("^point:"+pointRegex+"$");
	private Pattern pPolylineEndPoints=Pattern.compile("^endpoints$");
	private Pattern pPolylineEnd=Pattern.compile("^EndPolyline$");
	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	/**
	 * Parse color:r,g,b from param l
	 * @param l 
	 * @return the parsed color, or null if unable to parse
	 */
	private Color parseColor(String l) {
		Color color=null;
		Matcher m=pColor.matcher(l);
		if(m.matches()) {
			int r = Integer.parseInt(m.group(1));
			int g = Integer.parseInt(m.group(2));
			int b = Integer.parseInt(m.group(3));
			if(0<=r &&  r<=255 && 0<=g && g<=255 && 0<=b && b<=255) {
				color = Color.rgb(r, g, b);
			}
		}
		return color;
	}
	
	/**
	 * Parse point from pointPattern from param l
	 * @param label
	 * @param l 
	 * @return the parsed Point or null if unable to parse
	 */
	private Point parsePoint(Pattern pointPattern, String l) {
		Point p=null;
		Matcher m=pointPattern.matcher(l);
		if(m.matches()) {
			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			p=new Point(x,y);
		}
		return p;
	}
	/**
	 * Parse filled:true|false from param l
	 * @param l 
	 * @return the filled value, or null if unable to parse
	 */
	private Boolean parseFilled(String l) {
		Boolean isFilled=null;
		Matcher m=pFilled.matcher(l);
		if(m.matches()) {
			if("true".equals(m.group(1)))isFilled=true;
			if("false".equals(m.group(1)))isFilled=false;
		}
		return isFilled;
	}
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @param paintModel the paint model to add the commands to
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream, PaintModel paintModel) {
		this.paintModel = paintModel;
		this.errorMessage="";
		
		// During the parse, we will be building one of the 
		// following commands. As we parse the file, we modify 
		// the appropriate command.
		
		CircleCommand circleCommand = null; 
		RectangleCommand rectangleCommand = null;
		SquiggleCommand squiggleCommand = null;
		PolylineCommand polylineCommand = null;
		Color color; Boolean isFilled; Point p;
	
		try {	
			int state=0; Matcher m; String l;
			
			this.lineNumber=0;
			while ((l = inputStream.readLine()) != null) {
				l=l.replaceAll("\\s+","");
				this.lineNumber++;
				System.out.println(lineNumber+" "+l+" "+state);
				if("".contentEquals(l))continue; // skip blank lines
				switch(state){
					case 0:
						m=pFileStart.matcher(l);
						if(m.matches()){
							state=1;
							break;
						}
						error("Expected Start of Paint Save File");
						return false;
					case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);
						if(m.matches()){
							circleCommand = new CircleCommand(new Point(0,0),1);
							state=10; 
							break;
						}
						m=pRectangleStart.matcher(l);
						if(m.matches()){
							rectangleCommand = new RectangleCommand(new Point(0,0), new Point(1,1));
							state=20; 
							break;
						}
						m=pSquiggleStart.matcher(l);
						if(m.matches()){
							squiggleCommand = new SquiggleCommand();
							state=30; 
							break;
						}
						m=pPolylineStart.matcher(l);
						if(m.matches()){
							polylineCommand = new PolylineCommand();
							state=40; 
							break;
						}
						m=pFileEnd.matcher(l);
						if(m.matches()){
							state=999; 
							break;
						}
						error("Expected Start of Shape or End Paint Save File");
						return false;
					case 10: // Circle color
						color = parseColor(l);
						if(color!=null) {
							circleCommand.setColor(color);
							state=11;
							break;	
						}
						error("Expected Circle color");
						return false;
					case 11: // Circle is filled?
						isFilled = parseFilled(l);
						if(isFilled!=null) {
							circleCommand.setFill(isFilled);
							state=12;
							break;	
						}
						error("Expected Circle filled");
						return false;
					case 12: // Circle center
						p = parsePoint(this.pCircleCenter, l);
						if(p!=null) {
							circleCommand.setCentre(p);
							state=13;
							break;	
						}
						error("Expected Circle center");
						return false;
					case 13: // Circle Radius
						m=pCircleRadius.matcher(l);
						if(m.matches()){
							int radius = Integer.parseInt(m.group(1));
							circleCommand.setRadius(radius);
							state=14; 
							break;
						}
						error("Expected Circle Radius");
						return false;
					case 14: // End Circle
						m=pCircleEnd.matcher(l);
						if(m.matches()){
							this.paintModel.addCommand(circleCommand);
							circleCommand = null;
							state=1; 
							break;
						}
						error("Expected End Circle");
						return false;
					case 20: // Rectangle color
						color = parseColor(l);
						if(color!=null) {
							rectangleCommand.setColor(color);
							state=21;
							break;	
						}
						error("Expected Rectangle color");
						return false;
					case 21: // Rectangle is filled?
						isFilled = parseFilled(l);
						if(isFilled!=null) {
							rectangleCommand.setFill(isFilled);
							state=22;
							break;	
						}
						error("Expected Rectangle filled");
						return false;
					case 22: // Rectangle p1
						p = parsePoint(this.pRectangleP1, l);
						if(p!=null) {
							rectangleCommand.setP1(p);
							state=23;
							break;	
						}
						error("Expected Rectangle p1");
						return false;
					case 23: // Rectangle p2
						p = parsePoint(this.pRectangleP2, l);
						if(p!=null) {
							rectangleCommand.setP2(p);
							state=24;
							break;	
						}
						error("Expected Rectangle p2");
						return false;
					case 24: // End Rectangle
						m=pRectangleEnd.matcher(l);
						if(m.matches()){
							this.paintModel.addCommand(rectangleCommand);
							rectangleCommand = null;
							state=1; 
							break;
						}
						error("Expected End Rectangle");
						return false;
					case 30: // Squiggle color
						color = parseColor(l);
						if(color!=null) {
							squiggleCommand.setColor(color);
							state=31;
							break;	
						}
						error("Expected Squiggle color");
						return false;
					case 31: // Squiggle is filled?
						isFilled = parseFilled(l);
						if(isFilled!=null) {
							squiggleCommand.setFill(isFilled);
							state=32;
							break;	
						}
						error("Expected Squiggle filled");
						return false;
					case 32: // Squiggle points
						m=pSquigglePoints.matcher(l);
						if(m.matches()){
							state=33; 
							break;
						}
						error("Expected Squiggle points");
						return false;
					case 33: // Squiggle point or end points
						p = parsePoint(this.pSquigglePoint, l);
						if(p!=null) {
							squiggleCommand.add(p);
							state=33;
							break;	
						}
						m=pSquiggleEndPoints.matcher(l);
						if(m.matches()){
							state=34; 
							break;
						}
						error("Expected Squiggle point or end points");
						return false;
					case 34: // End Squiggle
						m=pSquiggleEnd.matcher(l);
						if(m.matches()){
							this.paintModel.addCommand(squiggleCommand);
							squiggleCommand = null;
							state=1; 
							break;
						}
						error("Expected End Squiggle");
						return false;
					case 40: // Polyline color
						color = parseColor(l);
						if(color!=null) {
							polylineCommand.setColor(color);
							state=41;
							break;	
						}
						error("Expected Polyline color");
						return false;
					case 41: // Polyline is filled?
						isFilled = parseFilled(l);
						if(isFilled!=null) {
							polylineCommand.setFill(isFilled);
							state=42;
							break;	
						}
						error("Expected Polyline filled");
						return false;
					case 42: // Polyline points
						m=pPolylinePoints.matcher(l);
						if(m.matches()){
							state=43; 
							break;
						}
						error("Expected Polyline points");
						return false;
					case 43: // Polyline point or end points
						p = parsePoint(this.pPolylinePoint, l);
						if(p!=null) {
							polylineCommand.add(p);
							state=43;
							break;	
						}
						m=pPolylineEndPoints.matcher(l);
						if(m.matches()){
							state=44; 
							break;
						}
						error("Expected Polyline point or end points");
						return false;
					case 44: // End Polyline
						m=pPolylineEnd.matcher(l);
						if(m.matches()){
							this.paintModel.addCommand(polylineCommand);
							polylineCommand = null;
							state=1; 
							break;
						}
						error("Expected End Polyline");
						return false;
					// ...
					case 999: // Should all be empty lines, if not error
						error("Extra content after End of File");
						return false;
				}
			}
			if(state==999) {
				return true;
			} else {
				error("Unexpected end of file");
				return false;
			}
		}  catch (Exception e){
			
		}
		return false;
	}
}
