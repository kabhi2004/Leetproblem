class Solution {
    public String[] createGrid(int m, int n) {

        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];

            // Initially, all cells are obstacles
            for (int j = 0; j < n; j++) {
                row[j] = '#';
            }

            // Open the first row
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    row[j] = '.';
                }
            }

            // Open the last column
            row[n - 1] = '.';

            grid[i] = new String(row);
        }

        return grid;
    }
}