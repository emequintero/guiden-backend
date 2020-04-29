package com.hylicmerit.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
	private Map<Node, Set<Node>> graph;
	
	public Graph(){
		super();
		graph = new HashMap<Node, Set<Node>>();
	}
	
	public void addNode(Node node) {
		graph.put(node, new HashSet<Node>());
	}
	
	public void addNeighbor(Node nodeOne, Node nodeTwo) {
		graph.get(nodeOne).add(nodeTwo);
		if(graph.containsKey(nodeTwo)) {
			graph.get(nodeTwo).add(nodeOne);
		}
	}
	
	public Set<Node> getNeighbors(Node node) {
		return graph.get(node);
	}
	
	public Set<Node> nodes(){
		return graph.keySet();
	}
}
