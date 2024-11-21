
/*
 * Katherine Tsai
 * This is the point class and provides basic methods for a point (x,y).
 */

public class Point {
	
	private int x; 
	private int y; 
	
	//initializes point (0,0)
	public Point(){

		x = 0;
		y = 0;
	}
	
	//initializes point with input
	public Point(int someX, int someY){

		x = someX;
		y = someY;
	}
	
	//copy constructor
	public Point(Point p){

		x = p.x;
		y = p.y;
	}
	
	
	//returns the distance between this point and the parameter point
	public double distance(Point p){

		return Math.sqrt(Math.pow(p.x-x, 2) + Math.pow(p.y-y, 2));
	}
	
	//returns x value
	public int getX(){

		return x;
	}
	
	//returns y value
	public int getY(){

		return y;
	}
	
	//sets x and y to parameter values
	public void set(int someX, int someY){

		x = someX;
		y = someY;
	}
	
	//returns true if this point is equal to parameter
	public boolean equals(Object o){
	
		if(!(o instanceof Point))
			return false;

		Point p = (Point)o;
		return (x == p.x && y == p.y);
	}
	
	//returns the hash value of this point
	public int hashCode() {
		
		return 31*x +y;
	}
	
	//returns the point in string format
	public String toString(){

		return "[" + x + ", " + y + "]";
	}
	
}
