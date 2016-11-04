 import java.util.Arrays;

import processing.core.PApplet;

public class Tester {
	public static double[][] sampleData;
	public static String datafile = "data/walkingSampleData-out.txt";
	public static String videofile = "data/walkingSampleData.mp4";

	public static void main(String[] args) {
		// Create data set
		CSVData dataset = CSVData.createDataSet(datafile, 0);

		// Get 2d array of all data
		sampleData = dataset.getAllData();

		// Extract columns for time, and x acceleration, y acceleration, z
		// acceleration
		double[] time = ArrayHelper.extractColumn(sampleData, 0);
		double[][] accelerationData = ArrayHelper.extractColumns(sampleData, new int[] { 1,2,3 });

		double[] accelerationMagnitudes = CountStepsBlank.calculateMagnitudesFor(accelerationData);

		double[] steps = StepCounter.countSteps(time, accelerationData);
		//System.out.println(Arrays.toString(steps));
		double[][] displayData = ArrayHelper.combineAsColumns(time, accelerationMagnitudes);

		double[][] correspondStepsToTime = ArrayHelper.combineAsColumns(time, steps);

		// DataExplorer.runDataExplorer(displayData, new String[] {"time"
		// ,"mags"}, videofile);

		System.out.println(Arrays.toString(ArrayHelper.extractColumn(correspondStepsToTime, 0)));
		System.out.println(Arrays.toString(ArrayHelper.extractColumn(correspondStepsToTime, 1)));
		System.out.println(StepCounter.getNumSteps(steps));
	}
}