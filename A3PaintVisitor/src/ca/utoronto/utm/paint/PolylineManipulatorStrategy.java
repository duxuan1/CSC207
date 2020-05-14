package ca.utoronto.utm.paint;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

class PolylineManipulatorStrategy extends ShapeManipulatorStrategy {
	PolylineManipulatorStrategy(PaintModel paintModel) {
		super(paintModel);
	}

	private PolylineCommand polylineCommand;
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.polylineCommand!=null) {
			this.polylineCommand.replaceLast(new Point((int)e.getX(), (int)e.getY()));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getButton()==MouseButton.PRIMARY && this.polylineCommand==null) {
				// new polyline
				this.polylineCommand=new PolylineCommand();
				this.addCommand(this.polylineCommand);
				this.polylineCommand.add(new Point((int)e.getX(), (int)e.getY()));
				this.polylineCommand.add(new Point((int)e.getX(), (int)e.getY()));
			} else if(e.getButton()==MouseButton.PRIMARY) {
				// new point for existing polyline
				this.polylineCommand.replaceLast(new Point((int)e.getX(), (int)e.getY()));
				this.polylineCommand.add(new Point((int)e.getX(), (int)e.getY()));
			} else if(e.getButton()==MouseButton.SECONDARY && this.polylineCommand!=null) {
				// Final click on polyline
				this.polylineCommand.replaceLast(new Point((int)e.getX(), (int)e.getY()));
				this.polylineCommand=null;
			}
	}
}
