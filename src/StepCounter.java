
public class StepCounter {
	// aaaaa
	public static double[] countSteps(double[][] sensorData, int n) {
		double[] magnitudes = calculateMagnitudesFor(sensorData);
		double[] steps = new double[magnitudes.length];
		// double mean = calculateMean(magnitudes);
		// double standardDeviation = calculateStandardDeviation(magnitudes,
		// mean);
		// for (int i = 1; i < magnitudes.length - 1; i++) {
		// if (isPeak(magnitudes[i], magnitudes[i - 1], magnitudes[i + 1])) {
		// if (Math.abs(magnitudes[i] - mean) > standardDeviation)
		// steps++;
		// }
		// }
		for (int i = 1; i < magnitudes.length - 1; i++) {
			if (isPeak(magnitudes[i], magnitudes[i - 1], magnitudes[i + 1])) {
				double threshold = getThresholdForSection(magnitudes, i, n);
				if (magnitudes[i] >= threshold)
					steps[i]++;
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
		double SD = 0;
		for (double i : arr) {
			standardDeviation += ((i - mean) * (i - mean));
		}
		SD = standardDeviation / (double) ((arr.length - 1) - 1);
		return Math.sqrt(SD);
	}

	private static double calculateMean(double[] arr) {
		double sum = 0;
		for (double i : arr) {
			sum += i;
		}
		double mean = (double) sum / (double) arr.length;
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
	private static double getThresholdForSection(double[] arr, int currentIndex, int n) {
		double[] magnitudes = CSVData.getPartOfArray(arr, Math.max(currentIndex - n, 0),
				Math.min(currentIndex + n, arr.length - 1), n, currentIndex);
		double mean = calculateMean(magnitudes);
		double standardDeviation = calculateStandardDeviation(magnitudes, mean);
		double threshold = mean + standardDeviation;
		return threshold;
	}

	public static int getNumSteps(double[] arr) {
		int steps = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				steps++;
			}
		}
		return steps;
	}
}
