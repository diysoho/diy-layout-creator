package org.diylc.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.diylc.core.ComponentState;
import org.diylc.core.IDIYComponent;
import org.diylc.core.IDrawingObserver;
import org.diylc.core.Project;
import org.diylc.core.Theme;
import org.diylc.core.VisibilityPolicy;
import org.diylc.core.annotations.ComponentDescriptor;
import org.diylc.core.annotations.EditableProperty;
import org.diylc.core.measures.Capacitance;
import org.diylc.core.measures.CapacitanceUnit;
import org.diylc.core.measures.Resistance;
import org.diylc.core.measures.ResistanceUnit;
import org.diylc.core.measures.Size;
import org.diylc.core.measures.SizeUnit;

@ComponentDescriptor(name = "Mock", author = "bancika", category = "Sample", instanceNamePrefix = "M", description = "test", zOrder = IDIYComponent.COMPONENT)
public class MockDIYComponent implements IDIYComponent<Capacitance> {

	private static final long serialVersionUID = 1L;

	private String testField = "Hello World";
	private Capacitance c = new Capacitance(100d, CapacitanceUnit.uF);
	private Resistance r = new Resistance(123d, ResistanceUnit.K);
	private Size s = new Size(1d, SizeUnit.cm);
	private Color color = Color.green;
	private Point leftTopCorner = new Point(0, 0);
	private String name = "something";

	public MockDIYComponent(String testField, Capacitance c, Resistance r, Size s, Color color,
			Point leftTopCorner) {
		super();
		this.testField = testField;
		this.c = c;
		this.r = r;
		this.s = s;
		this.color = color;
		this.leftTopCorner = leftTopCorner;
	}

	public MockDIYComponent() {
		super();
	}

	@Override
	public boolean canControlPointOverlap(int index) {
		return false;
	}

	@EditableProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getControlPointCount() {
		return 1;
	}

	@Override
	public boolean isControlPointSticky(int index) {
		return false;
	}

	@Override
	public VisibilityPolicy getControlPointVisibilityPolicy(int index) {
		return VisibilityPolicy.WHEN_SELECTED;
	}

	@Override
	public Point getControlPoint(int index) {
		return leftTopCorner;
	}

	@Override
	public void setControlPoint(Point point, int index) {
		leftTopCorner.setLocation(point);
	}

	@EditableProperty(name = "Test Field")
	public String getTestField() {
		return testField;
	}

	public void setTestField(String testField) {
		this.testField = testField;
	}

	@EditableProperty(defaultable = true)
	public Capacitance getValue() {
		return c;
	}

	public void setValue(Capacitance c) {
		this.c = c;
	}

	@EditableProperty(defaultable = true)
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@EditableProperty
	public Resistance getR() {
		return r;
	}

	// Setter is commented out, ClassProcessor should ignore this property.
	// public void setR(Resistance r) {
	// this.r = r;
	// }

	// No annotation, so this property should be ignored.
	public Size getS() {
		return s;
	}

	public void setS(Size s) {
		this.s = s;
	}

	@Override
	public void draw(Graphics2D g2d, ComponentState componentState, Project project,
			IDrawingObserver drawingObserver) {
		g2d.setColor(componentState.equals(ComponentState.SELECTED) ? color : color.darker());
		g2d.fillRect(leftTopCorner.x, leftTopCorner.y, 200, 50);
	}

	@Override
	public void drawIcon(Graphics2D g2d, int width, int height) {
		g2d.drawString("X", 10, 10);
	}
}
