
public class StepCounter {
//aaaaa
	public static int countSteps(double[][] sensorData) {
		int steps = 0;
		double[] magnitudes = calculateMagnitudesFor(sensorData);
//		double mean = calculateMean(magnitudes);
//		double standardDeviation = calculateStandardDeviation(magnitudes, mean);
//		for (int i = 1; i < magnitudes.length - 1; i++) {
//			if (isPeak(magnitudes[i], magnitudes[i - 1], magnitudes[i + 1])) {
//				if (Math.abs(magnitudes[i] - mean) > standardDeviation)
//					steps++;
//			}
//		}
		int n = 10;
		System.out.println(magnitudes.length);
		for(int i = n; i < magnitudes.length - n; i++){
			double threshold = getThresholdForSection(magnitudes, i, n);
			if (isPeak(magnitudes[i], magnitudes[i - 1], magnitudes[i + 1])) {
				if (magnitudes[i] > threshold)steps++;
			}
		}
		return steps;
	}

	private static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public static double[] calculateMagnitudesFor(double[][] sensorData) {
		double[] magnitudes = new double[sensorData.length];
		for (int i = 0; i < sensorData.length; i++) {
			magnitudes[i] = calculateMagnitude(sensorData[i][0], sensorData[i][1], sensorData[i][2]);
		}
		return magnitudes;
	}

	public static double calculateStandardDeviation(double[] arr, double mean) {
		double standardDeviation = 0;
		for (double i : arr) {
			standardDeviation += ((i - mean) * (i - mean));
		}
		standardDeviation /= (arr.length - 1);
		return Math.sqrt(standardDeviation);
	}

	private static double calculateMean(double[] arr) {
		double sum = 0;
		for (double i : arr) {
			sum += i;
		}
		double mean = sum / arr.length;
		return mean;
	}

	/****
	 * checks if a value is greater than its values next to it
	 * 
	 * @param index
	 * @param arr
	 * @param magnitudes
	 * @return whether an index is considered a peak
	 */

	private static boolean isPeak(double possiblePeak, double valueBefore, double valueAfter) {
		if (possiblePeak > valueBefore && possiblePeak > valueAfter)
			return true;
		return false;
	}

	/***
	 * Calculates the threshold for n data points left and right of current
	 * value
	 * 
	 * @param startingIndex
	 *            index that we are finding threshold for
	 * @param n
	 *            values left and right of startingIndex to use in window
	 * @return threshold for this window
	 */
	private static double getThresholdForSection(double[] arr, int startingIndex, int n) {
		double[] magnitudes = CSVData.getPartOfArray(arr, startingIndex - n, startingIndex + n);
		double mean = calculateMean(magnitudes);
		double standardDeviation = calculateStandardDeviation(magnitudes, mean);
		double threshold = mean + standardDeviation;
		return threshold;
	}
}
