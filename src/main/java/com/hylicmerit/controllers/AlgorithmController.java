package com.hylicmerit.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.algorithms.DjikstraHelper;
import com.hylicmerit.models.AlgorithmInput;
import com.hylicmerit.models.Node;

@RestController
@CrossOrigin
@RequestMapping(value = "/algorithm", produces = "application/json")
public class AlgorithmController {
	ObjectMapper mapper = new ObjectMapper();

	@PostMapping("/dijkstra")
	public ResponseEntity<String> dijkstra(@RequestBody AlgorithmInput input)
			throws JsonProcessingException {
		List<Node> visitedNodesInOrder = DjikstraHelper.djikstra(
				input.getRows(), input.getColumns(), 
				input.getStart(), input.getEnd(),
				input.getWalls());
		List<Node> shortestPath = DjikstraHelper.getShortestPathAnimation();
		return ResponseEntity.ok().body(
				"{\"visitedNodesInOrder\" : " 
						+ mapper.writeValueAsString(visitedNodesInOrder) + "," +
				"\"path\" : " 
						+ mapper.writeValueAsString(shortestPath) + "}");
	}
}
