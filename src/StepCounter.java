
public class StepCounter {

	public static double[] countSteps(double[] times, double[][] sensorData) {
		double[] steps = new double[times.length];
		double[] magnitudes = calculateMagnitudesFor(sensorData);
		double mean = calculateMean(magnitudes);
		double standardDeviation = calculateStandardDeviation(magnitudes, mean);
		for (int i = 1; i < magnitudes.length - 1; i++) {
			//System.out.println(isPeak(magnitudes, i));
		//	System.out.println(magnitudes.length);
			//System.out.println(magnitudes[i]);
			if (isPeak(magnitudes, i)) {
				if (magnitudes[i] >= mean + standardDeviation) {
					steps[i]++;
				}
			}
		}
		return steps;

	}

	public static double calculateMagnitude(double x, double y, double z) {
		return Math.sqrt(x * x + y * y + z * z);
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
		for (int i = 0; i < arr.length; i++) {
			standardDeviation += (arr[i] - mean) * (arr[i] - mean);
		}
		standardDeviation = standardDeviation / (double) ((arr.length - 1) - 1);

		return Math.sqrt(standardDeviation);
	}

	public static double calculateMean(double[] arr) {
		int mean = 0;
		for (int i = 0; i < arr.length; i++) {
			mean += arr[i];
		}

		return (double) mean / (double) arr.length;
	}

	/****
	 * checks if a value is greater than its values next to it
	 * 
	 * @param index
	 * @param arr
	 * @param magnitudes
	 * @return whether an index is considered a peak
	 */
	public static boolean isPeak(double[] arr, int index) {
		if (arr[index] > arr[index - 1] && arr[index] > arr[index + 1]) {
			return true;
		}
		return false;
	}

	public static int getNumSteps(double[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				count++;
			}
		}

		return count;
	}

}
