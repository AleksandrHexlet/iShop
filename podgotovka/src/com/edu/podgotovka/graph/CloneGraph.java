package com.edu.podgotovka.graph;

import java.util.List;

public class CloneGraph {
        public static class Node{
        int value;
        List<Node> neighbors;

        public Node(int value, List<Node> neighbors) {
            this.value = value;
            this.neighbors = neighbors;
        }

            public Node(int value) {
                this.value = value;
            }
        }
//    private  static List<Node> copyNeighbors(Node node){
//        for (Node neighbor: node.neighbors) {
//            Node newListNeighbor = new Node(neighbor.value,
//
//                    neighbor.neighbors);
//            newNode.neighbors.add(neighbor);
//    };
//
//    public static Node getHeadCloneGraph(Node node){
//        if(node == null) return null;
//        Node newNode = new Node(node.value);
//
//        };




//    }

}
