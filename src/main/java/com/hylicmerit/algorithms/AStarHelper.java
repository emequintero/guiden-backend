package com.hylicmerit.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.hylicmerit.models.Node;

public class AStarHelper {

	private static Node[][] graph = null;
	private static List<Node> visitedNodesInOrder = null;
	private static Node startNode = null, endNode = null;

	public static List<Node> aStar(int rows, int columns, 
			int[] start, int[] end, int[][] walls) {
		//declare start and end nodes
		//unvisited nodes compared by distance (compare method in node class)
		List<Node> unvisitedNodes = new ArrayList<Node>();
		graph = new Node[rows][columns];
		//build graph
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				//current node in graph (nodes have an initial distance of infinity)
				Node current = new Node(row, col);
				//add to graph
				graph[row][col] = current;
				//check if node is a wall
				if(checkIfWall(walls, current)) current.setWall(true);
				//add to unvisited nodes list
				unvisitedNodes.add(current);
				if(row == start[0] && col == start[1]) {
					//set start node
					startNode = current;
					//initialize starting node to a distance of 0
					startNode.setDistance(0);
					startNode.setStart(true);
				}
				else if(row == end[0] && col == end[1]) {
					//set end node
					endNode = current;
					endNode.setFinish(true);
				}
			}
		}
		//list of visited nodes in order
		visitedNodesInOrder = new ArrayList<Node>();
		while(unvisitedNodes.size() != 0) {
			//sort unvisited nodes by distance
			Collections.sort(unvisitedNodes);
			//get closest node and remove from list
			Node closest = unvisitedNodes.remove(0);
			//skip walls
			if(closest.isWall()) continue;
			//stop traversing if trapped (closest node is at a distance of max int)
			if(closest.getDistance() == Integer.MAX_VALUE) return visitedNodesInOrder;
			//set closest node as visited
			closest.setVisited(true);
			//add to visited nodes in order list
			visitedNodesInOrder.add(closest);
			//if closest node = finish node stop traversing
			if(closest.isFinish()) return visitedNodesInOrder;
			//update unvisited neighbors
			updateUnvisitedNeighbors(closest);
		}
		return visitedNodesInOrder;
	}
	
	private static void updateUnvisitedNeighbors(Node node) {
		//get node's unvisited neighbors
		List<Node> unvisitedNeighbors = getUnvisitedNeighbors(node);
		for(Node neighbor : unvisitedNeighbors) {
			//set new distance based on where it is from end node
			int x = (int) Math.pow(endNode.getColumn() - node.getColumn(), 2.0);
			int y = (int) Math.pow(endNode.getColumn() - node.getColumn(), 2.0);
			neighbor.setDistance(x+y);
			//set neighbor's previous node
			neighbor.setPreviousNode(node);
		}
	}
	
	private static List<Node> getUnvisitedNeighbors(Node node){
		List<Node> unvisitedNeighbors = new ArrayList<Node>();
		Integer row = node.getRow();
		Integer column = node.getColumn();
		//check if node has left neighbor
		if(column != 0) unvisitedNeighbors.add(graph[row][column-1]);
		//check if node has right neighbor
		if(column != graph[0].length-1) unvisitedNeighbors.add(graph[row][column+1]);
		//check if node has neighbor above
		if(row != 0) unvisitedNeighbors.add(graph[row-1][column]);
		//check if node has neighbor below
		if(row != graph.length-1) unvisitedNeighbors.add(graph[row+1][column]);
		//iterate through neighbors to remove visited ones
		Iterator<Node> nIterator = unvisitedNeighbors.iterator();
		//remove visited neighbors
		while(nIterator.hasNext()) {
			Node neighbor = nIterator.next();
			if(neighbor.isVisited()) {
				nIterator.remove();
			}
		}
		return unvisitedNeighbors;
	}
	
	//uses shortest path beginning from end node to get to start node (used in animation)
	public static List<Node> getShortestPathAnimation(){
		List<Node> shortestPathAnimations = new ArrayList<Node>();
		//get end node
		Node node = visitedNodesInOrder.get(visitedNodesInOrder.size()-1);
		//while node has previous node
		while(node != null) {
			Node prevNode = node.getPreviousNode();
			shortestPathAnimations.add(0, node);
			node = prevNode;
		}
		return shortestPathAnimations;
	}
	
	private static boolean checkIfWall(int[][] walls, Node current) {
		for(int i = 0; i < walls.length; i++) {
			if(current.getRow() == walls[i][0] && current.getColumn() == walls[i][1]) {
				return true;
			}
		}
		return false;
	}


}
