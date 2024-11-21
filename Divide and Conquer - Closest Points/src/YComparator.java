

import java.util.*;
public class YComparator implements Comparator<Point> {
	
	
	public int compare(Point p1, Point p2){
		
		if(p1.getY() != p2.getY())
			return p1.getY() - p2.getY();
		else
			return p1.getX() - p2.getX();
	}
}
