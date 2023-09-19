package tree;

import linkedlist.schema.ListNode;

import java.util.*;

public class LeetCode {



     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    public int maxDepth(TreeNode root) {
         if(root == null)
             return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if(t != null) {
                if(t.left != null)
                    queue.offer(t.left);
                if(t.right != null)
                    queue.offer(t.right);
            } else {
                maxDepth++;
                if(!queue.isEmpty())
                    queue.offer(null);
            }
        }
        return maxDepth;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
         if(p == null && q == null)
             return true;
         if(p == null || q == null)
             return false;
         return p.val == q.val ? (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)): false;
    }

    public TreeNode invertTree(TreeNode root) {
         if(root == null)
             return null;
         TreeNode leftInvert = invertTree(root.left);
         TreeNode rightInvert = invertTree(root.right);
         root.left = rightInvert;
         root.right = leftInvert;
         return root;
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {

         if(root == null)
             return false;
         if(isLeaf(root) && root.val == targetSum)
             return true;
         return hasPathSum(root.left, targetSum - root.val) ? true : hasPathSum(root.right, targetSum - root.val);

    }

    public boolean isLeaf(TreeNode node) {
         if(node != null && node.left == null && node.right == null)
             return true;
         return false;
    }

    public boolean isSymmetric(TreeNode root) {
         if(root == null)
             return true;
         if(root.left == null && root.right == null)
             return true;
         if(root.left == null || root.right == null)
             return false;
         return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode a, TreeNode b) {
         if(a == null && b == null)
             return true;
         if(a == null || b == null)
             return false;
        return a.val == b.val ? (isMirror(a.left, b.right) && isMirror(a.right, b.left)): false;
    }



    int sum = 0;
    public int sumNumbers(TreeNode root) {
        StringBuilder s = new StringBuilder();
        sumNumbersRecur(root, s);
        return sum;
    }

    public void sumNumbersRecur(TreeNode root, StringBuilder s) {
         if(root == null)
             return;
         s.append(root.val);
         if(isLeaf(root)) {
             sum = sum + Integer.valueOf(s.toString());
             s.deleteCharAt(s.length() - 1);
             return;
         }
         if(root.left != null)
             sumNumbersRecur(root.left, s);
         if(root.right != null){
             sumNumbersRecur(root.right, s);
         }
         s.deleteCharAt(s.length() - 1);
    }


    public void flatten(TreeNode root) {
        flattenRecur(root);
    }

    public TreeNode flattenRecur(TreeNode root) {

        if(root == null)
            return null;

        TreeNode left = flattenRecur(root.left);
        TreeNode right = flattenRecur(root.right);
        root.right = left;
        root.left = null;
        TreeNode itr = root;
        while (itr.right != null)
            itr = itr.right;
        itr.right = right;
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {
        if(root == null)
            return null;
        if(root.left != null) {
            if(root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = getLastLeftNode(root.next);
            }
        }
        if(root.right != null) {
            root.right.next = getLastLeftNode(root.next);
        }
        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node getLastLeftNode(Node root) {
        if(root == null)
            return root;
        if(root.left != null)
            return root.left;
        if(root.right != null)
            return root.right;
        return getLastLeftNode(root.next);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null)
            return null;
        if(root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null)
            return right;
        if(right == null)
            return left;
        return root;
    }

/*    int index = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeRecur(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeRecur(int[] preorder, int[] inorder, int l, int r) {
        if(l > r)
            return null;
        if(l == r) {
            return new TreeNode(preorder[index++]);
        }
        int i = l;
        for( ; i <=r ; i++){
            if(inorder[i] == preorder[index])
                break;
        }
        TreeNode root = new TreeNode(preorder[index++]);
        root.left = buildTreeRecur(preorder, inorder, l, i - 1);
        root.right = buildTreeRecur(preorder, inorder, i + 1, r);
        return root;
    }*/

    int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        index = postorder.length - 1;
        return buildTreeRecur(inorder, postorder, 0, inorder.length - 1);

    }

    public TreeNode buildTreeRecur(int[] inorder, int[] postorder, int l, int r) {
        if(l > r)
            return null;
        if(l == r) {
            return new TreeNode(postorder[index--]);
        }
        int i = l;
        for( ; i <=r ; i++){
            if(inorder[i] == postorder[index])
                break;
        }
        TreeNode root = new TreeNode(postorder[index--]);
        root.left = buildTreeRecur(inorder, postorder, l, i - 1);
        root.right = buildTreeRecur(inorder, postorder, i + 1, r);
        return root;
    }


    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int count = 0;
        double sum = 0;
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if(t != null) {
                sum = sum + t.val;
                count++;
                if(t.left != null)
                    queue.offer(t.left);
                if(t.right != null)
                    queue.offer(t.right);
            } else {
                result.add(sum / count);
                sum = 0;
                count = 0;
                if(!queue.isEmpty()) {
                    queue.offer(null);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                if(queue.peek().left != null)
                    queue.offer(queue.peek().left);
                if(queue.peek().right != null)
                    queue.offer(queue.peek().right);
                list.add(queue.poll().val);
            }
            result.add(list);
        }
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int element = 0;
            for(int i = 0; i < size; i++) {
                if(queue.peek().left != null)
                    queue.offer(queue.peek().left);
                if(queue.peek().right != null)
                    queue.offer(queue.peek().right);
                element = queue.poll().val;
            }
            result.add(element);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int dir = 1;
        stack1.push(root);
        List<Integer> list = new ArrayList<>();
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            TreeNode t = stack1.peek();
            list.add(t.val);
            stack1.pop();
            if(dir == 1) {
                if(t.left != null)
                    stack2.push(t.left);
                if(t.right != null)
                    stack2.push(t.right);
            } else {
                if(t.right != null)
                    stack2.push(t.right);
                if(t.left != null)
                    stack2.push(t.left);
            }
            if(stack1.isEmpty()) {
                result.add(list);
                list = new ArrayList<>();
                Stack<TreeNode> stack3 = stack1;
                stack1 = stack2;
                stack2 = stack3;
                dir = -dir;
            }
        }
        return result;
    }

    int count;

    public int kthSmallest(TreeNode root, int k) {
        int count = 0;
        return kthSmallestHelper(root, k);
    }

    public int kthSmallestHelper(TreeNode root, int k) {
        if(root == null)
            return -1;
        int left = kthSmallestHelper(root.left, k);
        if(left != -1)
            return left;
        count++;
        if(count == k)
            return root.val;
        return kthSmallestHelper(root.right, k);
    }


    TreeNode prev= null;
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;

        boolean isLeftBst = isValidBST(root.left);
        if(isLeftBst == false)
            return false;
        if(prev != null && prev.val > root.val) {
            return false;
        }
        prev = root;
        return isValidBST(root.right);
    }

    public int getMinimumDifference(TreeNode root) {
        if(root == null)
            return -1;
        int minDiffLeft = getMinimumDifference(root.left);
        int minDiffRight = getMinimumDifference(root.right);
        TreeNode maxLeft = getMax(root.left);
        TreeNode minRight = getMin(root.right);
        int minDiffRoot = Integer.MAX_VALUE;
        if(maxLeft != null && minDiffRoot > Math.abs(maxLeft.val - root.val)) {
            minDiffRoot = Math.abs(maxLeft.val - root.val);
        }
        if(minRight != null && minDiffRoot > Math.abs(minRight.val - root.val)) {
            minDiffRoot = Math.abs(minRight.val - root.val);
        }

        if(minDiffLeft != -1 && minDiffRoot > minDiffLeft)
            minDiffRoot = minDiffLeft;
        if(minDiffRight != -1 && minDiffRoot > minDiffRight)
            minDiffRoot = minDiffRight;
        return minDiffRoot;
    }

    public TreeNode getMax(TreeNode root) {
        if(root == null)
            return null;
        while (root.right != null){
            root = root.right;
        }
        return root;
    }

    public TreeNode getMin(TreeNode root) {
        if(root == null)
            return null;
        while (root.left != null){
            root = root.left;
        }
        return root;
    }


    class BSTIterator {

        TreeNode prev;
        List<Integer> inorder = new ArrayList<>();
        int itr;

        public BSTIterator(TreeNode root) {
            fillInorder(root);
            itr = 0;
        }

        public void fillInorder(TreeNode root) {
            if(root == null)
                return;
            fillInorder(root.left);
            inorder.add(root.val);
            fillInorder(root.right);
        }

        public int next() {
            return inorder.get(itr++);
        }

        public boolean hasNext() {
            if(itr >= inorder.size())
                return false;
            return true;
        }
    }


