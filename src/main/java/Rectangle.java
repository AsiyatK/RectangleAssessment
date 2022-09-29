
public class Rectangle {

	private Point topLeft;
	private Point topRight;
	private Point bottomLeft;
	private Point bottomRight;
	private int width;
	private int height;

	/**
	 * Constructor.
	 * 
	 * @param bottomLeft
	 * @param width
	 * @param height
	 */
	public Rectangle(Point bottomLeft, int width, int height) {
		this.bottomLeft = bottomLeft;
		this.width = width;
		this.height = height;

		// create all corner points based on the given bottomLeft, width and height
		topLeft = new Point(bottomLeft.getX(), bottomLeft.getY() + height);
		topRight = new Point(bottomLeft.getX() + width, bottomLeft.getY() + height);
		bottomRight = new Point(bottomLeft.getX() + width, bottomLeft.getY());
	}

	@Override
	public String toString() {
		return "Rectangle{" + "topLeft=" + topLeft + ", topRight=" + topRight + ", bottomLeft=" + bottomLeft
				+ ", bottomRight=" + bottomRight + ", width=" + width + ", height=" + height + '}';
	}

	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point topLeft) {
		this.topLeft = topLeft;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns True, if the given Rectangles are adjacent.
	 * 
	 * @param a Rectangle a
	 * @param b Rectangle b
	 * @return boolean
	 */
	public static boolean isAdjacent(Rectangle a, Rectangle b) {
		if (a.isAdjacent(b)) {
			return true;
		} else if (b.isAdjacent(a)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Returns True, if the given Rectangles is adjacent to the current Rectangle.
	 * 
	 * @param b The other Rectangle
	 * @return boolean
	 */
	private boolean isAdjacent(Rectangle b) {
		if (isAdjacentProperLeftAndRightSide(b)) {
			return true;
		} else if (isAdjacentProperTopAndBottomSide(b)) {
			return true;
		} else if (isAdjacentOnLeftOrRigthSide(b)) {
			return true;
		} else if (isAdjacentOnTopOrBottomSide(b)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns True, if the given Rectangles is adjacent to the current Rectangle,
	 * side by side, properly with same height.
	 * 
	 * @param b The other Rectangle
	 * @return boolean
	 */
	private boolean isAdjacentProperLeftAndRightSide(Rectangle b) {
		boolean adjacenyProperLeftAndRight =
				// y same
				// aBR.x = bBL.x
				(this.bottomRight.getX() == b.bottomLeft.getX()) && //
				// aBR.y = bBL.y &&
				// aTR.y = bTL.y
						this.bottomRight.getY() == b.bottomLeft.getY() && this.topRight.getY() == b.topLeft.getY();
		return adjacenyProperLeftAndRight;
	}

	/**
	 * Returns True, if the given Rectangles is adjacent to the current Rectangle,
	 * one over the other, properly with same width.
	 * 
	 * @param b The other Rectangle
	 * @return boolean
	 */
	private boolean isAdjacentProperTopAndBottomSide(Rectangle b) {
		// a is TOP of b
		// b is BOTTOM of a

		boolean adjacenyProperTopAndBottom =
				// y same
				// aBL.y = bTL.y
				(this.topLeft.getY() == b.bottomLeft.getY()) && //
				// aBL.x = bTL.x &&
				// aBR.x = bTR.x
						this.bottomLeft.getX() == b.topLeft.getX() && this.bottomRight.getX() == b.topRight.getX();

		return adjacenyProperTopAndBottom;
	}

	/**
	 * Returns True, if the given Rectangles is adjacent to the current Rectangle,
	 * one over the other, partial or sub adjacent.
	 * 
	 * @param b The other Rectangle
	 * @return boolean
	 */
	private boolean isAdjacentOnTopOrBottomSide(Rectangle b) {
		// a is BOTTOM of b
		// b is TOP of a

		// 1.
		// B is RIGHTwards

		boolean adjacenyTopRightwards =
				// y same
				// aTL.y = bBL.y
				(this.topLeft.getY() == b.bottomLeft.getY()) && //
				// bBL.x >= aTL.x &&
				// bBL.x < aTR.x
						(b.bottomLeft.getX() >= this.topLeft.getX() && b.bottomLeft.getX() < this.topRight.getX());
		if (adjacenyTopRightwards) {
			return true;
		}

		// 2.
		// B is LEFTwards
		boolean adjacenyTopLeftwards =
				// y same
				// aTL.y = bBL.y
				(this.topLeft.getY() == b.bottomLeft.getY()) && //
				// bBR.x <= aTR.x &&
				// bBR.x > aTL.x
						b.bottomRight.getX() <= this.topRight.getX() && //
						b.bottomRight.getX() > this.topLeft.getX();
		if (adjacenyTopLeftwards) {
			return true;
		}

		return false;
	}

	/**
	 * Returns True, if the given Rectangles is adjacent to the current Rectangle,
	 * side by side, partial or sub adjacent.
	 * 
	 * @param b The other Rectangle
	 * @return boolean
	 */
	private boolean isAdjacentOnLeftOrRigthSide(Rectangle b) {
		// a is left of b
		// b is right of a

		// 1.
		// B is upwards

		boolean adjacenyRightUpwards =
				// aBR.x = bBL.x
				(this.bottomRight.getX() == b.bottomLeft.getX()) && //
				// bBL.y >= aBR.y &&
				// bBL.y < aTR.y
						(b.bottomLeft.getY() >= this.bottomRight.getY() && b.bottomLeft.getY() < this.topRight.getY());
		if (adjacenyRightUpwards) {
			return true;
		}

		// 2.
		// B is downwards

		boolean adjacenyRightDownwards =
				// aBR.x = bBL.x
				(this.bottomRight.getX() == b.bottomLeft.getX()) && //
				// bTL.y <= aTR.y &&
				// bTL.y > aBR.y
						(b.topLeft.getY() <= this.topRight.getY() && b.topLeft.getY() > this.bottomRight.getY());

		if (adjacenyRightDownwards) {
			return true;
		}

		return false;
	}

	/**
	 * Returns True, if the given Rectangles is contained within the current
	 * Rectangle.
	 * 
	 * @param b The other Rectangle
	 * @return boolean
	 */
	public boolean isContained(Rectangle b) {
		// is TopLeft InsideRectangle
		boolean topLeftInside = isPointInsideRectangle(this, b.topLeft);
		// is BottomRight InsideRectangle
		boolean bottomRightInside = isPointInsideRectangle(this, b.bottomRight);
		// is BottomLeft InsideRectangle
		boolean bottomLeftInside = isPointInsideRectangle(this, b.bottomLeft);
		// is TopRight InsideRectangle
		boolean topRightInside = isPointInsideRectangle(this, b.topRight);
		//
		return topLeftInside && topRightInside && bottomLeftInside && bottomRightInside;

	}

	/**
	 * Find intersection by comparing the current Rectangle with the given rectangle
	 * b. Returns intersection points if any. If no intersection, returns null.
	 * 
	 * @param a
	 * @param b
	 * @return Point[] The intersection points if any. If no intersection, returns
	 *         null.
	 */
	public static Point[] getIntersectionPoints(Rectangle a, Rectangle b) {
		Point[] points;
		points = a.getIntersectionPoints(b);
		if (points == null) {
			points = b.getIntersectionPoints(a);
		}
		return points;

	}

	/**
	 * Find intersection by comparing the given rectangle with the current
	 * Rectangle. Returns intersection points if any. If no intersection, returns
	 * null.
	 * 
	 * @param a
	 * @param b
	 * @return Point[] The intersection points if any. If no intersection, returns
	 *         null.
	 */
	public Point[] getIntersectionPoints(Rectangle b) {
		// If Contained, no intersections.
		if (isContained(b)) {
			return null;
		}

		// 1. Given rectangle's top Left corner is INSIDE, extending diagonally down
		// wards.

		// is TopLeft InsideRectangle
		boolean topLeftInside = isPointInsideRectangle(this, b.topLeft);
		// is BottomRight InsideRectangle
		boolean bottomRightInside = isPointInsideRectangle(this, b.bottomRight);

		// the Rect B is intersecting on bottom right side
		if (topLeftInside && !bottomRightInside) {

			Point bottomIntersection = new Point(bottomRight.getX(), b.bottomLeft.getY());
			Point topIntersection = new Point(bottomRight.getX(), b.topLeft.getY());

			Point[] points = new Point[2];

			points[0] = bottomIntersection;
			points[1] = topIntersection;

			return points;
		}

		// 2. Given rectangle's Bottom Left AND Top Left INSIDE, extending rightwards

		// is BottomLeft InsideRectangle
		boolean bottomLeftInside = isPointInsideRectangle(this, b.bottomLeft);
		if (topLeftInside && bottomLeftInside) {

			Point bottomIntersection = new Point(bottomRight.getX(), b.bottomLeft.getY());
			Point topIntersection = new Point(bottomRight.getX(), b.topLeft.getY());

			Point[] points = new Point[2];

			points[0] = bottomIntersection;
			points[1] = topIntersection;

			return points;
		}

		return null;
	}

	/**
	 * Returns true, if the given point is within the boundary of the given
	 * Rectangle.
	 * 
	 * @param rectangle
	 * @param point
	 * @return
	 */
	private boolean isPointInsideRectangle(Rectangle rectangle, Point point) {
		if (point.getX() > rectangle.bottomLeft.getX() //
				&& point.getY() > rectangle.bottomLeft.getY() //
				&& point.getX() < rectangle.bottomLeft.getX() + width//
				&& point.getY() < rectangle.bottomLeft.getY() + height//
		) {
			return true;
		}
		return false;
	}
}
