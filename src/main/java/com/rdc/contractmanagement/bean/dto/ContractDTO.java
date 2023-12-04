package com.rdc.contractmanagement.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
    private String contractId;
    private String contractType;
    private String employer;
    private String department;
    private String projectName;
    private String projectAddress;
    private double projectScale;
    private double projectCost;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date signedTime;
}