/*    public int maxPathSum(TreeNode root) {

        if(root == null)
            return Integer.MIN_VALUE;

        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);

        int leftMax = getMaxSumFromRoot(root.left);
        int rightMax = getMaxSumFromRoot(root.right);

        int max = left;
        if(right != Integer.MIN_VALUE && right > max)
            max = right;
        if(max < leftMax + root.val)
            max = leftMax + root.val;
        if(max < rightMax + root.val)
            max = rightMax + root.val;
        if(max < rightMax + leftMax + root.val)
            max = rightMax + leftMax + root.val;
        return max;
    }

    int maxSum = 0;
    public int getMaxSumFromRoot(TreeNode root) {
        maxSum = 0;
        maxPathSumFromRootToLeaf(root, 0);
        return maxSum;
    }

    public void maxPathSumFromRootToLeaf(TreeNode root, int sum) {
        if(root == null)
            return;
        if (sum + root.val > maxSum)
            maxSum = sum + root.val;
        maxPathSumFromRootToLeaf(root.left, sum + root.val);
        maxPathSumFromRootToLeaf(root.right, sum + root.val);
    }*/


    private int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if(root.left == null && root.right == null) return root.val;
        helper(root);
        return maxPath;
    }

    private int helper(TreeNode node) {
        if(node == null) return 0;

        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));

        maxPath = Math.max(node.val + left + right, maxPath);
        return node.val + Math.max(left, right);
    }




    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

