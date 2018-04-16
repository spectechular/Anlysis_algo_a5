package com.company;

import javafx.scene.layout.GridPane;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;


/*
 * Michael Ramirez CS 3343 M|W 4:00PM
 * Assignment 4
 * This program demonstrates depth first search and breadth first search graph algorithms
 */

import java.net.Inet4Address;
import java.util.*;

public class Main {

    final static int NUM_NODES = 10;

    public static void main(String[] args) {

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

        //
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

        while (!toBeChecked.isEmpty()) {

            int v = getNextToBeChecked(toBeChecked, currentDistance);
            toBeChecked.remove(Integer.valueOf(v));

            for (int i = 0; i < NUM_NODES; i++) {

                if (graph.graphNodes[v].adjacentNodes[i] != 0) {

                    if (currentDistance[i] > currentDistance[v] + graph.graphNodes[v].adjacentNodes[i]) {

                        currentDistance[i] = currentDistance[v] + graph.graphNodes[v].adjacentNodes[i];
                        predecessor[i] = v;

                    }

                }

            }

        }

        for (int i = 0; i < NUM_NODES; i++) {
            int identifierStringASCII = 65 + i;
            int pLetterIdentifierASCII = 65 + predecessor[i];
            int nodeDistance = currentDistance[i];
            String letter = Character.toString((char) identifierStringASCII);
            String predecessorLetter = Character.toString((char) pLetterIdentifierASCII);

            if (nodeDistance == 999 || nodeDistance == 0) {
                System.out.println(letter + ": predecessor " + "None" + "\tDistance " + "None");

            } else {
                System.out.println(letter + ": predecessor " + predecessorLetter + "\tDistance " + currentDistance[i] + printPath(graph, predecessor, currentDistance, i));
            }


        }
    }

    public static String printPath(Graph graph, int[] predecessor, int[] currentDistance, int i) {
        ArrayList<Integer> arrayListPath = new <Integer>ArrayList();
        String path = "\t\t";
        arrayListPath.add(i);
        getPrevious(predecessor, arrayListPath, currentDistance, i);
        for (int j = arrayListPath.size() - 1; j >= 0; j--) {
            path += graph.graphNodes[arrayListPath.get(j)].letter;
            if (j != 0) {
                path += " --> ";

            }
        }

        return path;
    }

    public static void getPrevious(int[] predecessor, ArrayList<Integer> arrayListPath, int[] currentDistance, int i) {

        if (currentDistance[i] == 0 || currentDistance[i] == 999) {
            return;
        }

        arrayListPath.add(predecessor[i]);
        getPrevious(predecessor, arrayListPath, currentDistance, predecessor[i]);

    }

    public static int getNextToBeChecked(ArrayList toBeChecked, int[] currentDistance) {

        int v = 1000;

        for (int i = 0; i < toBeChecked.size(); i++) {

            if (currentDistance[(Integer) toBeChecked.get(i)] < v) {
                v = (Integer) toBeChecked.get(i);
            }
        }
        return v;
    }


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




