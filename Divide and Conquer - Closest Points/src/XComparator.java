

import java.util.*;
public class XComparator implements Comparator<Point> {

	
	public int compare(Point p1, Point p2){
		
		if(p1.getX() != p2.getX())
			return p1.getX() - p2.getX();
		else
			return p1.getY() - p2.getY();
	}
}
