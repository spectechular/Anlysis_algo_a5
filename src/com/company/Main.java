package com.company;

import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;


/*
 * Michael Ramirez CS 3343 M|W 4:00PM
 * Assignment 4
 * This program demonstrates depth first search and breadth first search graph algorithms
 */

import java.util.*;

public class Main {

    final static int NUM_NODES = 10;

    public static void main(String[] args) {

        //Two separate sets of "Graphs" so they can be manipulated by the separate algorithms
        Graph graph = new Graph();


        //Initialize
        for (int i = 0; i < NUM_NODES; i++) {

            //Initialize nodes
            graph.graphNodes[i] = new Node();

            //Using ASCII codes to have easy interface between indexes and letter representations
            int identifierStringASCII = 65 + i;
            graph.graphNodes[i].letter = Character.toString((char) identifierStringASCII);

        }

        //Draw edges on graphs
        graph.initializeEdges();


        dspAlgo(graph);


    }


    public static void dspAlgo(Graph graph) {

        int currentDistance[] = new int[NUM_NODES];
        int predecessor[] = new int[NUM_NODES];


        for (int i = 0; i < NUM_NODES; i++) {

            currentDistance[i] = 999;
        }

        currentDistance[0] = 0;
        ArrayList<Integer> toBeChecked = new ArrayList<Integer>();

        for (int i = 0; i < NUM_NODES; i++) {

            toBeChecked.add(i);
        }

        while(!toBeChecked.isEmpty()){

            int v = getNextToBeChecked(toBeChecked, currentDistance);
            System.out.println(v);
            toBeChecked.remove(Integer.valueOf(v));

            for (int i = 0; i < NUM_NODES; i++) {

                if(graph.graphNodes[v].adjacentNodes[i] != 0){

                    if (currentDistance[i] > currentDistance[v] + graph.graphNodes[v].adjacentNodes[i]){

                        currentDistance[i] = currentDistance[v] + graph.graphNodes[v].adjacentNodes[i];
                        predecessor[i] = v;

                    }

                }

            }

        }

        for (int i = 0; i < NUM_NODES; i++) {
            System.out.println("predecessor " + predecessor[i] + "\tDistance " + currentDistance[i]);
        }
    }

    public static int getNextToBeChecked(ArrayList toBeChecked, int[] currentDistance){

        int v = 1000;

            for (int i = 0; i < toBeChecked.size() ; i++) {

                if (currentDistance[(Integer)toBeChecked.get(i)] < v){
                    v = (Integer)toBeChecked.get(i);
                }
            }
        return v;
    }

   /* // A, E, F, B, C, G, I, J, H, D
    public static void depthFirstSearch(Graph graph) {

        for (int i = 0; i < NUM_NODES; i++) {
            if (graph.graphNodes[i].visited == false) {
                //Start recursion
                DFS(i, graph);
            }
        }
    }

    static void DFS(int v, Graph graph) {
        graph.graphNodes[v].visited = true;
        System.out.println("Depth First Search Visited: " + graph.graphNodes[v].letter);
        for (int i = 0; i < NUM_NODES; i++) {
            //Checks is current node has adjacent node and if that has  been visited yet
            if (graph.graphNodes[v].adjacentNodes[i] == true && graph.graphNodes[i].visited == false) {
                DFS(i, graph);
            }
        }
    }


    public static void breadthFirstSearch(Graph graph) {
        // A, E, H, F, I, B, C, G, J, D

        //Created Queue. Built in! Woo!
        Queue<Integer> queue = new LinkedList<>();

        //Iterated through all nodes and if they have not been visited take appropriate action
        for (int i = 0; i < NUM_NODES; i++) {
            if (graph.graphNodes[i].visited == false) {
                graph.graphNodes[i].visited = true;
                System.out.println("Breadth First Search Visited: " + graph.graphNodes[i].letter);

                //Visited node so add to queue
                queue.add(i);

                //Loop to check if the queue has something in it. If so do the following loop
                while (queue.peek() != null) {

                    //Grab from queue node to check adjacency
                    int j = queue.remove();
                    //Checks through all adjacent possibilities
                    for (int k = 0; k < NUM_NODES; k++) {

                        //Checks is node adjacent has not been visited. If it has not we visit it
                        if (graph.graphNodes[j].adjacentNodes[k] == true && graph.graphNodes[k].visited == false) {
                            graph.graphNodes[k].visited = true;
                            System.out.println("Breadth First Search Visited: " + graph.graphNodes[k].letter);
                            queue.add(k);
                        }
                    }
                }
            }
        }
        //Print line breaks for visibility in console
        System.out.println("\n\n");
    }*/
}

class Graph {

    Node[] graphNodes;

    Graph() {
        //Makes 10 nodes since there are 10 nodes in the graph
        graphNodes = new Node[10];
    }


    void addEdgeToNode(String node, String edgeToNode, int weight) {

        int nodeIdx, edgeToNodeIdx = 0;

        nodeIdx = node.charAt(0) - 65;
        edgeToNodeIdx = edgeToNode.charAt(0) - 65;
        graphNodes[nodeIdx].adjacentNodes[edgeToNodeIdx] = weight;

    }

    void initializeEdges() {
        addEdgeToNode("A", "E", 1);
        addEdgeToNode("A", "H", 10);
        addEdgeToNode("B", "C", 2);
        addEdgeToNode("D", "A", 4);
        addEdgeToNode("D", "H", 1);
        addEdgeToNode("E", "F", 3);
        addEdgeToNode("F", "B", 1);
        addEdgeToNode("F", "C", 3);
        addEdgeToNode("F", "G", 7);
        addEdgeToNode("F", "I", 1);
        addEdgeToNode("H", "I", 9);
        addEdgeToNode("I", "J", 2);
        addEdgeToNode("J", "G", 1);
    }


}

class Node {

    String letter;
    boolean visited;
    int[] adjacentNodes;

    Node() {
        visited = false;
        letter = "";
        adjacentNodes = new int[10];
    }


}




