/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.demo;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 Before running this code example, create an Amazon DynamoDB table named Work with a primary key named id.
 */
@Component
public class DynamoDBCaseService {

    private DynamoDbClient getClient() {
        Region region = Region.US_EAST_1;
        return DynamoDbClient.builder()
            .region(region)
            .build();
    }
    // Get All items from the DynamoDB table.
    public List<Case> getAllCases() {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(getClient())
            .build();

        try{
            DynamoDbTable<Case> table = enhancedClient.table("registration_details", TableSchema.fromBean(Case.class));
            Iterator<Case> results = table.scan().items().iterator();
            ArrayList<Case> itemList = new ArrayList<>();

            while (results.hasNext()) {
                // Push the workItem to the list.
                itemList.add(results.next());
            }
            return itemList;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }



    public void setItem(Case caseDetail) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(getClient())
            .build();

        putRecord(enhancedClient, caseDetail) ;
    }

    // Put an item into a DynamoDB table.
    public void putRecord(DynamoDbEnhancedClient enhancedClient, Case caseDetail) {

        try {
            DynamoDbTable<Case> workTable = enhancedClient.table("registration_details", TableSchema.fromBean(Case.class));
            String myGuid = java.util.UUID.randomUUID().toString();
            workTable.putItem(caseDetail);

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private String now() {
        String dateFormatNow = "yyyy-MM-dd HH:mm:ss";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatNow);
        return sdf.format(cal.getTime());
    }
}