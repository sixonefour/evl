package evl.algo;

/**
 * DTW算法
 * @author  Liuyisi
 * @since   0.1
 */
public class Dtw {
    /**
     * 最短距离计算
     * @param arr1 数组1
     * @param arr2 数组2
     * @return distance or Infinity
     */
    public static double distance(double[] arr1, double[] arr2) {
        if(null == arr1 || null == arr2) {
            return Double.POSITIVE_INFINITY;
        }
        int n=arr1.length + 1;
        int m=arr2.length + 1;
        double[][] dp = new double[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        dp[0][0]=0;
        for(int i = 1; i < n; i++){
            for(int j=1; j < m; j++){
                double cost = Math.abs(arr1[i-1] - arr2[j-1]);
                dp[i][j] = cost + Math.min( dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
            }
        }
        return dp[n-1][m-1];
    }


    public static void main(String[] args) {
        System.out.println(distance(new double[]{1, 1, 1}, new double[]{1}));

        System.out.println(distance(new double[]{50,50}, new double[]{40,60,50}));
    }
}
