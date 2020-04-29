package com.hylicmerit.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.algorithms.DjikstraHelper;
import com.hylicmerit.models.Node;

@RestController
@CrossOrigin
@RequestMapping(value = "/algorithm", produces = "application/json")
public class AlgorithmController {
	ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/dijkstra")
	public ResponseEntity<String> dijkstra(
			@RequestParam("start") int[] start,
			@RequestParam("end") int[] end,
			@RequestParam("rows") int rows,
			@RequestParam("columns") int columns)
			throws JsonProcessingException {
		List<Node> visitedNodesInOrder = DjikstraHelper.djikstra(rows, columns, start, end);
		List<Node> shortestPath = DjikstraHelper.getShortestPathAnimation();
		return ResponseEntity.ok().body(
				"{\"shortestDistance\" : " 
						+ mapper.writeValueAsString(visitedNodesInOrder) + "," +
				"\"shortestPath\" : " 
						+ mapper.writeValueAsString(shortestPath) + "}");
	}
}
