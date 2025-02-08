package evl.algo;

import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.util.Arrays;

/**
 * 箱线图计算
 * @author  Liuyisi
 * @since   0.1
 */
public class Box {

    private final double[] box;

    private Box(double[] data) {
        this.box = boxPlotFilter(data);
    }

    public static Box box(double[] data) {
        return new Box(data);
    }

    public double lower_1() {
        return box[0];
    }

    public double lower_2() {
        return box[1];
    }

    public double lower_3() {
        return box[2];
    }

    public double lower_4() {
        return box[3];
    }

    public double upper_4() {
        return box[4];
    }


    public double upper_3() {
        return box[5];
    }

    public double upper_2() {
        return box[6];
    }

    public double upper_1() {
        return box[7];
    }

    public double min() {
        return box[8];
    }

    public double max() {
        return box[9];
    }

    /**
     * 计算
     */
    private static double[] boxPlotFilter(double[] dataA) {
        double[] boxArray = new double[10];
        //排序
        Arrays.sort(dataA);
        //下四分位数
        //double q1 = dataA[dataA.length / 4 - 1] / 4 + dataA[dataA.length / 4] * 3 / 4;
        double q1 = percentile(dataA, 25);
        boxArray[3] = q1;
        //中位数
        Median median = new Median();
        double q2 = median.evaluate(dataA);
        //上四分位数
        //double q3 = dataA[(dataA.length - dataA.length / 4) - 1] * 3 / 4 + dataA[(dataA.length - dataA.length / 4)] / 4;
        double q3 = percentile(dataA, 75);
        boxArray[4] = q3;
        double iqr = q3 - q1;
        double max = q3 + 1.5 * iqr;
        boxArray[5] = max;
        double min = q1 - 1.5 * iqr;
        boxArray[2] = min;
        boxArray[6] = max * 1.25;
        boxArray[7] = max * 1.5;
        boxArray[1] = min > 0 ? min * 0.75 : min * 1.25;
        boxArray[0] = (min > 0 ? min * 0.5 : min * 1.5);
        //最小值
        boxArray[8] = min > 0 ? min * 0.25 : min * 1.75;
        //最大值
        boxArray[9] = max * 1.5 * 1.25;
        return boxArray;
    }

    /**
     * 计算百分位数
     */
    private static double percentile(double[] sortedData, double percentile) {
        int n = sortedData.length;
        double rank = percentile / 100.0 * (n - 1);
        int lowerIndex = (int) Math.floor(rank);
        int upperIndex = (int) Math.ceil(rank);
        if (lowerIndex == upperIndex) {
            return sortedData[lowerIndex];
        }
        double weight = rank - lowerIndex;
        return sortedData[lowerIndex] * (1 - weight) + sortedData[upperIndex] * weight;
    }

}
