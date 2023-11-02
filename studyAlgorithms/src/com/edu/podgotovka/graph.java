package com.edu.podgotovka.graph;

import java.util.*;

public class Graph {

    Map<Vertex, List<Vertex>> vertexMap = new HashMap<>();

    public boolean addVertex(String city) {
        if (city == null) return false;
        Vertex vertexNew = new Vertex(city);
        if (!vertexMap.containsKey(vertexNew)) {
//
            vertexMap.put(new Vertex(city), new ArrayList<>());
//            vertexMap.putIfAbsent(new Vertex(city),new ArrayList<>());  putIfAbsent проверяет наличие ключа и при наличии не добавляет в мапу
        }
        return true;
    }

    public boolean deleteVertex(String city) {
        if (city == null) return false;
        Vertex vertexNew = new Vertex(city);
        Collection<List<Vertex>> vertexList = vertexMap.values();
        vertexList.forEach((list) -> list.removeIf((vertex -> vertexNew.equals(vertex))));
        vertexMap.remove(vertexNew);
        return true;
    }

    public boolean addEdge(String cityToAddTo, String cityToAdd, boolean isCross) { // куда и кого
        if (cityToAddTo == null || cityToAdd == null) return false;
        Vertex vertexToAddTo = new Vertex(cityToAddTo);
        Vertex vertexToAdd = new Vertex(cityToAdd);
        List<Vertex> listVertex = vertexMap.get(vertexToAddTo);
        if (listVertex != null) {
            listVertex.add(vertexToAdd);
        }
        if (isCross) {
            listVertex = vertexMap.get(vertexToAdd);
            if (listVertex != null) {
                listVertex.add(vertexToAddTo);
            }
        }
        return true;
    }

    public void deleteEdge(String cityFrom, String cityTo) { // откого и кого
        Vertex vertexFrom = new Vertex(cityFrom);
        Vertex vertexTo = new Vertex(cityTo);
        List<Vertex> listVertex = vertexMap.get(vertexFrom);
        if (listVertex != null) {
            listVertex.remove(vertexTo);
        }

        listVertex = vertexMap.get(vertexTo);
        if (listVertex != null) {
            listVertex.remove(vertexFrom);
        }
    }

    public void BFSprint(String startCity) { // в ширину
        Vertex newVertex = new Vertex(startCity);

        Set<Vertex> vertexSet = new LinkedHashSet<>();
        Queue<Vertex> queueVertex = new LinkedList<>();

        vertexSet.add(newVertex);
        queueVertex.add(newVertex);

        while (!queueVertex.isEmpty()) {
            Vertex vertInQueue = queueVertex.poll();
            List<Vertex> neighborsList = vertexMap.get(vertInQueue);
            for (Vertex neighbor : neighborsList) {
                if(!vertexSet.contains(neighbor)) {

                    vertexSet.add(neighbor);
                    queueVertex.add(neighbor);
                }
            }
        }
    }

    public void DFSalgorithm (String startCity){
//Stack
        Vertex newVertex = new Vertex(startCity);
        Set<Vertex> vertexSet = new LinkedHashSet<>();
        Stack<Vertex> vertexStack = new Stack<>();

        vertexStack.push(newVertex);

        while (!vertexStack.isEmpty()){
            Vertex oneMoreVertex = vertexStack.pop();

            if(!vertexSet.contains(oneMoreVertex)){
                vertexSet.add(oneMoreVertex);
                List<Vertex> neighbors = vertexMap.get(oneMoreVertex);

                for (Vertex neighbor : neighbors) {
                    vertexStack.push(neighbor);
                }
            }



// алгоритмы сортировки
            public fastSort(int[] arr){

                int opornyiElement = arr[arr.length/2]
                int[] arrLow = new int[arr.length/2 + 1]
                int[] arrBiggest = new int[arr.length/2 + 1]
                        int start = 0

                for (int i = 0; i < arr.length ; i++) {
                 if(arr[i] < opornyiElement){
                     arrLow[i] = arr[i]

                 } else {
                     arrBiggest[i] = arr[i]
                 }
                }
                fastSort(arrLow);
                fastSort(arrBiggest);

            }

              public fastSort(int[] arr){

        }
// списки             ArrayList; LinkedList доступ к индексам
// множества          Set нельзя дубли
// очередь            Queue Deque === LinkedList  ArrayDeque
    }

    public static class Vertex {
        String city;

        public Vertex(String city) {
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(city, vertex.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city);
        }
    }
}
//vertex
//добавление нового города
//        удаление существующего города
//        добавление связи между городами (ребра)
//        удаление связи между городами (ребра)