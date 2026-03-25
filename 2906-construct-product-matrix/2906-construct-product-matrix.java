class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 12345;
        
        int[][] result = new int[m][n];
        int total = m * n;
        
        
        long[] prefix = new long[total];
        long[] suffix = new long[total];
        
        
        prefix[0] = 1;
        suffix[total - 1] = 1;
        
        
        for (int i = 1; i < total; i++) {
            int row = (i - 1) / n;
            int col = (i - 1) % n;
            prefix[i] = (prefix[i - 1] * grid[row][col]) % MOD;
        }
        
        
        for (int i = total - 2; i >= 0; i--) {
            int row = (i + 1) / n;
            int col = (i + 1) % n;
            suffix[i] = (suffix[i + 1] * grid[row][col]) % MOD;
        }
        
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                result[i][j] = (int)((prefix[idx] * suffix[idx]) % MOD);
            }
        }
        
        return result;
    }
}