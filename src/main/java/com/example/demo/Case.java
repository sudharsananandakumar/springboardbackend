/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.demo;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Case {
    private String caseId;
    private String contactNumber;
    private String familyDetails ;
    private String healthDetails;
    private String memberType ;
    private String name ;
    private String navigatorId ;
    private String ssn ;

    @DynamoDbPartitionKey
    public String getCaseId() {
        return this.caseId;
    };

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFamilyDetails() {
        return familyDetails;
    }

    public void setFamilyDetails(String familyDetails) {
        this.familyDetails = familyDetails;
    }

    public String getHealthDetails() {
        return healthDetails;
    }

    public void setHealthDetails(String healthDetails) {
        this.healthDetails = healthDetails;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNavigatorId() {
        return navigatorId;
    }

    public void setNavigatorId(String navigatorId) {
        this.navigatorId = navigatorId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}