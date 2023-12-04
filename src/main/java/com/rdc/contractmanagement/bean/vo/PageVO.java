package com.rdc.contractmanagement.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    private Object data;
    private Long page;
    private Long size;
    private Long total;
}
