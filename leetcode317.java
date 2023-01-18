class Solution {
    private int[] rowDir = {0,0,1,-1};
    private int[] colDir = {1,-1,0,0};
    
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0)    return -1;
        int rows = grid.length, cols = grid[0].length;
        
        int[][] canReach = new int[rows][cols];// 每个空地可以reach多少个1
        int[][] dist = new int[rows][cols];// 去到所有的1距离是多少
        
        int totalBuildings = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 1){
                    totalBuildings++;
                    if(!bfs(grid, i, j, canReach, dist))    return -1;
                }
            }
        }
        
        int minDist = Integer.MAX_VALUE;
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                if(canReach[i][j] == totalBuildings && dist[i][j] < minDist){
                    minDist = dist[i][j];
                }
            }
        }
        
        return minDist == Integer.MAX_VALUE ? -1:minDist;
    }
    
    private boolean bfs(int[][] grid, int row, int col, int[][] canReach, int[][] dist){
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        int d = 0;
        q.offer(new int[]{row, col});
        visited[row][col] = true;
        
        while(!q.isEmpty()){
            d++;
            int size = q.size();
            for(int i=0; i < size; i++){
                int[] cur = q.poll();
                for(int k=0; k < 4; k++){
                    int rr = rowDir[k] + cur[0];
                    int cc = colDir[k] + cur[1];
                    
                    if(!isValid(grid, rr, cc, visited))  continue;
                    if(grid[rr][cc] == 0){
                        q.offer(new int[]{rr,cc});
                        dist[rr][cc] += d;
                        canReach[rr][cc]++;
                    }
                    visited[rr][cc] = true;

                }
            }
        }
        
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isValid(int[][] grid, int rr, int cc, boolean[][] visited){
        if(rr < 0 || rr > grid.length-1 || cc < 0 || cc > grid[0].length-1)
            return false;
        if(visited[rr][cc]) return false;
        if(grid[rr][cc] == 2)   return false;
        return true;
    }
}

class Solution {
    private int[] rowDir = {1, -1, 0, 0};
    private int[] colDir = {0, 0, 1, -1};
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int rows = grid.length, cols = grid[0].length;
        int[][] canReach = new int[rows][cols]; // 每个空地可以reach多少个1
        int[][] dist = new int[rows][cols]; // 去到所有的1距离是多少
        
        int totalBuildings = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    totalBuildings++;
                    if (!bfs(grid, i, j, dist, canReach)) return -1;
                }
            }
        }
        
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (canReach[i][j] == totalBuildings &&
                   dist[i][j] < minDis) {
                    minDis = dist[i][j];
                }
            }
        }
        
        return minDis == Integer.MAX_VALUE ? -1 : minDis;
    }
    
    private boolean bfs(int[][] grid, int row, int col, int[][] dist, int[][] canReach) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        visited[row][col] = true;
        
        int d = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            d++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                for (int k = 0; k < 4; k++) {
                    int rr = rowDir[k] + r;
                    int cc = colDir[k] + c;
                    if (!isValid(grid, rr, cc, visited)) continue;                   
                    if (grid[rr][cc] == 0) {
                        dist[rr][cc] += d;
                        canReach[rr][cc]++;
                        q.offer(new int[]{rr, cc});                        
                    }
                    visited[rr][cc] = true;
                }
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isValid(int[][] grid, int rr, int cc, boolean[][] visited) {
        if (rr > grid.length - 1 ||
           rr < 0 || cc < 0 || cc > grid[0].length - 1) return false;
        if (visited[rr][cc]) return false;
        if(grid[rr][cc] == 2) return false;
        
        return true;
    }
}

class Solution {
    private int[] rowDir = {0,0,1,-1};
    private int[] colDir = {1,-1,0,0};
    
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0)    return -1;
        int rows = grid.length, cols = grid[0].length;
        
        int[][] canReach = new int[rows][cols];// 每个空地可以reach多少个1
        int[][] dist = new int[rows][cols];// 去到所有的1距离是多少
        
        int totalBuildings = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 1){
                    totalBuildings++;
                    bfs(grid, i, j, canReach, dist);
                }
            }
        }
        
        int minDist = Integer.MAX_VALUE;
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                if(canReach[i][j] == totalBuildings && dist[i][j] < minDist){
                    minDist = dist[i][j];
                }
            }
        }
        
        return minDist == Integer.MAX_VALUE ? -1:minDist;
    }
    
    private void bfs(int[][] grid, int row, int col, int[][] canReach, int[][] dist){
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        int d = 0;
        q.offer(new int[]{row, col});
        visited[row][col] = true;
        
        while(!q.isEmpty()){
            d++;
            int size = q.size();
            for(int i=0; i < size; i++){
                int[] cur = q.poll();
                for(int k=0; k < 4; k++){
                    int rr = rowDir[k] + cur[0];
                    int cc = colDir[k] + cur[1];
                    
                    if(!isValid(grid, rr, cc, visited))  continue;
                    visited[rr][cc] = true;
                    q.offer(new int[]{rr,cc});
                    dist[rr][cc] += d;
                    canReach[rr][cc]++;
                }
            }
        }
    }
    
    private boolean isValid(int[][] grid, int rr, int cc, boolean[][] visited){
        if(rr < 0 || rr > grid.length-1 || cc < 0 || cc > grid[0].length-1)
            return false;
        if(visited[rr][cc]) return false;
        if(grid[rr][cc] != 0)   return false;
        return true;
    }
}