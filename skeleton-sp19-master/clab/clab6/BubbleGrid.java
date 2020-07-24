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
        // TODO
        int[] falls = new int[darts.length];  //结果数组
        int[][] Grid = GridLast(grid,darts);  // 最后状态的网格
        int above = grid.length * grid[0].length;
        UnionFind UF = UnionFindOfOneGrid(Grid); //计算出Grid中的union集合

        for(int i =0; i < darts.length; i++){
            int preSize = UF.sizeOf(above); //union中最后一位统计卡住的泡泡数量
            int updatePosX = darts[darts.length - 1 - i][0]; //从最后一次操作开始还原
            int updatePosY = darts[darts.length - 1 - i][1];
            updateUF(UF, Grid, updatePosX, updatePosY);
            int updatesize = UF.sizeOf(above);
            if(updatesize == preSize){
                falls[darts.length - 1 - i] = 0;
            }else {
                falls[darts.length - 1 - i] = updatesize - preSize - 1;
            }
        }


        return falls;
    }

    public void updateUF(UnionFind UF, int[][] Grid, int updX, int updY){
        if( grid[updX][updY] == 1){
            connectSurround(Grid, updX, updY, UF);
            if(updX == 0){
                int above = grid.length * grid[0].length;
                UF.union(Index(Grid,updX,updY),above);
            }
        }
    }




    public static int[][] GridLast(int[][] grid, int[][] darts){
        int[][] gridlast = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                gridlast[i][j] = grid[i][j];
            }
        }

        for(int i =0; i < darts.length; i++){
            gridlast[darts[i][0]][darts[i][1]] = 0;
        }



        return gridlast;

    }

    public UnionFind UnionFindOfOneGrid(int[][] grid){
        UnionFind uf = new UnionFind(grid.length * grid[0].length + 1); //最后一位统计有多少卡住的泡泡
        int AboveAll = grid.length * grid[0].length;
        for(int i = 0; i < grid.length; i++){   //如果该位中有泡泡，连接他和他附近的泡泡，加入union
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    connectSurround(grid, i, j, uf);
                }
            }
        }

        for (int i = 0; i < grid[0].length; i++){  //连入最后一位，统计
            uf.union(i,AboveAll);
        }
        return uf;
    }

    public void connectSurround(int[][] grid, int row, int col, UnionFind uf){ //将一个点和其他附近的连接
        int[][] dxy = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for(int i = 0; i < 4; i++){
            int rowPos = row + dxy[i][0];
            int colPos = col + dxy[i][1];
            if(inGrid(grid, rowPos, colPos) && grid[rowPos][colPos] == 1){
                uf.union(Index(grid, row, col), Index(grid, rowPos, colPos));
            }
        }

    }

    public boolean inGrid(int[][] grid, int row, int col){
        if(row >= 0 && col >=0 && row < grid.length && col < grid[0].length){
            return true;
        }
        return false;
    }

    public int Index(int[][] grid, int row, int col){
        int index = row * grid[0].length + col;
        return index;
    }



}
