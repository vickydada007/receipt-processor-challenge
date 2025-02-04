package com.exercise.receiptProcessor.controller;

import com.exercise.receiptProcessor.service.ReceiptProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessingController {


    private ReceiptProcessingService receiptProcessingService;
    private Map<String, Integer> receiptPoints = new HashMap<>();

    public ReceiptProcessingController(ReceiptProcessingService receiptProcessingService){
        this.receiptProcessingService = receiptProcessingService;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String,String>> processReceipt(@RequestBody Map<String,Object> receipt){
        String id = UUID.randomUUID().toString();
        int points = receiptProcessingService.calculatePoints(receipt);
        receiptPoints.put(id,points);
        return ResponseEntity.ok(Collections.singletonMap("id",id));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String,Integer>> getPoints(@PathVariable String id){
        return ResponseEntity.ok(Collections.singletonMap("points",receiptPoints.getOrDefault(id,0)));
    }
}
