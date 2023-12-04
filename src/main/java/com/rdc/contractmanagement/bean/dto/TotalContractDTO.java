package com.rdc.contractmanagement.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalContractDTO {
    private Integer id;
    private String contractId;
    private String contractName;
    private String contractType;
    private String contractAccess;
    private String department;
    private String operator;
    private Double settlementPrice;
    private Double contractAmount;
    private Double finishAmount;
    private Double unfinishAmount;
    private String signedAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date signedTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    private String awardText;
    private String remark;
    private Date bidDate;
    private Double bidAmount;
    private String bidInstitution;
    private String employer;
    private String employerAddress;
    private String contact;
    private String contactPhone;
    private String projectName;
    private String projectType;
    private String projectAddress;
    private Double projectScale;
    private Double projectCost;
    private String projectPrincipal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date commencementDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date completionDate;
    private Double completionScale;
    private Double completionCost;
    private Integer upLayers;
    private Integer underLayers;
    private List<FileDTO> contractAnnex;
    private List<FileDTO> supplementaryAgreement;
    private List<FileDTO> bidAnnex;
    private List<FileDTO> awardsAnnex;
    private List<FileDTO> acceptanceReport;
    private List<FileDTO> remarkAnnex;
}
