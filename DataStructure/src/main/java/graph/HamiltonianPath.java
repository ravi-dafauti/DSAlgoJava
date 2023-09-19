package graph;

//works for both directed and undirect graph
class HamiltonianPath
{
    final int V = 5;
    int path[];

    /* A utility function to check if the vertex v can be
       added at index 'pos'in the Hamiltonian Path
       constructed so far (stored in 'path[]') */
    boolean isSafe(int v, int graph[][], int path[], int pos)
    {
        /* Check if this vertex is an adjacent vertex of
           the previously added vertex. */
        if(pos != 0 && graph[path[pos - 1]][v] == 0) {
            return false;
        }

        /* Check if the vertex has already been included.
           This step can be optimized by creating an array
           of size V */
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    /* A recursive utility function to solve hamiltonian
       path problem */
    boolean hamPathUtil(int graph[][], int path[], int pos)
    {
        /* base case: If all vertices are included in
           Hamiltonian Path */
        if (pos == V)
        {
            return true;
        }

        // Try different vertices as a next candidate in
        // Hamiltonian Path.
        for (int v = 0; v < V; v++)
        {
            /* Check if this vertex can be added to Hamiltonian
               Path */
            if (isSafe(v, graph, path, pos))
            {
                path[pos] = v;

                /* recur to construct rest of the path */
                if (hamPathUtil(graph, path, pos + 1) == true)
                    return true;

                /* If adding vertex v doesn't lead to a solution,
                   then remove it */
                path[pos] = -1;
            }
        }

        /* If no vertex can be added to Hamiltonian Path
           constructed so far, then return false */
        return false;
    }


    int hamPath(int graph[][])
    {
        path = new int[V];
        for (int i = 0; i < V; i++)
            path[i] = -1;

        if (hamPathUtil(graph, path, 0) == false)
        {
            System.out.println("\nSolution does not exist");
            return 0;
        }

        printSolution(path);
        return 1;
    }

    /* A utility function to print solution */
    void printSolution(int path[])
    {
        System.out.println("Solution Exists: Following" +
                " is one Hamiltonian Path");
        for (int i = 0; i < V; i++)
            System.out.print(" " + path[i] + " ");
    }

    // driver program to test above function
    public static void main(String args[])
    {
        HamiltonianPath hamiltonianPath =
                new HamiltonianPath();
        /* Let us create the following graph
           (0)-->(1)-->(2)
            ^   / ^  |
            |  /   \  |
            | V     | V
           (3)<-------(4)    */
        int graph1[][] =
                {{0, 1, 0, 0, 0},
                        {0, 0, 1, 1, 0},
                        {0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0},
                        {0, 1, 0, 1, 0},
                };

        // Print the solution
        hamiltonianPath.hamPath(graph1);

    }
}
// This code is contributed by Abhishek Shankhadhar
