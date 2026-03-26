class Solution {
    private long totalSum = 0;

    public boolean canPartitionGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        totalSum = 0;
        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
            }
        }

        if (isValidCut(grid)) return true;

        reverseRows(grid);
        if (isValidCut(grid)) return true;
        reverseRows(grid);

        int[][] transposed = transpose(grid);
        if (isValidCut(transposed)) return true;

        reverseRows(transposed);
        return isValidCut(transposed);
    }

    private boolean isValidCut(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        long upperSum = 0;
        Set<Long> seenValues = new HashSet<>();

        for (int r = 0; r < rows - 1; r++) {
            for (int c = 0; c < cols; c++) {
                upperSum += grid[r][c];
                seenValues.add((long) grid[r][c]);
            }

            long lowerSum = totalSum - upperSum;
            long difference = upperSum - lowerSum;

            if (difference == 0) return true;

            if (difference == grid[0][0] ||
                difference == grid[0][cols - 1] ||
                difference == grid[r][0]) {
                return true;
            }

            if (r > 0 && cols > 1 && seenValues.contains(difference)) {
                return true;
            }
        }
        return false;
    }

    private void reverseRows(int[][] grid) {
        int top = 0, bottom = grid.length - 1;
        while (top < bottom) {
            int[] temp = grid[top];
            grid[top] = grid[bottom];
            grid[bottom] = temp;
            top++;
            bottom--;
        }
    }

    private int[][] transpose(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = grid[i][j];
            }
        }
        return result;
    }
}