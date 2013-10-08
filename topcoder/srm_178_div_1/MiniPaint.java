public class MiniPaint {
    int calcMinMis(String[] board, int row, int minCol, int maxCol) {
        int numBlack = 0;
        int numWhite = 0;
        for (int i = minCol; i <= maxCol; i++) {
            if (board[row].charAt(i) == 'B') {
                numBlack++;
            } else {
                numWhite++;
            }
        }
        return Math.min(numBlack, numWhite);
    }

    int leastBad(String[] board, int maxStrokes) {
        int[][][] f = new int[board.length][board[0].length()][maxStrokes + 1];
        int n = board.length;
        int m = board[0].length();
        if (maxStrokes == 0) {
            return n * m;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i][j][0] = i * m + j + 1;
            }
        }

        int best = f[n - 1][m - 1][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 1; c <= maxStrokes; c++) {
                    if (c == 1) {
                        if (i > 0) {
                            f[i][j][c] = calcMinMis(board, i, 0, j)
                                    + f[i - 1][m - 1][0];
                        } else {
                            f[i][j][c] = calcMinMis(board, i, 0, j);
                        }
                    } else {
                        f[i][j][c] = f[i][j][c - 1];

                        // first we consider last stroke is on current row
                        for (int k = 0; k < j; k++) {
                            int v = f[i][k][c - 1]
                                    + calcMinMis(board, i, k + 1, j);
                            if (v < f[i][j][c]) {
                                f[i][j][c] = v;
                            }
                        }

                        // then we need consider last stroke is on previous
                        // row(s)
                        if (i > 0) {
                            int v = f[i - 1][m - 1][c - 1]
                                    + calcMinMis(board, i, 0, j);
                            if (v < f[i][j][c]) {
                                f[i][j][c] = v;
                            }
                        }
                    }

                    // we need consider rest blocks
                    if (c == maxStrokes) {
                        f[i][j][c] = f[i][j][c] + (n * m - (i * m + j + 1));
                        if (f[i][j][c] < best) {
                            best = f[i][j][c];
                        }
                    }
                }
            }
        }

        return best;
    }

    public static void main(String[] args) {
        String[] board = {
                "BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
                "BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
                "BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
                "BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
                "BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW",
                "BWBWBWBWBWBWBWBWBWBWBWBWBWBWBW"};
        int maxStrokes = 100;
        System.out.println(new MiniPaint().leastBad(board, maxStrokes));
    }
}