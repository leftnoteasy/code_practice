public class HexagonalBoard {
    private static int[][] DIR = new int[][] {
        {0, -1},
        {-1, 0},
        {-1, 1},
        {0, 1},
        {1, 0},
        {1, -1},
    };

    public int minColors(String[] in) {
        int n = in.length;
        int max = 0;
        int[][] map = new int[n][n];
        boolean[] used = new boolean[4];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = 0;
            }
        }
        
        for (int i = 0; i < n; i++) {
            // we already have enough color
            if (max == 3) {
                break;
            }

            for (int j = 0; j < n; j++) {
                // we already have enough color
                if (max == 3) {
                    break;
                }

                // don't need handle a cell doesn't need color
                if (in[i].charAt(j) == '-') {
                    continue;
                }

                // check adjacent used cells
                for (int k = 1; k < 4; k++) {
                    used[k] = false;
                }

                // check adjancent cells
                for (int k = 0; k < 6; k++) {
                    int x = i + DIR[k][0];
                    int y = j + DIR[k][1];
                    if ((x >= 0) && (y >= 0) && (x < n) && (y < n)) {
                        if (in[x].charAt(y) == 'X') {
                            used[map[x][y]] = true;
                        }
                    }
                }

                // color current cell
                for (int k = 1; k < 4; k++) {
                    if (!used[k]) {
                        map[i][j] = k;
                        break;
                    }
                }

                if (k > max) {
                    max = k;
                }
            }
        }

        return max;
    }
    
    public static void main(String[] args) {
        String[] in = {"XX-XX--"
                ,"-XX-XXX"
                ,"X-XX--X"
                ,"X--X-X-"
                ,"XX-X-XX"
                ,"-X-XX-X"
                ,"-XX-XX-"};
        HexagonalBoard board = new HexagonalBoard();
        board.minColors(in);
    }
}