package com.rdc.contractmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rdc.contractmanagement.bean.CommonResult;
import com.rdc.contractmanagement.entity.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;

public interface ContractService extends IService<TotalContractInformation> {
    /**
     * 存储合同信息
     * @param contractAnnex
     * @param supplementaryAgreement
     * @param bidAnnex
     * @param awardsAnnex
     * @return
     */
    CommonResult saveContract(TotalContractInformation totalContractInformation, MultipartFile[] contractAnnex, MultipartFile[] supplementaryAgreement, MultipartFile[] bidAnnex, MultipartFile[] awardsAnnex,MultipartFile[] acceptanceReport,MultipartFile[] remarkAnnex) throws IOException;

    /**
     * 查询合同信息
     * @param project_DownScale
     * @param project_UpCost
     * @param project_DownCost
     * @return
     */
    CommonResult findContract(TotalContractInformation totalContractInformation, Double projectUpScale, Double project_DownScale, Double project_UpCost, Double project_DownCost,Long page,Long pageSize);

    /**
     * 删除合同信息
     * @param contractId 合同编号
     * @return
     */
    CommonResult delete(String contractId);

    /**
     * 查看合同详细信息
     * @return
     */
    CommonResult getMore(String contractId);

    /**
     * 获取下拉框数据
     * @return
     */
    CommonResult getComboBox();

    /**
     * 修改下拉框数据
     * @return
     */
    CommonResult addComboBox(ArrayList<String> contractAccesses, ArrayList<Integer> contractAccessesDel, ArrayList<String> contractTypes,
                             ArrayList<Integer> contractTypesDel, ArrayList<String> departments, ArrayList<Integer> departmentsDel, ArrayList<String> employerTypes,
                             ArrayList<Integer> employerTypesDel, ArrayList<String> projectTypes, ArrayList<Integer> projectTypesDel);

    /**
     *
     * @return
     */
    CommonResult update(TotalContractInformation totalContractInformation, String[] urls,MultipartFile[] contractAnnex, MultipartFile[] supplementaryAgreement, @RequestParam("bidAnnex") MultipartFile[] bidAnnex, @RequestParam("awardsAnnex") MultipartFile[] awardsAnnex,@RequestParam("acceptanceReport")MultipartFile[] acceptanceReport,@RequestParam("remarkAnnex")MultipartFile[] remarkAnnex) throws IOException;
}
