
public class ClosestPairDC {

	public final static double INF = java.lang.Double.POSITIVE_INFINITY;


	//
	// findClosestPair()
	//
	// Given a collection of nPoints points, find and ***print***
	// * the closest pair of points
	// * the distance between them
	// in the form "(x1, y1) (x2, y2) distance"
	//

	// INPUTS:
	// - points sorted in nondecreasing order by X coordinate
	// - points sorted in nondecreasing order by Y coordinate
	//


	public static XYPoint[] findClosestPairHelp(XYPoint pointsByX[],XYPoint pointsByY[]) {

		int nPoints = pointsByX.length;
		
		//Base cases
		if (nPoints == 2)
			return new XYPoint[] { pointsByX[0], pointsByX[1]};

		if (nPoints == 3)
			return ClosestPairNaive.findClosestPairHelp(pointsByX);

		int midpt = pointsByX.length / 2;

		//Left and Right Portions of data set by X
		XYPoint[] XL = new XYPoint[midpt];
		XYPoint[] XR = new XYPoint[nPoints - midpt];

		int r = 0;
		int s = 0;

		//Sorting points from sorted (by X value) array into left or right portion
		for (int q = 0; q < nPoints; q++) {

			XYPoint point = pointsByX[q];
			if (point.isLeftOf(pointsByX[midpt])) {
				XL[r] = point;
				r++;
			} else {
				XR[s] = point;
				s++;
			}
		}

		XYPoint[] YL = new XYPoint[midpt];
		XYPoint[] YR = new XYPoint[nPoints - midpt];

		int d = 0;
		int e = 0;

		// same as above, but sorted by Y value 
		for (int x = 0; x < nPoints; x++) {

			XYPoint point = pointsByY[x];
			if (point.isLeftOf(pointsByX[midpt])) {
				YL[d] = point;
				d++;
			} else {
				YR[e] = point;
				e++;
			}
		}

		//Recursive call 
		XYPoint[] CPL = findClosestPairHelp(XL, YL);
		XYPoint[] CPR = findClosestPairHelp(XR, YR);

		double distL = CPL[0].dist(CPL[1]);
		double distR = CPR[0].dist(CPR[1]);

		XYPoint mid = pointsByX[midpt];

		XYPoint[] closeP = CPL;
		double DistLR = distL;
		if (distL > distR) {
			closeP = CPR;
			DistLR = distR;
		}

		XYPoint[] yStrip = new XYPoint[nPoints];
		int yStripLength = 0;
		//Generation of YStrips
		for (int j = 0; j < nPoints; j++) {

			if (Math.abs(pointsByY[j].x - mid.x) < DistLR) {
				yStrip[yStripLength] = pointsByY[j];
				yStripLength++;
			}
		}
		//Comparison of YStrips
		for (int k = 0; k < yStripLength - 1; k++) {

			int l = k + 1;

			while (l < yStripLength && (yStrip[l].y - yStrip[k].y) < DistLR) {

				double dis = yStrip[k].dist(yStrip[l]);
				if (dis < DistLR) {
					DistLR = dis;
					closeP[0] = yStrip[k];
					closeP[1] = yStrip[l];
				}
				l++;
			}
		}
		
		return closeP;
		
		
	}
	//use helper method from above to calculate closest point from input arrays
	public static void findClosestPair(XYPoint[] PX, XYPoint[] PY){
		XYPoint[] CPF = findClosestPairHelp(PX, PY);
		
		XYPoint one = CPF[0]; 
		XYPoint two = CPF[1];
		double dist = CPF[0].dist(CPF[1]);

		if (CPF[1].x < CPF[0].x) {
			one = CPF[1];
			two = CPF[0];
		} 
		else if (CPF[0].x == CPF[1].x) {

			if (CPF[0].y < CPF[1].y) {
				one = CPF[0];
				two = CPF[1];
			} 
			else {
				one = CPF[1];
				two = CPF[0];
			}
		}
		System.out.println("(" + one.x + ", " + one.y + ") (" + two.x + ", " + two.y + ") " + dist);
	}
}
