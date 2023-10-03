package com.edu.podgotovka.tasks;

import com.edu.podgotovka.graph.Graph;

import java.util.*;

public class CourseShedule {
    public Map<Integer, List<Integer>> graph = new LinkedHashMap<>();

    public void createGraph(int[][] matrix){
        for (int[] ints : matrix) {
//            1-надо пройти  0-хотим
            if(!graph.containsKey(ints[1])){
                graph.put(ints[1],new ArrayList<>());
            }
            graph.get(ints[1]).add(ints[0]);
        }
    }

    public boolean hasCircle(int vertex, Set<Integer> currentVertex, Set<Integer> vizitedVertex){
      if(currentVertex.contains(vertex)) return true;
      if(vizitedVertex.contains(vertex)) return false;

      vizitedVertex.add(vertex);
      currentVertex.add(vertex);

      List<Integer> listNeighbors = graph.get(vertex);
        for (Integer neighbor : listNeighbors) {
            if(hasCircle(neighbor,currentVertex,vizitedVertex)) return  true;
        }
       currentVertex.remove(vertex);
       return  false;
    };

    public boolean isCircle(int numCourses, int[][] matrix){
        Set<Integer> currentVertex = new LinkedHashSet<>();
        Set<Integer> vizitedVertex = new LinkedHashSet<>();
        int countNotCircle = 0;

        for (Integer vertex : graph.keySet()) {
            if(!hasCircle(vertex,currentVertex,vizitedVertex))countNotCircle++;

        }
   return countNotCircle>=numCourses;
    }
}
