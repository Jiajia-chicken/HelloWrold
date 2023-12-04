package com.rdc.contractmanagement.bean;

import com.rdc.contractmanagement.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComboBox {
    List<ContractAccess> contractAccesses;
    List<ContractType> contractTypes;
    List<Department> departments;
    List<EmployerType> employerTypes;
    List<ProjectType> projectTypes;
}
