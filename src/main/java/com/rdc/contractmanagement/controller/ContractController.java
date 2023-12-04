package com.rdc.contractmanagement.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rdc.contractmanagement.bean.CommonResult;
import com.rdc.contractmanagement.entity.*;
import com.rdc.contractmanagement.listener.ExcelDataListener;
import com.rdc.contractmanagement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/input")
    public CommonResult input(TotalContractInformation totalContractInformation,
                               MultipartFile[] contractAnnex,  MultipartFile[] supplementaryAgreement,  MultipartFile[] bidAnnex,  MultipartFile[] awardsAnnex,MultipartFile[] acceptanceReport,MultipartFile[] remarkAnnex){
        try{
            return contractService.saveContract(totalContractInformation,contractAnnex,supplementaryAgreement,bidAnnex,awardsAnnex,acceptanceReport,remarkAnnex);
        }catch (Exception e){
            return CommonResult.fail("录入失败");
        }
    }

    @GetMapping("/find")
    public CommonResult find(TotalContractInformation totalContractInformation,Double projectUpScale,Double projectDownScale,Double projectUpCost,Double projectDownCost,Long page,@RequestParam(defaultValue = "10") Long pageSize){
        return contractService.findContract(totalContractInformation,projectUpScale,projectDownScale,projectUpCost,projectDownCost,page,pageSize);
    }

    @PostMapping("/delete")
    public CommonResult delete(String contractId){
        return contractService.delete(contractId);
    }

    @GetMapping("/getMore")
    public CommonResult getMore(String contractId){
        return contractService.getMore(contractId);
    }

    @GetMapping("/getComboBox")
    public CommonResult getComboBox(){
        return contractService.getComboBox();
    }

    @PostMapping("/addComboBox")
    public CommonResult addComboBox(@RequestParam(value = "contractAccesses",required = false)ArrayList<String> contractAccesses,@RequestParam(value = "contractAccessesDel",required = false)ArrayList<Integer> contractAccessesDel,@RequestParam(value = "contractTypes",required = false)ArrayList<String> contractTypes,
                                    @RequestParam(value = "contractTypesDel",required = false)ArrayList<Integer> contractTypesDel,@RequestParam(value = "departments",required = false)ArrayList<String> departments,@RequestParam(value = "departmentsDel",required = false)ArrayList<Integer> departmentsDel, @RequestParam(value = "employerTypes",required = false)ArrayList<String> employerTypes,
                                    @RequestParam(value = "employerTypesDel",required = false)ArrayList<Integer> employerTypesDel,@RequestParam(value = "projectTypes",required = false)ArrayList<String> projectTypes,@RequestParam(value = "projectTypesDel",required = false) ArrayList<Integer> projectTypesDel){
        return contractService.addComboBox(contractAccesses,contractAccessesDel,contractTypes,contractTypesDel,departments,departmentsDel,employerTypes,employerTypesDel,projectTypes,projectTypesDel);
    }

    @PostMapping("/update")
    public CommonResult update(TotalContractInformation totalContractInformation,String[] urls,
                               MultipartFile[] contractAnnex,  MultipartFile[] supplementaryAgreement,  MultipartFile[] bidAnnex,  MultipartFile[] awardsAnnex,MultipartFile[] acceptanceReport,MultipartFile[] remarkAnnex){
        try{
            return contractService.update(totalContractInformation,urls,contractAnnex,supplementaryAgreement,bidAnnex,awardsAnnex,acceptanceReport,remarkAnnex);
        }catch (Exception e){
            return CommonResult.fail("修改失败");
        }
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response,TotalContractInformation totalContractInformation,Double projectUpScale,Double projectDownScale,Double projectUpCost,Double projectDownCost) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        QueryWrapper<TotalContractInformation> totalContractInformationQueryWrapper = new QueryWrapper<>();
        //对三个范围参数进行判空
        if(Optional.ofNullable(projectUpScale).isPresent()){
            totalContractInformationQueryWrapper.between("project_scale",projectDownScale,projectUpScale);
        }
        if(Optional.ofNullable(projectUpCost).isPresent()){
            totalContractInformationQueryWrapper.between("project_cost",projectDownCost,projectUpCost);
        }
        if(Optional.ofNullable(totalContractInformation.getBeginTime()).isPresent()) {
            totalContractInformationQueryWrapper.between("signed_time", totalContractInformation.getBeginTime(), totalContractInformation.getEndTime());
        }
        totalContractInformationQueryWrapper.like("contract_id",totalContractInformation.getContractId())
                .like("employer",totalContractInformation.getEmployer())
                .like("project_name",totalContractInformation.getProjectName())
                .like("project_address",totalContractInformation.getProjectAddress())
                .like("contract_type",totalContractInformation.getContractType())
                .eq("is_delete",0);
        List<TotalContractInformation> totalContractInformations = contractService.list(totalContractInformationQueryWrapper);
        EasyExcel.write(response.getOutputStream(), TotalContractInformation.class).sheet("模板").doWrite(totalContractInformations);
    }

    @PostMapping("/upload")
    @ResponseBody
    public CommonResult upload(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), TotalContractInformation.class, new ExcelDataListener(contractService)).sheet().doRead();
        }
        catch (Exception e){
            e.printStackTrace();
            return CommonResult.fail("导入失败");
        }
        return CommonResult.success("导入成功");
    }
}
