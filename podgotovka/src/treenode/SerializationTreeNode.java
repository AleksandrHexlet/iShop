package treenode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializationTreeNode {

//   private TreeNode treeNode;
//
//    public SerializationTreeNode(TreeNode treeNode) {
//        this.treeNode = treeNode;
//    }


    public String serializeTrenode(TreeNodeSerialization treeNode) {
        if (treeNode == null) return "null";
        String finalStr = treeNode.value + "";
        String strLeft = serializeTrenode(treeNode.left);
        String strRight = serializeTrenode(treeNode.right);
        finalStr = finalStr+ "_" + strLeft + "_" + strRight;

        return finalStr;
    }


    public TreeNodeSerialization recursion(Queue<String> queueStr) {
        String valueStr = queueStr.poll();
        if (valueStr.equals("null")) return null;
        TreeNodeSerialization treeNodeSerialization = new TreeNodeSerialization(Integer.valueOf(valueStr));
        treeNodeSerialization.left = recursion(queueStr);
        treeNodeSerialization.right = recursion(queueStr);

        return treeNodeSerialization;
    }


    public TreeNodeSerialization deSerializeTrenode(String str) {
        Queue<String> queueStr = new LinkedList<>(Arrays.asList(str.split("_")));
        return recursion(queueStr);
    }

    ;


    public static class TreeNodeSerialization {
        private TreeNodeSerialization left;
        private TreeNodeSerialization right;
        private int value;

        public TreeNodeSerialization(int value) {
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
