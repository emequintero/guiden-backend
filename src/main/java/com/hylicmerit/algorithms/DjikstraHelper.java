package com.hylicmerit.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

import com.hylicmerit.models.Animation;
import com.hylicmerit.models.Graph;
import com.hylicmerit.models.Node;

public class DjikstraHelper {
	private static List<Animation> animations = null;
	private static Animation current = null;
	private static Graph nodeGraph = null;

	public static int djikstra(int[][] graph, Node start) {
		int rows = graph.length;
		int columns = graph[0].length;
		buildGraph(graph, rows, columns);
		//total costs for each node
		Map<Node, Integer> totalCosts = new HashMap<Node, Integer>();
		//hash map for previous nodes (alternate paths)
		Map<Node, Node> prevNodes = new HashMap<Node, Node>();
		//min heap for possible routes
		PriorityQueue<Node> nodeMinPQ = new PriorityQueue<Node>();
		//set (no duplicates) for visited nodes
		Set<Node> visited = new HashSet<Node>();
		
		//set start node cost to 0
		totalCosts.put(start, 0);
		//add start to nodeMinPQ
		nodeMinPQ.add(start);
		//add total initial cost of each nodes
		for(Node current : nodeGraph.nodes()) {
			if(current != start) {
				//add current node to total costs with infinite value
				totalCosts.put(current, Integer.MAX_VALUE);
			}
		}
		while(!nodeMinPQ.isEmpty()) {
			//add smallest distance node to min heap
			Node newSmallest = nodeMinPQ.poll();
			//add smallest distance node to visited set
			visited.add(newSmallest);
			/**
			 * check the neighbors of the current smallest node
			 * to see which has the shortest distance 
			 * */
			for(Node neighbor : nodeGraph.getNeighbors(newSmallest)) {
				//check if the current neighbor has not been visited
				if(!visited.contains(neighbor)) {
					//calculate new path distance
					int altPath = totalCosts.get(newSmallest) 
							+ distance(newSmallest, neighbor, columns);
					//check if new path is better
					if(altPath < totalCosts.get(neighbor)) {
						//update path length for neighbor
						totalCosts.replace(neighbor, altPath);
						//update previous node to the newSmallest
						prevNodes.put(neighbor, newSmallest);
						
					}
					//add neighbor to min heap
					nodeMinPQ.add(neighbor);
				}
			}
		}
		
		int shortestPath = 0;
		//calculate shortest path
		for(Entry cost : totalCosts.entrySet()) {
			//
			shortestPath += 
		}
		
		return shortestPath;
	}
	
	private static int distance(Node node, Node neighbor, int columns) {
		// row = floor(nodeId/columns)
		// column = nodeId - (columns * row)
		
		//get current node's x and y
		int y1 = (int) Math.floor(node.getId() / columns);
		int x1 = node.getId() - (columns * y1);
		//get neighbor node's x and y
		int y2 = (int) Math.floor(neighbor.getId() / columns);
		int x2 = neighbor.getId() - (columns * y2);
		
		//calculate euclidean distance
		int distance = (int) Math.floor(Math.sqrt((y2 - y1) * (y2 - y1)
													+ (x2 - x1) * (x2 - x1)));
		return distance;
	}
	
	private static void buildGraph(int[][] graph, int rows, int columns) {
		//set size for node graph
		nodeGraph = new Graph();
		//add nodes to node graph
		/* 
		 * (columns * row) + col = node's id
		 * 
		 * 0, 1, 2, 3	-1 gets you left node's id / +1 gets you right node's id
		 * 4, 5, 6, 7	+number of columns gets you node below's id
		 * 8, 9,10,11	-number of columns gets you node above's id
		 * */
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				//current node's id
				int nodeId = (columns * row) + col;
				//get current node (add id & data)
				Node current = new Node(nodeId, graph[row][col]);
				//add current node to graph
				nodeGraph.addNode(current);
				//check if it's not the first column (has no left node)
				if(col != 0) {
					//create association between current and its left neighbor
					nodeGraph.addNeighbor(current, 
							new Node(nodeId-1, graph[row][col-1]));
				}
				//check if it's not the last column (has no right node)
				if(col != columns-1) {
					//create association between current and its right neighbor
					nodeGraph.addNeighbor(current, 
							new Node(nodeId+1, graph[row][col+1]));
				}
				//check if it's not the first row (has no above node)
				if(row != 0) {
					//create association between current and its neighbor above
					nodeGraph.addNeighbor(current, 
							new Node(nodeId-columns, graph[row-1][col]));
				}
				//check if it's not the last row (has no below node)
				if(row != rows-1) {
					//create association between current and its neighbor below
					nodeGraph.addNeighbor(current, new 
							Node(nodeId+columns, graph[row+1][col]));
				}
				nodeId++;
			}
		}
	}
	
	public static void resetAnimations() {
		animations = new ArrayList<Animation>();
	}

	public static List<Animation> getAnimations() {
		return animations;
	}

}
