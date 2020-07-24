public class BubbleGrid {

    private int[][] grid;


    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;

    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] result = new int[darts.length];
        int above = grid.length * grid[0].length;
        int[][] Grid = getLast(darts);
        UnionFind UF = UnionFindOfGrid(Grid);

        for (int i = 0; i < darts.length; i++){
            int preSize = UF.sizeOf(above);
            int updX = darts[darts.length - 1 - i][0];
            int updY = darts[darts.length - 1 - i][1];
            updateUF(UF,updX,updY,Grid);
            int updSize = UF.sizeOf(above);
            if (updSize == preSize){
                result[darts.length - 1 - i] = 0;
            }else {
                result[darts.length - 1 - i] = updSize - preSize - 1;
            }
        }

        return result;
    }


    public void updateUF(UnionFind UF, int updX, int updY, int[][] Grid){
        if (grid[updX][updY] == 1){
            connectAround(UF,Grid,updX,updY);
            if (updX == 0){
                UF.union(Index(updX,updY),grid.length * grid[0].length);
            }
        }


    }






    public int[][] getLast(int[][] darts){
        int[][] gridlast = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                gridlast[i][j] = grid[i][j];
            }
        }
        for(int i = 0; i < darts.length; i++){
            gridlast[darts[i][0]][darts[i][1]] = 0;
        }
        return gridlast;
    }

    public UnionFind UnionFindOfGrid(int[][] Grid){
        UnionFind uf = new UnionFind(grid.length * grid[0].length + 1);
        int Above = grid.length * grid[0].length;
        for (int i =0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (Grid[i][j] == 1){
                    connectAround(uf, Grid, i, j);
                }
            }
        }
        for (int i = 0; i < grid[0].length; i++){
            if (Grid[0][i] == 1){
                uf.union(i, Above);
            }
        }
        return uf;
    }

    public void connectAround(UnionFind uf, int[][] Grid, int row, int col){
        int[][] dxy = {{-1,0},{0,-1},{0,1},{1,0}};
        Grid[row][col] = 1;
        for (int i = 0; i < 4; i++){
            int rowPos = row + dxy[i][0];
            int colPos = col + dxy[i][1];
            if(inGrid(rowPos,colPos) && Grid[rowPos][colPos] == 1){
                uf.union(Index(row,col),Index(rowPos,colPos));
            }
        }


    }

    public boolean inGrid(int rowPos, int colPos){
        if(rowPos >= 0 && rowPos < grid.length && colPos >= 0 && colPos < grid[0].length){
            return true;
        }
        return false;
    }

    public int Index(int r, int c){
        return r * grid[0].length + c;
    }


}
