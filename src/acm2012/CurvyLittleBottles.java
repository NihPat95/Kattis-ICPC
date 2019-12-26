package acm2012;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurvyLittleBottles {
    private final static double PI = 3.14159265;
    public static void main(String args[]) throws FileNotFoundException {

//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") +
                "/sampleInputs/CurvyLittleBottlesSample.txt"));
        int polynomialDeg;
        double start, end, intervalBy;
        int caseId = 1;
        while (scanner.hasNext()) {
            polynomialDeg = scanner.nextInt();
            double[] coefficients = new double[polynomialDeg + 1];
            for (int i = 0; i < polynomialDeg + 1; i++) {
                coefficients[i] = scanner.nextDouble();
            }
            start = scanner.nextDouble();
            end = scanner.nextDouble();
            intervalBy = scanner.nextDouble();
            computeHeightIntervals(coefficients, start, end, intervalBy, caseId);
            caseId++;
        }
    }

    private static void computeHeightIntervals(double[] coefficients, double start,
            double end, double intervalBy, int caseId) {

        double lowerVolume = computeIntegration(coefficients, start);
        double upperVolume = computeIntegration(coefficients, end);
        double totalVolume = upperVolume - lowerVolume;

        List<Double> points = new ArrayList<>();

        int i = 1;
        double nextVolume = computeIntegration(coefficients, start) + intervalBy;
        double prevStart = start;
        while(i <= 8 && nextVolume <= upperVolume){
            double point = binaryCompute(coefficients, prevStart, end, nextVolume);
            points.add(point - start);
            prevStart = point;
            i++;
            nextVolume = nextVolume + intervalBy;
        }
        System.out.println(String.format("Case %d: %.2f", caseId, totalVolume));
        StringBuilder sb = new StringBuilder();
        if(points.size() == 0) {
             sb.append("insufficient volume");
        } else {
            for(Double x: points){
                sb.append(String.format("%.2f ", x));
            }
        }

        System.out.println(sb.toString().trim());
    }

    private static double binaryCompute(double[] coefficients, double start,
                                        double end, double target) {
        double mid = (start + end) / 2;
        double temp = computeIntegration(coefficients, mid);
        if(Math.abs(start - end) < 0.00001) return start;
        else if(temp < target) return binaryCompute(coefficients, mid, end, target);
        else if(temp > target) return binaryCompute(coefficients, start, mid, target);
        else return start;
    }

    private static double computeIntegration(double[] coefficients, double point) {
        double sum = 0;
        for(int i=0; i<coefficients.length; i++){
            for(int j=0; j<coefficients.length; j++){
                double temp = coefficients[i] * coefficients[j] *
                        Math.pow(point, i+j+1) * PI / (double)(i+j+1);
                sum += temp;
            }
        }
        return sum;
    }

}
