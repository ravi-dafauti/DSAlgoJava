package disjointset;

//Union by Size implementation.
public class DisjointSet {

    private int[] set;
    private int size;

    public DisjointSet(int size) {
        this.set = new int[size];
        this.size = size;
        makeSet();
    }

    // creates size number of DS
    public void  makeSet() {
        for(int i = 0; i < size; i++) {
            set[i] =  -1;
        }
    }

    //finds name of set for an element and does path compression.
    public int find(int x) {
        if(set[x] < 0)
            return x;
        int root = find(set[x]);
        set[x] = root;
        return root;
    }

    public void union(int x, int y) throws Exception{
        if((x < 0 || x > size - 1) || (y < 0 || y > size - 1))
            throw new Exception("one of the element is not in any set");
        int rootX = find(x);
        int rootY = find(y);
        if((rootX == rootY))
            throw new Exception("Elements are in same set, can't perform union");
        if(set[rootX] < set[rootY]) {
            set[rootX] = set[rootX] + set[rootY];
            set[rootY] = rootX;
        } else {
            set[rootY] = set[rootX] + set[rootY];
            set[rootX] = rootY;
        }
    }


    public static void main(String[] args) throws  Exception{
        DisjointSet disjointSet = new DisjointSet(5);


        //System.out.println(disjointSet.find(4));
        disjointSet.union(2,4);
        disjointSet.union(3,4);
        disjointSet.union(2,1);
        for(int i = 0; i < 5; i++) {
            System.out.println(disjointSet.find(i));
        }

    }
}
