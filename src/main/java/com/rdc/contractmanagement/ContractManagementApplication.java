package com.rdc.contractmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication
public class ContractManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContractManagementApplication.class, args);
    }

}
