import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RectangleTest {

	@Test
	void testIsAdjacentProperLeftAndRightSide() {
		// a is left of b
		// b is right of a

		Rectangle a = new Rectangle(new Point(1, 5), 10, 5);

		Rectangle b = new Rectangle(new Point(11, 5), 5, 5);

		assertTrue(Rectangle.isAdjacent(b, a));
	}

	@Test
	void testIsAdjacentProperTopAndBottomSide() {
		// a is TOP of b
		// b is BOTTOM of a

		Rectangle a = new Rectangle(new Point(1, 5), 10, 5);

		Rectangle b = new Rectangle(new Point(1, 10), 5, 5);

		assertTrue(Rectangle.isAdjacent(b, a));
	}

	@Test
	void testIsAdjacentOnTopOrBottomSide_adjacency_NO() {
		// a is BOTTOM of b
		// b is TOP of a

		// 1.
		// Top side partial adjacency. B is LEFTwards

		// (1,10), (10,10), (1,15), (20,5), (20,10), (15,15), (15,10), (10,5)
		Rectangle a = new Rectangle(new Point(10, 5), 10, 5);

		Rectangle b = new Rectangle(new Point(1, 10), 5, 5);

		assertFalse(Rectangle.isAdjacent(a, b));
	}

	@Test
	void testIsAdjacentOnTopOrBottomSide_rightSideAdjacencyLeftwards() {
		// a is BOTTOM of b
		// b is TOP of a

		// 1.
		// Top side partial adjacency. B is LEFTwards

		// (1,10), (10,10), (1,15), (20,5), (20,10), (15,15), (15,10), (10,5)
		Rectangle a = new Rectangle(new Point(10, 5), 10, 5);

		Rectangle b = new Rectangle(new Point(1, 10), 14, 5);

		assertTrue(Rectangle.isAdjacent(a, b));
	}

	@Test
	void testIsAdjacentOnTopOrBottomSide_rightSideAdjacencyRightwards() {
		// a is BOTTOM of b
		// b is TOP of a

		// 1.
		// Top side partial adjacency. B is RIGHTwards

		// (2,5), (10,10), (2,10.00), (20.00,10), (20.00,15), (15,10), (15,5), (10,15)
		Rectangle a = new Rectangle(new Point(2, 5), 13, 5);

		Rectangle b = new Rectangle(new Point(10, 10), 10, 5);

		assertTrue(Rectangle.isAdjacent(a, b));
	}

	@Test
	void testIsAdjacentOnLeftOrRigthSide_rightSideAdjacencyUpwards() {

		// a is left of b
		// b is right of a

		// 1.
		// Right side partial adjacency. B is upwards

		// (5.00,5.00), (10.00,5.00), (5.00,15.00), (20.00,8.00), (20.00,20.00),
		// (10.00,8.00), (10.00,15.00), (10.00,20.00)
		Rectangle a = new Rectangle(new Point(5, 5), 5, 12);

		Rectangle b = new Rectangle(new Point(10, 8), 10, 10);

		assertTrue(Rectangle.isAdjacent(a, b));

	}

	@Test
	void testIsAdjacentOnLeftOrRigthSide_rightSideAdjacencyDownwards() {

		// a is left of b
		// b is right of a

		// 1.
		// Right side partial adjacency. B is downwards

		// (5.00,5.00), (10.00,5.00), (5.00,15.00), (20.00,8.00), (20.00,2.00),
		// (10.00,8.00), (10.00,15.00), (10.00,2.00)
		Rectangle a = new Rectangle(new Point(5, 5), 5, 10);

		Rectangle b = new Rectangle(new Point(10, 2), 10, 6);

		assertTrue(Rectangle.isAdjacent(a, b));

	}

	@Test
	void testIsContained_true() {
		// (5.00,5.00), (20.00,5.00), (5.00,20.00), (20.00,20.00),
		// (8,7),(15,7),(8,15),(15,15)

		Rectangle a = new Rectangle(new Point(5, 5), 15, 15);

		Rectangle b = new Rectangle(new Point(8, 7), 7, 8);

		assertTrue(a.isContained(b));
	}

	@Test
	void testIsContained_false() {

		Rectangle a = new Rectangle(new Point(5, 5), 15, 15);

		Rectangle b = new Rectangle(new Point(8, 7), 20, 8);

		assertFalse(a.isContained(b));
	}

	@Test
	void testGetIntersectionPoints_topLeftInside_or_bottomRightInside() {
		// (5.00,5.00), (15.00,5.00), (5.00,20.00), (15.00,20.00),
		// (8,7),(17,7),(8,3),(17,3)
		Rectangle a = new Rectangle(new Point(5, 5), 10, 15);

		Rectangle b = new Rectangle(new Point(8, 3), 11, 7);

//		Point{x=15, y=3}
//		Point{x=15, y=10}
		Point[] ipoints = Rectangle.getIntersectionPoints(a, b);
		assertTrue(ipoints[0].getX() == 15);
		assertTrue(ipoints[0].getY() == 3);

		assertTrue(ipoints[1].getX() == 15);
		assertTrue(ipoints[1].getY() == 10);
	}

	@Test
	void testGetIntersectionPoints_topLeftInside() {
		// (5.00,5.00), (15.00,5.00), (5.00,20.00), (15.00,20.00),
		// (8,7),(17,7),(8,3),(17,3)
		Rectangle a = new Rectangle(new Point(5, 5), 10, 15);

		Rectangle b = new Rectangle(new Point(8, 3), 11, 7);

//		Point{x=15, y=3}
//		Point{x=15, y=10}
		Point[] ipoints = a.getIntersectionPoints(b);
		assertTrue(ipoints[0].getX() == 15);
		assertTrue(ipoints[0].getY() == 3);

		assertTrue(ipoints[1].getX() == 15);
		assertTrue(ipoints[1].getY() == 10);
	}

	@Test
	void testGetIntersectionPoints_bottomLeftAndTopLeftInside() {
		// (5.00,5.00), (15.00,5.00), (5.00,20.00), (15.00,20.00),
		// (8,7),(20,7),(8,15),(20,15)

		Rectangle a = new Rectangle(new Point(5, 5), 10, 15);

		Rectangle b = new Rectangle(new Point(8, 7), 12, 8);

//		Point{x=15, y=7}
//		Point{x=15, y=15}
		Point[] ipoints = a.getIntersectionPoints(b);
		assertTrue(ipoints[0].getX() == 15);
		assertTrue(ipoints[0].getY() == 7);

		assertTrue(ipoints[1].getX() == 15);
		assertTrue(ipoints[1].getY() == 15);
	}

	@Test
	void testGetIntersectionPoints_noIntersection_notContained() {
		// (5.00,5.00), (20.00,5.00), (5.00,20.00), (20.00,20.00),
		// (8,7),(15,7),(8,15),(15,15)

		Rectangle a = new Rectangle(new Point(5, 5), 10, 10);

		Rectangle b = new Rectangle(new Point(20, 5), 10, 10);

		Point[] ipoints = a.getIntersectionPoints(b);
		assertNull(ipoints);
	}

	@Test
	void testGetIntersectionPoints_noIntersection_contained() {
		// (5.00,5.00), (20.00,5.00), (5.00,20.00), (20.00,20.00),
		// (8,7),(15,7),(8,15),(15,15)

		Rectangle a = new Rectangle(new Point(5, 5), 15, 15);

		Rectangle b = new Rectangle(new Point(8, 7), 7, 8);

		Point[] ipoints = a.getIntersectionPoints(b);
		assertNull(ipoints);
	}

}
