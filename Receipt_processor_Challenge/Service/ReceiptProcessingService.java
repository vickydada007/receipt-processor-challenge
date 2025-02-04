package com.exercise.receiptProcessor.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceiptProcessingService {

    public int calculatePoints(Map<String, Object> receipt) {
        int points = 0;


        String retailer = receipt.get("retailer").toString();

        if(retailer != null){
            points += (int) retailer.chars().filter(Character::isLetterOrDigit).count();
        }

        double total = Double.parseDouble(receipt.get("total").toString());
        if(total == Math.floor(total)){
            points += 50;
        }

        if (total % 0.25 == 0){
            points += 25;
        }

        List<Map<String,String>> items = (List<Map<String, String>>) receipt.get("items");
        points += (items.size() / 2) * 5;

        for(Map<String,String> item : items){
            String description = item.get("shortDescription").trim();
            if (description.length() % 3 == 0){
                double price = Double.parseDouble(item.get("price"));
                points += Math.ceil(price * 0.2);
            }
        }
        String purchaseDate = receipt.get("purchaseDate").toString();
        int purchaseDay = Integer.parseInt(purchaseDate.split("-")[2]);
        if(purchaseDay % 2 != 0){
            points += 6;
        }

        String purchaseTime = receipt.get("purchaseTime").toString();
        int hour = Integer.parseInt(purchaseTime.split(":")[0]);
        if(hour >= 14 && hour < 16){
            points += 10;
        }

        return points;
    }
}
