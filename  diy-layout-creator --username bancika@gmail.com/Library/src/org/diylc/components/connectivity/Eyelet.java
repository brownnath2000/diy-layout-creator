package org.diylc.components.connectivity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.diylc.components.AbstractComponent;
import org.diylc.core.ComponentState;
import org.diylc.core.IDIYComponent;
import org.diylc.core.IDrawingObserver;
import org.diylc.core.Project;
import org.diylc.core.VisibilityPolicy;
import org.diylc.core.annotations.ComponentDescriptor;
import org.diylc.core.annotations.EditableProperty;
import org.diylc.core.measures.Size;
import org.diylc.core.measures.SizeUnit;
import org.diylc.utils.Constants;

@ComponentDescriptor(name = "Eyelet", category = "Connectivity", author = "Branislav Stojkovic", description = "Eyelet or turret terminal", instanceNamePrefix = "Eyelet", stretchable = false, zOrder = IDIYComponent.TRACE + 0.1)
public class Eyelet extends AbstractComponent<String> {

	private static final long serialVersionUID = 1L;

	public static Size SIZE = new Size(0.2d, SizeUnit.in);
	public static Size HOLE_SIZE = new Size(0.1d, SizeUnit.in);
	public static Color COLOR = Color.decode("#C3E4ED");

	private Size size = SIZE;
	private Size holeSize = HOLE_SIZE;
	private Color color = COLOR;
	private Point point = new Point(0, 0);
	private String value = "";

	@Override
	public void draw(Graphics2D g2d, ComponentState componentState, boolean outlineMode,
			Project project, IDrawingObserver drawingObserver) {
		if (checkPointsClipped(g2d.getClip())) {
			return;
		}
		int diameter = getClosestOdd((int) size.convertToPixels());
		int holeDiameter = getClosestOdd((int) holeSize.convertToPixels());
		g2d.setColor(color);
		g2d.fillOval(point.x - diameter / 2, point.y - diameter / 2, diameter, diameter);
		g2d.setColor(componentState == ComponentState.SELECTED
				|| componentState == ComponentState.DRAGGING ? SELECTION_COLOR : color.darker());
		g2d.drawOval(point.x - diameter / 2, point.y - diameter / 2, diameter, diameter);
		g2d.setColor(Constants.CANVAS_COLOR);
		g2d.fillOval(point.x - holeDiameter / 2, point.y - holeDiameter / 2, holeDiameter,
				holeDiameter);
		g2d.setColor(componentState == ComponentState.SELECTED
				|| componentState == ComponentState.DRAGGING ? SELECTION_COLOR : color.darker());
		g2d.drawOval(point.x - holeDiameter / 2, point.y - holeDiameter / 2, holeDiameter,
				holeDiameter);
	}

	@Override
	public void drawIcon(Graphics2D g2d, int width, int height) {
		int diameter = getClosestOdd(width / 2);
		int holeDiameter = 5;
		g2d.setColor(COLOR);
		g2d.fillOval((width - diameter) / 2, (height - diameter) / 2, diameter, diameter);
		g2d.setColor(COLOR.darker());
		g2d.drawOval((width - diameter) / 2, (height - diameter) / 2, diameter, diameter);
		g2d.setColor(Constants.CANVAS_COLOR);
		g2d.fillOval((width - holeDiameter) / 2, (height - holeDiameter) / 2, holeDiameter,
				holeDiameter);
		g2d.setColor(COLOR.darker());
		g2d.drawOval((width - holeDiameter) / 2, (height - holeDiameter) / 2, holeDiameter,
				holeDiameter);
	}

	@EditableProperty
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@EditableProperty(name = "Hole size")
	public Size getHoleSize() {
		return holeSize;
	}

	public void setHoleSize(Size holeSize) {
		this.holeSize = holeSize;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public int getControlPointCount() {
		return 1;
	}

	@Override
	public boolean isControlPointSticky(int index) {
		return true;
	}

	@Override
	public VisibilityPolicy getControlPointVisibilityPolicy(int index) {
		return VisibilityPolicy.NEVER;
	}

	@Override
	public Point getControlPoint(int index) {
		return point;
	}

	@Override
	public void setControlPoint(Point point, int index) {
		this.point.setLocation(point);
	}

	@EditableProperty(name = "Color")
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	@EditableProperty
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
}
