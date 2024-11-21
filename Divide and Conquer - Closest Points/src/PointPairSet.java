
/*
 * Katherine Tsai
 * This class represents a small set of point pairs and provides basic methods for them.
 */

import java.util.*;

public class PointPairSet extends HashSet<PointPair> {

	//default constructor
	public PointPairSet(){
		
	}
	
	//copy constructor
	public PointPairSet( PointPairSet s){

		super(s);
	}
	
	//clears the set
	public void clean(){
		
		//using iterator, removes PointPairs one at a time
		Iterator iter = this.iterator();
		while(iter.hasNext()){
			iter.next();
			iter.remove();
		}
	}
	
	//adds all PointPairs from other to this PointPairSet
	public void addAll(PointPairSet other){
		
		Iterator<PointPair> iter = other.iterator();
		
		while(iter.hasNext())
			add(iter.next());
	}

	//returns the distance of the PointPair in the set with the smallest hashCode
	public double getDistance(){
		Iterator<PointPair> iter = this.iterator();
		return iter.next().distance();
	} 

}
