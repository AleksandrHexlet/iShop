package treenode;


public class TreeNodeMain {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        TreeNodeMain.getDepthForBalance(treeNode);
//        TreeNodeMain.getDepth(treeNode);
    }

    static int depth = 0;

    public static TreeNode invert(TreeNode treeNode) {
        if (treeNode == null) return null;
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;

        invert(treeNode.left);
        invert(treeNode.right);
        return treeNode;
    }

    public static int getDepth(TreeNode treeNode) {
        if (treeNode == null) return 0;
        int depthRight = getDepth(treeNode.right);
        int depthLeft = getDepth(treeNode.left);
        return Math.max(depthLeft, depthRight) + 1;
    }

    public static int getDepthForBalance(TreeNode treeNode) {
        if (treeNode == null) return 0;
        int depthRight = getDepthForBalance(treeNode.right);
        int depthLeft = getDepthForBalance(treeNode.left);
        if (Math.abs(depthLeft - depthRight) > 1) return -1;
        return Math.max(depthLeft, depthRight) + 1;
    }

    ;

    public static boolean isBalance(TreeNode treeNode) {
        if (treeNode == null) return false;
        int res = TreeNodeMain.getDepthForBalance(treeNode);
        return res != -1;
    }

    private static boolean isValidBinaryTree(TreeNode treeNode, int min, int max) {
        if (treeNode == null) return true;
        if (treeNode.value <= min || treeNode.value >= max) return false;
        boolean isValidLeft = isValidBinaryTree(treeNode.left, min, treeNode.value);
        boolean isValidRight = isValidBinaryTree(treeNode.right, treeNode.value, max);
        return isValidLeft && isValidRight;
    }

    ;

    public static boolean isValidBinarySearchTree(TreeNode treeNode) {
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        return isValidBinaryTree(treeNode, min, max);

    }


    public static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int value;

        public TreeNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", value=" + value +
                    '}';
        }
    }
}
