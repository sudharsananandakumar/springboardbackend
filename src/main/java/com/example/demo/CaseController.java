/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ComponentScan(basePackages = {"com.example.demo"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/cases")
public class CaseController {
    private final DynamoDBCaseService dbService;

    @Autowired
    CaseController(DynamoDBCaseService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("")
    public List<Case> getCases() {
        Iterable<Case> result;
        result = dbService.getAllCases();

        return StreamSupport.stream(result.spliterator(), false)
            .collect(Collectors.toUnmodifiableList());
    }



    @PostMapping("")
    public List<Case> addCase(@RequestBody Map<String, String> payload) {

        String caseId = payload.get("caseId");
        String contactNumber = payload.get("contactNumber");
        String familyDetails = payload.get("familyDetails");
        String healthDetails = payload.get("healthDetails");
        String memberType = payload.get("memberType");
        String name = payload.get("name");
        String navigatorId = payload.get("navigatorId");
        String ssn = payload.get("ssn");

        Case caseDetail = new Case();
        caseDetail.setCaseId(caseId);
        caseDetail.setContactNumber(contactNumber);
        caseDetail.setFamilyDetails(familyDetails);
        caseDetail.setHealthDetails(healthDetails);
        caseDetail.setMemberType(memberType);
        caseDetail.setName(name);
        caseDetail.setNavigatorId(navigatorId);
        caseDetail.setSsn(ssn);
        dbService.setItem(caseDetail);
        List<Case> result= dbService.getAllCases();
        return StreamSupport.stream(result.spliterator(), false)
            .collect(Collectors.toUnmodifiableList());
    }
}