/*    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int diameterWithRoot = depthOfTree(root.left) + depthOfTree(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        int diameter = Math.max(leftDiameter, rightDiameter);
        diameter = Math.max(diameter, diameterWithRoot);
        return diameter;
    }

    public int depthOfTree(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int depth = 0;
        while(!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if(t != null) {
                if(t.left != null)
                    queue.offer(t.left);
                if(t.right != null)
                    queue.offer(t.right);
            } else {
                depth++;
                if(!queue.isEmpty())
                    queue.offer(null);
            }
        }
        return depth;
    }*/

//Better solution for diameter of tree
    public int diameterOfBinaryTree(TreeNode root) {

        // Create an array to hold the diameter of the tree
        int diameter[] = new int[1];

        // Recursively calculate the height of the tree and update the diameter array
        height(root,diameter);

        // Return the diameter of the tree
        return diameter[0];
    }

    public int height(TreeNode root, int diameter[]){

        // Base case: if the root is null, the height is 0
        if(root == null){
            return 0;
        }

        // Recursively calculate the height of the left and right subtrees
        int left = height(root.left,diameter);
        int right = height(root.right,diameter);

        // Update the diameter array by taking the maximum diameter that passes through the current node
        diameter[0] = Math.max(diameter[0],left + right);

        // Return the maximum depth of the current node by adding 1 to the maximum depth of its deepest subtree
        return Math.max(left,right)+1;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return getBst(nums, 0, nums.length - 1);
    }

    public TreeNode getBst(int[] nums, int l , int h) {
        if(l > h)
            return null;
        if(l == h) {
            return new TreeNode(nums[l]);
        }

        int mid = l + (h - l)/2;
        TreeNode t = new TreeNode(nums[mid]);
        t.left = getBst(nums, l, mid - 1);
        t.right = getBst(nums, mid + 1, h);
        return t;
    }

    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        int nonLeafNodes = 0;
        boolean isLastNonLeafPartial = false;
        while(!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if(t != null) {
                if(t.left == null && t.right == null) {
                    break;
                }
                nonLeafNodes++;
                if(t.left != null) {
                    queue.offer(t.left);
                } else {
                    break;
                }
                if(t.right != null){
                    queue.offer(t.right);
                } else {
                    isLastNonLeafPartial = true;
                    break;
                }

            } else {
                if(!queue.isEmpty()){
                    queue.offer(null);
                }
            }
        }

        if(isLastNonLeafPartial) {
            return 2 * nonLeafNodes;
        }
        return 2 * nonLeafNodes + 1;

    }


    int countSumHelper;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null)
            return 0;
        int left = pathSum(root.left, targetSum);
        int right = pathSum(root.right, targetSum);
        int pathSum = 0;
        if(root.val == targetSum) {
            pathSum = 1;
        }
        countSumHelper = 0;
        pathSumHelper(root.left, targetSum - root.val);
        pathSum += countSumHelper;
        countSumHelper = 0;
        pathSumHelper(root.right, targetSum - root.val);
        pathSum += countSumHelper;
        pathSum += left + right;
        return pathSum;
    }

    public void pathSumHelper(TreeNode root , long targetSum) {
        if(root == null)
            return;
        if(root.val == targetSum)
           countSumHelper++;
        pathSumHelper(root.left, targetSum - root.val);
        pathSumHelper(root.right, targetSum - root.val);
    }
}
