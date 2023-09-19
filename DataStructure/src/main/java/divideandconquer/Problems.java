package divideandconquer;

import java.util.*;

public class Problems {

    /*

    Given n rectangular buildings in a 2-dimensional city, computes the skyline of these buildings, eliminating hidden lines. The main task is to view buildings from a side and remove all sections that are not visible.

All buildings share common bottom and every building is represented by triplet (left, ht, right)

‘left’: is x coordinated of left side (or wall).
‘right’: is x coordinate of right side
‘ht’: is height of building.
A skyline is a collection of rectangular strips. A rectangular strip is represented as a pair (left, ht) where left is x coordinate of left side of strip and ht is height of strip.

     */

    public static class BuildingPoint {
        int x;
        int y;
        int s;
        public BuildingPoint(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    public static void skyLine(int[][] buildings) {

        BuildingPoint[] arr = new BuildingPoint[buildings.length * 2];
        for(int i = 0, k = 0; i < buildings.length; i++) {
            arr[k++] = new BuildingPoint(buildings[i][0], buildings[i][2], 1);
            arr[k++] = new BuildingPoint(buildings[i][1], buildings[i][2], -1);
        }
        Arrays.sort(arr, ((o1, o2) -> {
            if(o1.x - o2.x == 0) {
                if((o1.s == 1 && o2.s == 1)) {
                    return o2.y - o1.y;
                } else if ((o1.s == -1 && o2.s == -1)) {
                    return o1.y - o2.y;
                } else {
                    if(o1.s == 1)
                        return -1;
                    else
                        return 1;
                }
            }
            return o1.x - o2.x;
        }));


        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int maxHeight = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].s == 1) {
                if(map.containsKey(arr[i].y)) {
                    map.put(arr[i].y, map.get(arr[i].y) + 1);
                } else {
                    map.put(arr[i].y, 1);
                }

                int currMaxHeight = map.lastKey();
                if(currMaxHeight > maxHeight) {
                    System.out.println(arr[i].x + " " + arr[i].y);
                    maxHeight = currMaxHeight;
                }
            } else {
                int currHeightCount = map.get(arr[i].y);
                if(currHeightCount <= 1) {
                    map.remove(arr[i].y);
                } else {
                    map.put(arr[i].y, currHeightCount - 1);
                }
                int currMaxHeight = map.lastKey();
                if(currMaxHeight < maxHeight) {
                    maxHeight = currMaxHeight;
                    System.out.println(arr[i].x + " " + maxHeight);
                }
            }
        }
    }


    // convert array of 2n a1a2a3a4b1b2b3b4 to a1b1a2b2a3b3a4b4


    public static void jumbleArray(int[] arr) {
        if(arr.length <= 2)
            return;
        convert(arr, 0, arr.length - 1);
    }

    public static void convert(int[] arr, int l, int h) {
        if(l >= h)
            return;
        if(l + 1 == h)
            return;
        int mid = l + (h - l) / 2;
        int leftMid = l + (mid - l)/2;
        int i = leftMid + 1;
        int j = mid + 1;
        for(int k = leftMid + 1 ; k <= mid; k++) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j++;
        }
        convert(arr, l , mid);
        convert(arr, mid + 1, h);
    }


    // Write an efficient program to find the sum of the contiguous subarray within a one-dimensional array of numbers that has the largest sum.

    public static int maximumSumContinous(int[] arr) {
        if(arr.length == 0)
            return Integer.MIN_VALUE;
        return maxSum(arr, 0, arr.length - 1);
    }

    public static int maxSum(int[] arr, int l, int h) {
        if(l > h)
            return Integer.MIN_VALUE;
        if(l == h)
            return arr[l];
        int mid = l + (h - l)/2;
        int leftMax = maxSum(arr, l, mid);
        int rightMax = maxSum(arr, mid + 1, h);
        int sum = 0;
        int currentLeftMax = Integer.MIN_VALUE;
        int currentRightMax = Integer.MIN_VALUE;
        for(int i = mid; i>=l; i--) {
            sum += arr[i];
            if(sum > currentLeftMax) {
                currentLeftMax = sum;
            }
        }
        sum = 0;
        for(int i = mid + 1; i<=h; i++) {
            sum += arr[i];
            if(sum > currentRightMax) {
                currentRightMax = sum;
            }
        }
        return Math.max(currentLeftMax + currentRightMax,leftMax > rightMax ? leftMax : rightMax);
    }

    //We are given an array of n points in the plane, and the problem is to find out the closest pair of points in the array.

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double bruteForce(Point[] P, int l, int h) {
        double min = Double.MAX_VALUE;
        double currMin = 0;
        for (int i = l; i < h; ++i) {
            for (int j = i + 1; j <= h; ++j) {
                currMin = distance(P[i], P[j]);
                if (currMin < min) {
                    min = currMin;
                }
            }
        }
        return min;
    }

    public static double findClosetDistance(Point[] arr) {
        if(arr.length <= 1)
            return Double.MAX_VALUE;
        Arrays.sort(arr, (a, b) -> a.x - b.x);
        return closestUtil(arr, 0, arr.length - 1);
    }

    public static double closestUtil(Point[] arr, int l, int h) {
        if(h - l + 1 <= 3)
            return bruteForce(arr, l, h);
        int mid = l + (h - l)/2;
        double leftClosestDistance = closestUtil(arr, l, mid);
        double rightClosestDistance = closestUtil(arr, mid, h);
        double closest = Math.min(leftClosestDistance, rightClosestDistance);
        Point[] strip = new Point[arr.length];
        int i = 0;
        for(int j = l; j<= h; j++) {
            if(Math.abs(arr[j].x - arr[mid].x) < closest){
                strip[i++] = arr[j];
            }
        }
        return Math.min(closest, stripClosest(strip, i, closest));


    }

    public static double stripClosest(Point[] arr, int size, double d) {
        Arrays.sort(arr, 0, size, (a, b) -> a.y - b.y);
        double stripMinimum = Double.MAX_VALUE;
        for(int i = 0; i < size; i++) {
            for(int j = i + 1; j < size && (arr[j].y - arr[i].y) <= d; j++) {
                stripMinimum = stripMinimum > distance(arr[i], arr[j]) ? distance(arr[i], arr[j]) : stripMinimum;
            }
        }
        return stripMinimum;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)));
    }


    // matrix multiplication code iterative

    //Matrix A m * n , Matrix B p * q
    public static void multiplyMatrix(int[][] A, int[][] B, int m, int n, int p, int q) {

        if(n != p) {
            System.out.println("multiplication not possible as no of columns of 1st matrix must be equal to number of rows of 2nd matrix");
        }
        int[][] res = new int[m][];
        for(int i = 0; i < m; i++) {
            res[i] = new int[q];
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < q; j++) {
                res[i][j] = 0;
                for(int k = 0; k < n; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < q; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println("");
        }
    }


    public static void main(String[] args) {

        int matrix1[][] = { { 2, 4 }, { 3, 4 } };
        int matrix2[][] = { { 1, 2 }, { 1, 3 } };


        multiplyMatrix(matrix1, matrix2, 2, 2, 2, 2);

    }


    //Leetcode 427. Construct Quad Tree
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    };
    public Node construct(int[][] grid) {
        return constructHelper(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    enum Type {
        ZERO,
        ONE,
        MIX;
    }

    public Type getTypeOfGrid(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        boolean one = false;
        boolean zero = false;
        for(int i = rowStart; i <= rowEnd; i++) {
            for(int j = colStart; j <= colEnd; j++) {
                if(grid[i][j] == 1) {
                    if(zero == true)
                        return Type.MIX;
                    else
                        one = true;
                } else {
                    if(one == true)
                        return Type.MIX;
                    else
                        zero = true;
                }
            }
        }

        return one == true ? Type.ONE : Type.ZERO;
    }

    public Node constructHelper(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        Type x = getTypeOfGrid(grid, rowStart, rowEnd, colStart, colEnd);
        if(rowStart == rowEnd || x != Type.MIX) {
            if(x == Type.ONE) {
                return new Node(true, true);
            } else {
                return new Node(false, true);
            }
        }
        Node root = new Node();
        root.isLeaf = false;
        root.val = false;
        root.topLeft = constructHelper(grid, rowStart, rowStart + (rowEnd - rowStart)/ 2, colStart, colStart + (colEnd - colStart)/ 2);
        root.topRight = constructHelper(grid, rowStart, rowStart + (rowEnd - rowStart)/ 2, colStart + (colEnd - colStart)/ 2 + 1, colEnd);
        root.bottomLeft = constructHelper(grid, rowStart + (rowEnd - rowStart)/ 2 + 1, rowEnd, colStart, colStart + (colEnd - colStart)/ 2);
        root.bottomRight = constructHelper(grid, rowStart + (rowEnd - rowStart)/ 2 + 1, rowEnd, colStart + (colEnd - colStart)/ 2 + 1, colEnd);
        return root;
    }
}
