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


    public void BFSprint(String startCity) {
        Vertex newVertex = new Vertex(startCity);

        Set<Vertex> vertexSet = new LinkedHashSet<>();
        Queue<Vertex> queueVertex = new LinkedList<>();

        vertexSet.add(newVertex);
        queueVertex.add(newVertex);

        while (!queueVertex.isEmpty()) {
            Vertex vertInQueue = queueVertex.poll();
            List<Vertex> neighborsList = vertexMap.get(vertInQueue);
            for (Vertex neighbor : neighborsList) {
                if (!vertexSet.contains(neighbor)) {
                    vertexSet.add(neighbor);
                    queueVertex.add(neighbor);
                }
            }
        }
        vertexSet.forEach(vertex -> System.out.println(vertex.city));
    }

    public void DFSalgorithm(String startCity) {
//Stack
        Vertex newVertex = new Vertex(startCity);
        Set<Vertex> vertexSet = new LinkedHashSet<>();
        Stack<Vertex> vertexStack = new Stack<>();

        vertexStack.push(newVertex);

        while (!vertexStack.isEmpty()) {
            Vertex oneMoreVertex = vertexStack.pop();
            if (!vertexSet.contains(oneMoreVertex)) {
                vertexSet.add(oneMoreVertex);
                List<Vertex> neighbors = vertexMap.get(oneMoreVertex);

                for (Vertex neighbor : neighbors) {
                    vertexStack.push(neighbor);
                }
            }
        }
        vertexSet.forEach(vertex -> System.out.println(vertex.city));
    }

    // проверка на наличие цикличности в графе
    private boolean hasCircleHelper(Vertex vertex, Set<Vertex> visited, Set<Vertex> currentVisiting) {
        if (currentVisiting.contains(vertex)) return true;
        if (visited.contains(vertex)) return false;

        visited.add(vertex);
        currentVisiting.add(vertex);

        List<Vertex> children = vertexMap.get(vertex);

        for (Vertex v: children) {
            if (hasCircleHelper(v, visited, currentVisiting)) return true;
        }

        currentVisiting.remove(vertex);
        return false;
    }
    public boolean hasCircle(){

        Set<Vertex> visited = new LinkedHashSet<>();
        Set<Vertex> currentVisiting = new LinkedHashSet<>();

        for (Vertex vertex : vertexMap.keySet()) {
            if (hasCircleHelper(vertex, visited, currentVisiting)) return true;
        }

        return false;
    }

    /* Топологическая сортировка - способ нумерации вершин ориентированного графа,
    при котором каждое ребро ведёт из вершины с меньшим номером в вершину с большим номером. */
    // Первая вершина в топологической сортировке — это всегда вершина без входящих ребер.
    // Если граф не является DAG (если в нём есть циклы), то топологическая сортировка для него невозможна.
    private void topologicalSortHelper(Vertex vertex, Set<Vertex> visited, Stack<Vertex> stack)
    {
        // отметили текущую вершину, как посещённую
        visited.add(vertex);

        // итерация для всех смежных вершин
        List<Vertex> adjList = vertexMap.get(vertex);
        for (Vertex v : adjList) {
            if (!visited.contains(v)) topologicalSortHelper(v, visited, stack);
        }
        // добавление текущей вершины в результат
        stack.push(vertex);
    }

    public Stack<Vertex> topologicalSort()
    {
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> visited = new LinkedHashSet<>();

        for (Vertex vertex : vertexMap.keySet()) {
            if (!visited.contains(vertex)) topologicalSortHelper(vertex, visited, stack);
        }
        return stack;
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

