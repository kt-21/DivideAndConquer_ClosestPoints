
/*
 * Katherine Tsai
 * This class tracks two points and provides basic methods for the point pair.
 */

public class PointPair {

	private Point p1;
	private Point p2;
	
	//initializes the two points to the parameter points
	public PointPair( Point s1, Point s2){

		p1 = new Point(s1);
		p2 = new Point(s2);
	}
	
	//copy constructor
	public PointPair( PointPair p){

		p1 = new Point(p.p1);
		p2 = new Point(p.p2);
	}
	
	//sets two points to parameter points
	public void setPoints(Point s1, Point s2){

		p1 = s1;
		p2 = s2;
	}

	//returns the distance between the two points
	public double distance(){

		return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2)+ Math.pow(p1.getY()-p2.getY(), 2));
	}

	//returns true if all four points in the two point pairs are equal
	public boolean equals(Object o){

		if(!(o instanceof PointPair))
		    return false;	
	
		PointPair  p = (PointPair)o;

		//checks p1 with p1 and p2 with p2
		if(p.p1.equals(p1)&& p.p2.equals(p2))
			return true;

		//checks p1 with p2 and p2 with p1
		if(p.p2.equals(p1)&& p.p1.equals(p2))
			return true;

		return false;
	}
	
	//returns the hash value of the point pair
	public int hashCode() {
		
		return p1.hashCode() + p2.hashCode();
	}
	
	//returns the point pair in string format
	public String toString(){

		return "{ "+  p1.toString() + ", " + p2.toString()+ " }";
	}
	
}
