
public class ClosestPairNaive {

	public final static double INF = java.lang.Double.POSITIVE_INFINITY;

	//
	// findClosestPair()
	//
	// Given a collection of nPoints points, find and ***print***
	//  * the closest pair of points
	//  * the distance between them
	// in the form "(x1, y1) (x2, y2) distance"
	//

	// INPUTS:
	//  - points sorted in nondecreasing order by X coordinate
	//  - points sorted in nondecreasing order by Y coordinate
	//

	//Helper Method that handles iteration and calculations while calculating Closest Pair. Returns closest pair as array of two points.
	public static XYPoint[] findClosestPairHelp(XYPoint points[])
	{
		int nPoints = points.length;
		double distance = INF;

		if(nPoints == 1){}

		XYPoint[] closestPair = new XYPoint[2];  

		for(int j=0; j<nPoints-1; j++) {
			
			for(int k=j+1; k<nPoints; k++) {
			
				double dis = points[j].dist(points[k]);
				if (dis < distance) {
					
					closestPair[0] = points[j];
					closestPair[1] = points[k];
					distance = dis;
				}
			}
		}

		return closestPair;
		
		
	}
	//Finds out the correct order to use to print out the closest pair of points. *Smallest point should come first when displaying results*
	public static void findClosestPair(XYPoint[] points){
		XYPoint[] CPFN = findClosestPairHelp(points);
		XYPoint one = CPFN[0]; 
		XYPoint two = CPFN[1];
		double dist = CPFN[0].dist(CPFN[1]);

		if (CPFN[1].x < CPFN[0].x) {
			one = CPFN[1];
			two = CPFN[0];
		} 
		else if (CPFN[0].x == CPFN[1].x) {

			if (CPFN[0].y < CPFN[1].y) {
				one = CPFN[0];
				two = CPFN[1];
			} 
			else {
				one = CPFN[1];
				two = CPFN[0];
			}
		}
		System.out.println("(" + one.x + ", " + one.y + ") (" + two.x + ", " + two.y + ") " + dist);
	}
}

