package graph;

import java.util.*;

public class LeetCode {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            if(graph[prerequisites[i][1]] == null) {
                graph[prerequisites[i][1]] = new ArrayList<>();
            }
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            count++;
            if(graph[u] != null) {
                for(int v : graph[u]) {
                    if(--inDegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }

        return count == numCourses ? true : false;
    }


    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            if(graph[prerequisites[i][1]] == null) {
                graph[prerequisites[i][1]] = new ArrayList<>();
            }
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            res.add(u);
            count++;
            if(graph[u] != null) {
                for(int v : graph[u]) {
                    if(--inDegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }

        if(count != numCourses)
            return new int[0];

        int[] result = new int[res.size()];
        int i = 0;
        for(int a : res)
            result[i++] = a;
        return result;
    }


    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    result++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return result;
    }

    private int[] row_delta = {-1, 1, 0, 0};
    private int[] col_delta = {0, 0, -1, 1};
    public void dfs(char[][] grid, int i, int j, int m, int n) {
        if(i < 0 || j < 0 || i >= m || j >=n || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        for(int k = 0; k < 4; k++) {
            dfs(grid, i + row_delta[k], j + col_delta[k], m, n);
        }
    }

    public void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if((i == 0 || j == 0 || i == m - 1 || j == n - 1) && board[i][j] == 'O') {
                    dfsBorder(board, i, j, m, n);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++) {
                if(board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }

    public void dfsBorder(char[][] board, int i, int j, int m, int n) {
        if(i < 0 || j < 0 || i >= m || j >=n || board[i][j] == 'X' || board[i][j] == '$')
            return;
        board[i][j] = '$';
        for(int k = 0; k < 4; k++) {
            dfsBorder(board, i + row_delta[k], j + col_delta[k], m, n);
        }
    }


    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        Node[] visited = new Node[101];
        cloneUtil(node, visited);
        return visited[node.val];
    }

    public Node cloneUtil(Node node, Node[] visited) {

        if(visited[node.val] != null)
            return visited[node.val];
        visited[node.val] = new Node(node.val);
        for(Node neigh : node.neighbors) {
            if(visited[neigh.val] != null)
                visited[node.val].neighbors.add(visited[neigh.val]);
            else
                visited[node.val].neighbors.add(cloneUtil(neigh, visited));
        }
        return visited[node.val];
    }


    ////

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = orangesRottingBfs(grid, m , n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return result;
    }

    class Cell {
        int i;
        int j;
        Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public int orangesRottingBfs(int[][] grid, int m, int n) {

        Queue<Cell> queue = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(new Cell(i, j));
                }
            }
        }
        queue.offer(null);

        int minute = -1;

        while (!queue.isEmpty()) {
            Cell c = queue.poll();

            if(c != null) {
                if (c.i + 1 < m && grid[c.i + 1][c.j] == 1) {
                    queue.offer(new Cell(c.i + 1, c.j));
                    grid[c.i + 1][c.j] = 2;
                }
                if (c.i - 1 >= 0 && grid[c.i - 1][c.j] == 1) {
                    queue.offer(new Cell(c.i - 1, c.j));
                    grid[c.i - 1][c.j] = 2;
                }
                if (c.j + 1 < n && grid[c.i][c.j + 1] == 1) {
                    queue.offer(new Cell(c.i, c.j + 1));
                    grid[c.i][c.j + 1] = 2;
                }
                if(c.j - 1 >= 0 && grid[c.i][c.j - 1] == 1) {
                    queue.offer(new Cell(c.i, c.j - 1));
                    grid[c.i][c.j - 1] = 2;
                }
            } else {
                minute++;
                if(!queue.isEmpty()) {
                    queue.offer(null);
                }
            }
        }
        return minute;
    }


    /////

    public void nextPermutation(int[] nums) {
        if(nums.length == 1)
            return;
        int i = nums.length - 2;
        while (i >= 0) {
            if(nums[i] >= nums[i + 1])
                i--;
            else
                break;
        }
        if(i < 0) {
            reverse(nums, 0, nums.length - 1);
        } else {
            int j = nums.length - 1;
            while (j > i && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);
        }
    }

