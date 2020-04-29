package com.hylicmerit.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylicmerit.algorithms.DjikstraHelper;
import com.hylicmerit.models.Animation;
import com.hylicmerit.models.Node;

@RestController
@CrossOrigin
@RequestMapping("/algorithm")
public class AlgorithmController {
	ObjectMapper mapper = new ObjectMapper();

	@PostMapping("/dijkstra")
	public ResponseEntity<String> dijkstra(@PathVariable Node start, 
			@RequestBody int[][] graph)
			throws JsonProcessingException {
		DjikstraHelper.resetAnimations();
		int distance = DjikstraHelper.djikstra(graph, start);
		List<Animation> animations = DjikstraHelper.getAnimations();
		return ResponseEntity.ok().body(
				"{\"shortestDistance\" : " + mapper.writeValueAsString(distance) + "," + 
				"\"animations\" : " + mapper.writeValueAsString(animations) + "}");
	}
}
