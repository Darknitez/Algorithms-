//
// LAB1.JAVA
// Main driver code for CSE 241 Lab #1.
//
// WARNING: this file will be replaced with the unmodified
// original by the auto-grader and at turn-in time!  Do
// not make modifications to this file that are necessary
// for the correctness of your lab!  (You may wish to
// modify your local copy to do the timing experiments
// requested by the lab.)

import java.util.*;

public class Lab1 {

	static final long seed = 9371;

	public static void main(String args[])
	{
		XYPoint points [];
		String fileName;
		XYPoint[] ran = null;
		int nPoints = 0;
		PRNG prng = null;

		if (args.length >= 1)
		{
			fileName = args[0];
		}
		else
		{
			System.out.println("Syntax: Lab1 <filename>");
			return;
		}

		if (fileName.charAt(0) != '@')
		{
			points = PointReader.readXYPoints(fileName);
		}
		else
		{
			nPoints = Integer.parseInt(fileName.substring(1));		

			prng = new PRNG(seed);

			points = genPointsAtRandom(nPoints, prng);


		}

		if (points.length < 2)
		{
			System.out.println("ERROR: input must contain at least two points");
			return;
		}

		{

			XComparator lessThanX = new XComparator();
			YComparator lessThanY = new YComparator();

			ran = genPointsAtRandom(nPoints, prng);
			for(int p = 0; p<100; p++){

				XYPoint pointsByX [] = new XYPoint [ran.length];
				XYPoint pointsByY [] = new XYPoint [ran.length];

				for (int j = 0; j < ran.length; j++)
				{
					pointsByX[j] = ran[j];
					pointsByY[j] = ran[j];
				}

				Arrays.sort(pointsByX, lessThanX); // sort by x-coord
				Arrays.sort(pointsByY, lessThanY); // sort by y-coord

				Date startTime = new Date();
				ClosestPairDC.findClosestPair(pointsByX, pointsByY);


				Date endTime = new Date();
				long elapsedTime = endTime.getTime() - startTime.getTime();

				System.out.println("For n = " + points.length + 
						", the elapsed time is " +
						elapsedTime + " milliseconds.");
				System.out.println("");
			}
		}





		// Now run the naive algorithm
//		{
//
//
//				Date startTime = new Date();
//
//				ClosestPairNaive.findClosestPair(ran);
//
//
//				Date endTime = new Date();
//				long elapsedTime = endTime.getTime() - startTime.getTime();
//
//				System.out.println("For n = " + points.length + 
//						", the naive elapsed time is " +
//						elapsedTime + " milliseconds.");
//				System.out.println("");
//			}
		}


	//
	// genPointsAtRandom()
	// Generate an array of specified size containing
	// points with coordinates chosen at random, using
	// the specified random sequence generator.
	//

	static XYPoint[] genPointsAtRandom(int nPoints, 
			PRNG prng) 
	{
		XYPoint points[] = new XYPoint [nPoints];

		double x = 0.0;
		double y = 0.0;

		double step = Math.sqrt(nPoints);

		for (int j = 0; j < nPoints; j++) 
		{
			// jitter next point's X coordinate
			x += 10000.0 * (prng.nextDouble() - 0.5);

			// move the Y coordinate a random amount up,
			// while keeping it within limits [0 .. nPoints)
			y = (y + step * prng.nextDouble()) % nPoints;

			points[j] = new XYPoint((int) Math.round(x), 
					(int) Math.round(y));
		}

		return points;
	}
}