    public void swap(int[] nums, int i , int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    ////

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if(nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length)
                i++;
            else {
                int c = nums[i] - 1;
                if(c >= 0 && c < nums.length) {
                    if(nums[c] == nums[i])
                        i++;
                    else {
                        swap(nums, c , i);
                    }
                }
            }
        }
        int res = 0;
        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                res = i + 1;
                break;
            }
        }
        if(i == nums.length)
            res = i + 1;
        return res;
    }


    ///
    //Alien Dictionary


    public String findOrder(String [] dict, int N, int K)
    {

        int[][] graph = new int[K][K];

        if(N == 1)
            return dict[0];

        for(int i = 0; i < N - 1; i++) {
            int len = Math.min(dict[i].length(), dict[i + 1].length());
            int j = 0;
            while (j < len && dict[i].charAt(j) == dict[i + 1].charAt(j))
                j++;
            if(j == len)
                continue;
            graph[dict[i].charAt(j) - 'a'][dict[i + 1].charAt(j) - 'a'] = 1;
        }

        int[] indegree = new int[K];

        for(int i = 0; i < K; i++) {
            for(int j = 0; j < K; j++) {
                if(graph[i][j] == 1)
                    indegree[j]++;
            }
        }

        StringBuilder result = new StringBuilder();
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < K; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            count++;
            result = result.append((char) (u + 'a'));
            for(int v = 0; v < K; v++) {
                if(graph[u][v] == 1) {
                    if(--indegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }

        if(count != K)
            return "";

        return result.toString();

    }


    //1192. Critical Connections in a Network

    int dfsNumber = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for(int i = 0; i < connections.size(); i++) {
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        List<List<Integer>> result = new ArrayList<>();
        int[] visited = new int[n];
        int[] dfsNum = new int[n];
        int[] low = new int[n];

        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                criticalConnectionsUtils(n, graph, visited, dfsNum, low, i, -1, result);
            }
        }

        return result;
    }

    public void criticalConnectionsUtils(int n, List<List<Integer>> graph, int[] visited, int[] dfsNum, int[] low, int u, int parent, List<List<Integer>> result) {
        dfsNum[u] = dfsNumber;
        low[u] = dfsNumber;
        dfsNumber++;
        visited[u] = 1;
        for(int v : graph.get(u)) {
            if(parent == v)
                continue;
            if(visited[v] == 0) {
                criticalConnectionsUtils(n, graph, visited, dfsNum, low, v, u, result);
                if(low[v] > dfsNum[u]) {
                    result.add(Arrays.asList(u, v));
                }
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], dfsNum[v]);
            }
        }
    }


    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        for(int i = 0; i < graph.length; i++)
            color[i] = -1;
        for(int i = 0; i < graph.length; i++) {
            if(color[i] == -1) {
                if(!isBipartiteDfs(graph, color, i, 1))
                    return false;
            }
        }
        return true;
    }

    public boolean isBipartiteDfs(int[][] graph, int[] color, int u, int c) {
        if(color[u] != -1 && color[u] != c)
            return false;
        color[u] = c;
        for(int v : graph[u]) {
            if(color[v] == -1) {
                if(!isBipartiteDfs(graph, color, v, 1 - c)) {
                    return false;
                }
            } else {
                if(color[v] != 1 - c)
                    return false;
            }
        }
        return true;
    }


    //399. Evaluate Division

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map <String, Map<String, Double>> graph = new HashMap<>();
        createGraph(equations, values, graph);
        double[] result = new double[queries.size()];

        for(int i = 0; i < queries.size(); i++) {
            String s = queries.get(i).get(0);
            String d = queries.get(i).get(1);
            List<String> path = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            if(!graph.containsKey(s) || !graph.containsKey(d) || !dfsCal(graph, s, d, path, visited)) {
                result[i] = -1;
            } else {
                double res = 1;
                if(s.equals(d)) {
                    result[i] = 1;
                } else {
                    for(int p = 0 ; p < path.size() - 1; p++) {
                        double x = graph.get(path.get(p)).get(path.get(p + 1));
                        res *= x;
                    }
                    result[i] = res;
                }
            }
        }
        return result;
    }

    public boolean dfsCal( Map <String, Map<String, Double>> graph, String source, String destination, List<String> path, Set<String> visited ) {
        visited.add(source);
        path.add(source);
        if(source.equals(destination)) {
            return true;
        }
        Map<String, Double> adj = graph.get(source);

        for(Map.Entry<String, Double> entry : adj.entrySet()) {
            if(!visited.contains(entry.getKey())) {
                if(dfsCal(graph, entry.getKey(), destination, path, visited))
                    return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public void createGraph(List<List<String>> equations, double[] values, Map <String, Map<String, Double>> graph ) {
        for(int i = 0; i < values.length; i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            if(!graph.containsKey(u)) {
                graph.put(u, new HashMap<>());
            }
            graph.get(u).put(v, values[i]);
            if(!graph.containsKey(v))
                graph.put(v, new HashMap<>());
            graph.get(v).put(u, 1/values[i]);
        }
    }


    //433. Minimum Genetic Mutation

    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> bankSet = new HashSet<>();
        for(int i = 0; i < bank.length; i++)
            bankSet.add(bank[i]);
        int count = 0;
        queue.offer(startGene);
        char[] charArray = new char[] {'A', 'C', 'G', 'T'};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String gene = queue.poll();
                if(gene.equals(endGene)) {
                    return count;
                }
                for(int j = 0; j < 8; j++) {
                    StringBuilder mutation = new StringBuilder(gene);
                    char oldCharAtJ = mutation.charAt(j);
                    for (int k = 0; k < 4; k++) {
                        if(oldCharAtJ == charArray[k])
                            continue;
                        mutation.setCharAt(j, charArray[k]);
                        if(bankSet.contains(mutation.toString()) && !visited.contains(mutation.toString())) {
                            visited.add(mutation.toString());
                            queue.offer(mutation.toString());
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }


    //127. Word Ladder

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord))
            return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int count = 1;
        int n = beginWord.length();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String ladder = queue.poll();
                if(ladder.equals(endWord)) {
                    return count;
                }
                for(int j = 0; j < n; j++) {
                    char oldChar = ladder.charAt(j);
                    StringBuilder mutation = new StringBuilder(ladder);
                    for(int k = 0; k < 26; k++) {
                        char newchar = (char) ('a' + k);
                        if(oldChar == newchar)
                            continue;
                        mutation.setCharAt(j, newchar);
                        if(wordSet.contains(mutation.toString()) && !visited.contains(mutation.toString())) {
                            visited.add(mutation.toString());
                            queue.offer(mutation.toString());
                        }
                    }
                }

            }
            count++;
        }
        return 0;
    }


    //909. Snakes and Ladders
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] elements = new int[(n * n)+ 1];
        int[] visited = new int[(n * n) + 1];
        fillElements(board, n, elements);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int box = queue.poll();
                if(box == n * n)
                    return count;
                for(int j = 1; j <=6 && (box + j <= n * n) ; j++) {
                    int next = elements[box + j] == -1 ? box + j : elements[box + j];
                    if(visited[next] == 0) {
                        visited[next] = 1;
                        queue.offer(next);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public void fillElements(int[][] board, int n, int[] elements) {
        int leftToRight = 1;
        int k = 1;
        for(int i = n - 1; i >=0; i--) {
            int j = leftToRight == 1 ? 0 : n - 1;
            int count = 1;
            while (count <= n) {
                elements[k++] = board[i][j];
                if(leftToRight == 1)
                    j++;
                else
                    j--;
                count++;
            }
            leftToRight = 1 - leftToRight;
        }
    }


    //1926. Nearest Exit from Entrance in Maze

    public boolean canMove(char[][] maze , int x, int y, int m, int n) {
        if(x < 0 || y < 0 || x >= m || y >= n || maze[x][y] == '+')
            return false;
        return true;
    }

    public boolean isExit(char[][] maze , int x, int y, int m, int n, int[] entrance) {
        if(x == entrance[0] && y == entrance[1])
            return false;
        if(x == 0 || y == 0 || x == m - 1 || y == n - 1)
            return true;
        return false;
    }

    private int[] row_delta_maze = {-1, 1, 0, 0};
    private int[] col_delta_maze = {0, 0, -1, 1};


    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;
        queue.offer(new int[]{entrance[0], entrance[1]});
        maze[entrance[0]][entrance[1]] = '+';
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] mazeBox = queue.poll();
                if(isExit(maze, mazeBox[0], mazeBox[1], m, n, entrance))
                    return count;
                for(int k = 0; k < 4; k++) {
                    if(canMove(maze, mazeBox[0] + row_delta_maze[k], mazeBox[1] + col_delta_maze[k], m, n)) {
                        maze[mazeBox[0] + row_delta_maze[k]][mazeBox[1] + col_delta_maze[k]] = '+';
                        queue.offer(new int[]{mazeBox[0] + row_delta_maze[k], mazeBox[1] + col_delta_maze[k]});
                    }
                }
            }
            count++;
        }
        return -1;
    }


    //841. Keys and Rooms
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        canVisitAllRoomsDfs(rooms, 0, visited);
        for(int i = 0; i < visited.length; i++) {
            if(visited[i] == false)
                return false;
        }
        return true;
    }

    public void canVisitAllRoomsDfs(List<List<Integer>> rooms, int room, boolean[] visited) {
        visited[room] = true;
        for(Integer r : rooms.get(room)) {
            if(!visited[r]) {
                canVisitAllRoomsDfs(rooms, r, visited);
            }
        }
    }



    //547. Number of Provinces

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                provinces++;
                findCircleNumDfs(isConnected, i, visited);
            }
        }
        return provinces;
    }

    public void findCircleNumDfs(int[][] graph, int u, boolean[] visited) {
        visited[u] = true;
        for(int i = 0; i < graph.length; i++) {
            if(graph[u][i] == 1 && !visited[i]) {
                findCircleNumDfs(graph, i, visited);
            }
        }
    }


    //1466. Reorder Routes to Make All Paths Lead to the City Zero
    class Edge {
        int v;
        int weight;
        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
    int count = 0;
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            if(!graph.containsKey(u)) {
                graph.put(u, new ArrayList<>());
            }
            if(!graph.containsKey(v)) {
                graph.put(v, new ArrayList<>());
            }
            graph.get(u).add(new Edge(v, 1));
            graph.get(v).add(new Edge(u, 0));
        }
        dfsUtilMinReorder(graph, 0, -1);
        return count;
    }

    public void dfsUtilMinReorder(Map<Integer, List<Edge>> graph, int u, int parent) {
        for(Edge edge : graph.get(u)) {
            int child = edge.v;
            if(child != parent) {
                count += edge.weight;
                dfsUtilMinReorder(graph, child, u);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        //int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        //System.out.println(l.orangesRotting(grid));
        //int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        //System.out.println(l.snakesAndLadders(board));

        //startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
        //System.out.println(l.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));

        String s = Integer.toString(12);
        System.out.println(s.length());


    }
}
