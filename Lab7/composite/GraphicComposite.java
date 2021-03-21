package designpatterns.composite;

import java.util.ArrayList;

//a composite graphic component contains other simple or composite components
public class GraphicComposite implements GraphicComponent {
	
	private ArrayList<GraphicComponent> children = new ArrayList<GraphicComponent>();
	private String name = "";
	
	public GraphicComposite(String name) {
		this.name = name;
	}

	@Override
	public void paint() {
		System.out.println(this.name + ": composite component");
		for (GraphicComponent c: this.children) {
			c.paint();
		}
	}
	
	public void add(GraphicComponent c) {
		this.children.add(c);
	}
}
