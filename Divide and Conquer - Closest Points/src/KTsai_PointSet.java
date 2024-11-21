/*
 * Katherine Tsai
 * This program utilizes the divide and conquer approach to achieve an O(nlogn) bound for the closest points method.
 */

import java.util.*;

public class KTsai_PointSet extends ArrayList<Point> {
	
	public KTsai_PointSet(){}

	public KTsai_PointSet(KTsai_PointSet l){
		super(l);
	}

	//initializes a set of num random points
	public KTsai_PointSet(int xMax, int yMax, int num){

		Random r = new Random();
		for(int i = 1; i <= num; i++){

			//creates a point with random x and y that ranges from 0 to xMax and 0 to yMax respectively
			int x = r.nextInt(xMax + 1);
			int y = r.nextInt(yMax + 1);
			Point toAdd = new Point(x,y);

			//add if it's a new point
			if(!contains(toAdd))
				this.add(new Point(x,y));
			else
				i--;
		}
	}

	//returns a PointSet with all points that set and this PointSet include
	public KTsai_PointSet union(KTsai_PointSet set){

		KTsai_PointSet ret = new KTsai_PointSet(set);
		for(Point p: this)
				ret.add(p);
		return ret;
	}

	//returns a PointSet with all points that set and this PointSet share
	public KTsai_PointSet intersection(KTsai_PointSet set){

		KTsai_PointSet ret = new KTsai_PointSet();
		for(Point p : this)
			if(set.contains(p))
				ret.add(p);
		return ret;
	}

	//non-recursive method for client to call
	public PointPairSet closestPointsDC() {
		
		//sort points by x and y
		KTsai_PointSet xOrder = new KTsai_PointSet(this);
		xOrder.sortPoints(new XComparator());
		KTsai_PointSet yOrder = new KTsai_PointSet(this);
		yOrder.sortPoints(new YComparator());
		
		return closestPointsHelp(xOrder, yOrder, 0, size()-1);
	}
	
	//recursively divide and conquer to determine the set of closest points
	private PointPairSet closestPointsHelp(KTsai_PointSet xOrder, KTsai_PointSet yOrder, int start, int end) {
		
		//two points left
		if(end - start == 1) {
			PointPairSet toReturn = new PointPairSet();
			toReturn.add(new PointPair(xOrder.get(start), xOrder.get(end)));
			return toReturn;
		}
		
		//three points left
		if(end - start == 2) {
			
			//brute force
			PointPairSet toReturn = new PointPairSet();
			double min = Double.MAX_VALUE;
			for(int i = start; i < end; i++) {
				for(int j = i+1; j <= end; j++) {
					
					PointPair pair = new PointPair(xOrder.get(i), xOrder.get(j));
					double distance = pair.distance();
					
					//multiple pairs with same min distance
					if(distance == min)
						toReturn.add(pair);
					
					//new min distance found
					else if(distance < min) {
						toReturn.clean();
						toReturn.add(pair);
						min = distance;
					}
				}
			}
			return toReturn;
		}
		
		//physically separate yOrder
		int middle = (start + end) / 2;
		KTsai_PointSet yLeft = new KTsai_PointSet();
		KTsai_PointSet yRight = new KTsai_PointSet();
		for(int i = 0; i < yOrder.size(); i++) {
			
			Point current = yOrder.get(i);
			
			//place in respective side by comparing X value with middle's X value
			if(current.getX() < xOrder.get(middle).getX())
				yLeft.add(current);
			else if(current.getX() > xOrder.get(middle).getX())
				yRight.add(current);
			
			//compare Y in case identical X values
			else {
				if(current.getY() <= xOrder.get(middle).getY())
					yLeft.add(current);
				else
					yRight.add(current);
			}
		}
		
		//determine closest for when points are on same side
		PointPairSet left = closestPointsHelp(xOrder, yLeft, start, middle);
		PointPairSet right = closestPointsHelp(xOrder, yRight, middle + 1, end);
		PointPairSet min;
		
		if(left.getDistance() < right.getDistance())
			min = left;
		else if(left.getDistance() == right.getDistance()) {
			min = left;
			min.addAll(right);
		}
		else
			min = right;
		
		//create strip
		KTsai_PointSet strip = new KTsai_PointSet();
		for(int i = 0; i < yOrder.size(); i++)
			if(Math.abs(yOrder.get(i).getX() - xOrder.get(middle).getX()) < min.getDistance())
				strip.add(yOrder.get(i));
		
		//examine points in strip
		for(int i = 0; i < strip.size(); i++) {
			for(int j = i + 1; j < strip.size(); j++) {
				
				if(Math.abs(strip.get(i).getY() - strip.get(j).getY()) > min.getDistance())
					j = strip.size();
				else if(strip.get(i).distance(strip.get(j)) < min.getDistance()) {
					min.clean();
					min.add(new PointPair(strip.get(i), strip.get(j)));
				}
				else if(strip.get(i).distance(strip.get(j)) == min.getDistance())
					min.add(new PointPair(strip.get(i), strip.get(j)));
			}
		}
		return min;
	}

	// sorts by X if XComparator object is passed in; sorts by Y if YComparator object is passed in
	// this sort runs in O(nlogn)
	public void sortPoints(Comparator<Point> compareMethod){
		Collections.sort(this, compareMethod);
	}
}
