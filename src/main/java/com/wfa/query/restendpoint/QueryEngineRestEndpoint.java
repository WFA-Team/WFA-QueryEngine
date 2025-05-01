package com.wfa.query.restendpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfa.query.api.IQueryEngineOrchestrator;
import com.wfa.query.restendpoint.helpers.QueryRegistry;

@Component
@RestController
@RequestMapping("/api")
public class QueryEngineRestEndpoint {

	private final IQueryEngineOrchestrator orchestrator;
	private final QueryRegistry queryRegistry;
	
	@Autowired
	QueryEngineRestEndpoint(IQueryEngineOrchestrator orchestrator, QueryRegistry queryRegistry) {
		this.orchestrator = orchestrator;
		this.queryRegistry = queryRegistry;
	}
	
	@PostMapping("/executeQuery")
	public ResponseEntity<Void> executeQuery(@RequestParam String query) {
		orchestrator.executeQuery(query);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/exit")
	public ResponseEntity<String> exit() {
		orchestrator.exit();
		return ResponseEntity.ok("Shutting down query engine");
	}
	
	@PostMapping("/executeQueryRT")
	public ResponseEntity<Void> executeQueryRT(@RequestParam String query) {
		orchestrator.executeQueryRT(query);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/createStream")
	public ResponseEntity<Void> createStream(@RequestParam String streamName, 
			@RequestParam String topic, @RequestBody List<String> fieldNames) {
		orchestrator.createStream(streamName, topic, fieldNames);
		return ResponseEntity.accepted().build();	
	}
}
