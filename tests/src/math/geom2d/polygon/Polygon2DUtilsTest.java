/**
 * File: 	Polygon2DUtilsTest.java
 * Project: javaGeom
 * 
 * Distributed under the LGPL License.
 *
 * Created: 25 janv. 09
 */
package math.geom2d.polygon;

import math.geom2d.Point2D;
import junit.framework.TestCase;


/**
 * @author dlegland
 *
 */
public class Polygon2DUtilsTest extends TestCase {

    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testUnion_Intersecting() {
        // points of the first polygon
        Point2D pa1 = new Point2D(50, 100);
        Point2D pa2 = new Point2D(250, 100);
        Point2D pa3 = new Point2D(250, 200);
        Point2D pa4 = new Point2D(50, 200);
        Polygon2D poly1 = new SimplePolygon2D(new Point2D[]{
                pa1, pa2, pa3, pa4});
        
        Point2D pb1 = new Point2D(100, 50);
        Point2D pb2 = new Point2D(200, 50);
        Point2D pb3 = new Point2D(200, 250);
        Point2D pb4 = new Point2D(100, 250);
        Polygon2D poly2 = new SimplePolygon2D(new Point2D[]{
                pb1, pb2, pb3, pb4});
       
        Polygon2D union = Polygon2DUtils.union(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(union.getVertexNumber(), 12);
    }

    public void testUnion_BInsideA() {
        // points of the first polygon
        Point2D pa1 = new Point2D(50, 50);
        Point2D pa2 = new Point2D(250, 50);
        Point2D pa3 = new Point2D(250, 250);
        Point2D pa4 = new Point2D(50, 250);
        Polygon2D poly1 = new SimplePolygon2D(new Point2D[]{
                pa1, pa2, pa3, pa4});
        
        Point2D pb1 = new Point2D(100, 100);
        Point2D pb2 = new Point2D(200, 100);
        Point2D pb3 = new Point2D(200, 200);
        Point2D pb4 = new Point2D(100, 200);
        Polygon2D poly2 = new SimplePolygon2D(new Point2D[]{
                pb1, pb2, pb3, pb4});
       
        Polygon2D union = Polygon2DUtils.union(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(union.getVertexNumber(), 4);
        //assertTrue(union.getBoundary().equals(poly1.getBoundary()));
    }

    public void testUnion_AInsideB() {
        // points of the first polygon
        Point2D pa1 = new Point2D(100, 100);
        Point2D pa2 = new Point2D(200, 100);
        Point2D pa3 = new Point2D(200, 200);
        Point2D pa4 = new Point2D(100, 200);
        Polygon2D poly1 = new SimplePolygon2D(new Point2D[]{
                pa1, pa2, pa3, pa4});
        
        Point2D pb1 = new Point2D(50, 50);
        Point2D pb2 = new Point2D(250, 50);
        Point2D pb3 = new Point2D(250, 250);
        Point2D pb4 = new Point2D(50, 250);
        Polygon2D poly2 = new SimplePolygon2D(new Point2D[]{
                pb1, pb2, pb3, pb4});
       
        Polygon2D union = Polygon2DUtils.union(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(union.getVertexNumber(), 4);
        //assertTrue(union.getBoundary().equals(poly2.getBoundary()));
    }
    
    public void testUnion_ADisjointB() {
        // points of the first polygon
        Point2D pa1 = new Point2D(50, 100);
        Point2D pa2 = new Point2D(100, 100);
        Point2D pa3 = new Point2D(100, 200);
        Point2D pa4 = new Point2D(50, 200);
        Polygon2D poly1 = new SimplePolygon2D(new Point2D[]{
                pa1, pa2, pa3, pa4});
        
        Point2D pb1 = new Point2D(200, 100);
        Point2D pb2 = new Point2D(250, 100);
        Point2D pb3 = new Point2D(250, 200);
        Point2D pb4 = new Point2D(200, 200);
        Polygon2D poly2 = new SimplePolygon2D(new Point2D[]{
                pb1, pb2, pb3, pb4});
       
        Polygon2D union = Polygon2DUtils.union(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(union.getVertexNumber(), 8);
    }

    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testUnion_TwoConcentricRings() {
    	LinearRing2D outerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(50, 50),
    			Point2D.create(450, 50),
    			Point2D.create(450, 450),
    			Point2D.create(50, 450)
    	});
    	LinearRing2D innerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(100, 100),
    			Point2D.create(100, 400),
    			Point2D.create(400, 400),
    			Point2D.create(400, 100)
    	});
    	Polygon2D poly1 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing1, innerRing1});
    	
    	LinearRing2D outerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(150, 150),
    			Point2D.create(350, 150),
    			Point2D.create(350, 350),
    			Point2D.create(150, 350)
    	});
    	LinearRing2D innerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(200, 200),
    			Point2D.create(200, 300),
    			Point2D.create(300, 300),
    			Point2D.create(300, 200)
    	});
    	Polygon2D poly2 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing2, innerRing2});
       
        Polygon2D union = Polygon2DUtils.union(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(16, union.getVertexNumber());
    }

    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testUnion_TwoIntersectingRings() {
    	LinearRing2D outerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(50, 150),
    			Point2D.create(350, 150),
    			Point2D.create(350, 450),
    			Point2D.create(50, 450)
    	});
    	LinearRing2D innerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(100, 200),
    			Point2D.create(100, 400),
    			Point2D.create(300, 400),
    			Point2D.create(300, 200)
    	});
    	Polygon2D poly1 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing1, innerRing1});
    	
    	LinearRing2D outerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(150, 50),
    			Point2D.create(450, 50),
    			Point2D.create(450, 350),
    			Point2D.create(150, 350)
    	});
    	LinearRing2D innerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(200, 100),
    			Point2D.create(200, 300),
    			Point2D.create(400, 300),
    			Point2D.create(400, 100)
    	});
    	Polygon2D poly2 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing2, innerRing2});
       
        Polygon2D union = Polygon2DUtils.union(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(24, union.getVertexNumber());
        
        assertEquals(4, union.getRings().size());
    }

    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testIntersection_Intersecting() {
        // points of the first polygon
        Point2D pa1 = new Point2D(50, 100);
        Point2D pa2 = new Point2D(250, 100);
        Point2D pa3 = new Point2D(250, 200);
        Point2D pa4 = new Point2D(50, 200);
        Polygon2D poly1 = new SimplePolygon2D(new Point2D[]{
                pa1, pa2, pa3, pa4});
        
        Point2D pb1 = new Point2D(100, 50);
        Point2D pb2 = new Point2D(200, 50);
        Point2D pb3 = new Point2D(200, 250);
        Point2D pb4 = new Point2D(100, 250);
        Polygon2D poly2 = new SimplePolygon2D(new Point2D[]{
                pb1, pb2, pb3, pb4});
       
        Polygon2D inter = Polygon2DUtils.intersection(poly1, poly2);
        assertTrue(inter!=null);
        assertEquals(4, inter.getVertexNumber());
    }
    
    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testIntersection_Disjoint() {
        // points of the first polygon
        Point2D pa1 = new Point2D(50, 100);
        Point2D pa2 = new Point2D(100, 100);
        Point2D pa3 = new Point2D(100, 200);
        Point2D pa4 = new Point2D(50, 200);
        Polygon2D poly1 = new SimplePolygon2D(new Point2D[]{
                pa1, pa2, pa3, pa4});
        
        Point2D pb1 = new Point2D(200, 100);
        Point2D pb2 = new Point2D(250, 100);
        Point2D pb3 = new Point2D(250, 200);
        Point2D pb4 = new Point2D(200, 200);
        Polygon2D poly2 = new SimplePolygon2D(new Point2D[]{
                pb1, pb2, pb3, pb4});
       
        Polygon2D inter = Polygon2DUtils.intersection(poly1, poly2);
        assertTrue(inter!=null);
        assertEquals(0, inter.getVertexNumber());
    }
    
    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testIntersection_TwoConcentricRings() {
    	LinearRing2D outerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(50, 50),
    			Point2D.create(450, 50),
    			Point2D.create(450, 450),
    			Point2D.create(50, 450)
    	});
    	LinearRing2D innerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(100, 100),
    			Point2D.create(100, 400),
    			Point2D.create(400, 400),
    			Point2D.create(400, 100)
    	});
    	Polygon2D poly1 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing1, innerRing1});
    	
    	LinearRing2D outerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(150, 150),
    			Point2D.create(350, 150),
    			Point2D.create(350, 350),
    			Point2D.create(150, 350)
    	});
    	LinearRing2D innerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(200, 200),
    			Point2D.create(200, 300),
    			Point2D.create(300, 300),
    			Point2D.create(300, 200)
    	});
    	Polygon2D poly2 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing2, innerRing2});
       
        Polygon2D union = Polygon2DUtils.intersection(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(0, union.getVertexNumber());
    }

    /**
     * Test method for {@link math.geom2d.polygon.Polygon2DUtils#union(math.geom2d.polygon.Polygon2D, math.geom2d.polygon.Polygon2D)}.
     */
    public void testIntersection_TwoIntersectingRings() {
    	LinearRing2D outerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(50, 150),
    			Point2D.create(350, 150),
    			Point2D.create(350, 450),
    			Point2D.create(50, 450)
    	});
    	LinearRing2D innerRing1 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(100, 200),
    			Point2D.create(100, 400),
    			Point2D.create(300, 400),
    			Point2D.create(300, 200)
    	});
    	Polygon2D poly1 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing1, innerRing1});
    	
    	LinearRing2D outerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(150, 50),
    			Point2D.create(450, 50),
    			Point2D.create(450, 350),
    			Point2D.create(150, 350)
    	});
    	LinearRing2D innerRing2 = LinearRing2D.create(new Point2D[]{
    			Point2D.create(200, 100),
    			Point2D.create(200, 300),
    			Point2D.create(400, 300),
    			Point2D.create(400, 100)
    	});
    	Polygon2D poly2 = MultiPolygon2D.create(new LinearRing2D[]{
    			outerRing2, innerRing2});
       
        Polygon2D union = Polygon2DUtils.intersection(poly1, poly2);
        assertTrue(union!=null);
        assertEquals(8, union.getVertexNumber());
        
        assertEquals(2, union.getRings().size());
    }

}
