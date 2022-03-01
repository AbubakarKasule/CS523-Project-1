package infodynamics.demos.autoanalysis;

import infodynamics.utils.ArrayFileReader;
import infodynamics.utils.MatrixUtils;

import infodynamics.measures.continuous.*;
import infodynamics.measures.continuous.gaussian.*;

public class GeneratedCalculator {

  public static void main(String[] args) throws Exception {

    // 0. Load/prepare the data:
    String dataFile = "C:\\Users\\Abubakar\\Desktop\\CS523\\Project 1\\final_EuropeJIDTformat_formatted.csv";
    ArrayFileReader afr = new ArrayFileReader(dataFile);
    double[][] data = afr.getDouble2DMatrix();
    // 1. Construct the calculator:
    TransferEntropyCalculatorGaussian calc;
    calc = new TransferEntropyCalculatorGaussian();
    // 2. Set any properties to non-default values:
    // No properties were set to non-default values
    
    // Compute for all pairs:
    for (int s = 0; s < 18; s++) {
        for (int d = 0; d < 18; d++) {
            // For each source-dest pair:
            if (s == d) {
                continue;
            }
            double[] source = MatrixUtils.selectColumn(data, s);
            double[] destination = MatrixUtils.selectColumn(data, d);

            // 3. Initialise the calculator for (re-)use:
            calc.initialise();
            // 4. Supply the sample data:
            calc.setObservations(source, destination);
            // 5. Compute the estimate:
            double result = calc.computeAverageLocalOfObservations();

            System.out.printf("TE_Gaussian(col_%d -> col_%d) = %.4f nats\n",
                s, d, result);
        }
    }
  }
}

