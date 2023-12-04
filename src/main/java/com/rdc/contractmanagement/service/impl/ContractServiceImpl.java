package com.rdc.contractmanagement.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rdc.contractmanagement.bean.ComboBox;
import com.rdc.contractmanagement.bean.CommonResult;
import com.rdc.contractmanagement.bean.dto.ContractDTO;
import com.rdc.contractmanagement.bean.dto.FileDTO;
import com.rdc.contractmanagement.bean.dto.TotalContractDTO;
import com.rdc.contractmanagement.bean.vo.PageVO;
import com.rdc.contractmanagement.entity.*;
import com.rdc.contractmanagement.mapper.*;
import com.rdc.contractmanagement.service.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("contractService")
public class ContractServiceImpl extends ServiceImpl<TotalContractInformationMapper, TotalContractInformation> implements ContractService {
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private BidInformationMapper bidInformationMapper;
    @Autowired
    private CompletionInformationMapper completionInformationMapper;
    @Autowired
    private CooperatorMapper cooperatorMapper;
    @Autowired
    private AnnexMapper annexMapper;
    @Autowired
    private TotalContractInformationMapper totalContractInformationMapper;
    @Autowired
    private ContractAccessMapper contractAccessMapper;
    @Autowired
    private ContractTypeMapper contractTypeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployerTypeMapper employerTypeMapper;
    @Autowired
    private ProjectTypeMapper projectTypeMapper;
    @Autowired
    private Environment env;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult saveContract(TotalContractInformation totalContractInformation, MultipartFile[] contractAnnex, MultipartFile[] supplementaryAgreement, MultipartFile[] bidAnnex, MultipartFile[] awardsAnnex,MultipartFile[] acceptanceReport,MultipartFile[] remarkAnnex)
            throws RuntimeException, IOException {
        saveFile(contractAnnex,totalContractInformation,1);
        saveFile(supplementaryAgreement,totalContractInformation,4);
        saveFile(bidAnnex,totalContractInformation,3);
        saveFile(awardsAnnex,totalContractInformation,2);
        saveFile(acceptanceReport,totalContractInformation,5);
        saveFile(remarkAnnex,totalContractInformation,6);
        totalContractInformationMapper.insert(totalContractInformation);
        return CommonResult.success("录入成功");
    }

    @Override
    public CommonResult findContract(TotalContractInformation totalContractInformation,Double projectUpScale,Double projectDownScale,Double projectUpCost,Double projectDownCost,Long page,Long pageSize) {
        QueryWrapper<TotalContractInformation> totalContractInformationQueryWrapper = new QueryWrapper<>();
        Page<TotalContractInformation> pageData = new Page<>(page,pageSize);
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

        Page<TotalContractInformation> totalContractInformationPage = totalContractInformationMapper.selectPage(pageData, totalContractInformationQueryWrapper);
        //将查出来的数据都换成DTO然后装入VO传给前端
        List<ContractDTO> contractDTOs = new ArrayList<>();
        for (TotalContractInformation contractInformation : totalContractInformationPage.getRecords()) {
            ContractDTO contractDTO = new ContractDTO();
            BeanUtil.copyProperties(contractInformation,contractDTO);
            contractDTOs.add(contractDTO);
        }
        return CommonResult.success(new PageVO(contractDTOs,totalContractInformationPage.getPages(),totalContractInformationPage.getSize(),totalContractInformationPage.getTotal()));
    }

    @Override
    public CommonResult delete(String contractId) {
        UpdateWrapper<TotalContractInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_delete",1).eq("contract_id",contractId);
        if(totalContractInformationMapper.update(null,updateWrapper)==1){
            return CommonResult.success("操作成功");
        }
        return CommonResult.fail("操作失败，请稍后再试");
    }

    @Override
    public CommonResult getMore(String contractId) {
        TotalContractDTO totalContractDTO = new TotalContractDTO();
        QueryWrapper<TotalContractInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contract_id",contractId).eq("is_delete",0);
        TotalContractInformation totalContractInformation = totalContractInformationMapper.selectOne(queryWrapper);
        BeanUtil.copyProperties(totalContractInformation,totalContractDTO);
        //获取文件信息
        QueryWrapper<Annex> annexQueryWrapper = new QueryWrapper<>();
        //查找ContractAnnex
        annexQueryWrapper.eq("contract_id",contractId).eq("type",1);
        List<FileDTO> fileDTOS = new ArrayList<>();
        List<Annex> annexes = annexMapper.selectList(annexQueryWrapper);
        for (Annex annex : annexes) {
            FileDTO fileDTO = new FileDTO();
            BeanUtil.copyProperties(annex,fileDTO);
            fileDTOS.add(fileDTO);
        }
        totalContractDTO.setContractAnnex(fileDTOS);
        //查找AwardsAnnex
        QueryWrapper<Annex> annexQueryWrapper1 = new QueryWrapper<>();
        annexQueryWrapper1.eq("contract_id",contractId).eq("type",2);
        List<FileDTO> fileDTOS1 = new ArrayList<>();
        List<Annex> awards = annexMapper.selectList(annexQueryWrapper1);
        for (Annex award : awards) {
            FileDTO fileDTO = new FileDTO();
            BeanUtil.copyProperties(award,fileDTO);
            fileDTOS1.add(fileDTO);
        }
        totalContractDTO.setAwardsAnnex(fileDTOS1);
        //查找bidAnnex
        QueryWrapper<Annex> annexQueryWrapper2 = new QueryWrapper<>();
        annexQueryWrapper2.eq("contract_id",contractId).eq("type",3);
        List<FileDTO> fileDTOS2 = new ArrayList<>();
        List<Annex> bidAnnexs = annexMapper.selectList(annexQueryWrapper2);
        for (Annex bidAnnex : bidAnnexs) {
            FileDTO fileDTO = new FileDTO();
            BeanUtil.copyProperties(bidAnnex,fileDTO);
            fileDTOS2.add(fileDTO);
        }
        totalContractDTO.setBidAnnex(fileDTOS2);
        //查找supplementaryAgreement
        QueryWrapper<Annex> annexQueryWrapper3 = new QueryWrapper<>();
        annexQueryWrapper3.eq("contract_id",contractId).eq("type",4);
        List<FileDTO> fileDTOS3 = new ArrayList<>();
        List<Annex> supplementaryAgreements = annexMapper.selectList(annexQueryWrapper3);
        for (Annex supplementaryAgreement : supplementaryAgreements) {
            FileDTO fileDTO = new FileDTO();
            BeanUtil.copyProperties(supplementaryAgreement,fileDTO);
            fileDTOS3.add(fileDTO);
        }
        totalContractDTO.setSupplementaryAgreement(fileDTOS3);

        //查找acceptanceReport
        QueryWrapper<Annex> annexQueryWrapper4 = new QueryWrapper<>();
        annexQueryWrapper4.eq("contract_id",contractId).eq("type",5);
        List<FileDTO> fileDTOS4 = new ArrayList<>();
        List<Annex> acceptanceReports = annexMapper.selectList(annexQueryWrapper4);
        for (Annex acceptanceReport : acceptanceReports) {
            FileDTO fileDTO = new FileDTO();
            BeanUtil.copyProperties(acceptanceReport,fileDTO);
            fileDTOS4.add(fileDTO);
        }
        totalContractDTO.setAcceptanceReport(fileDTOS4);

        //查找remarkAnnex
        QueryWrapper<Annex> annexQueryWrapper5 = new QueryWrapper<>();
        annexQueryWrapper5.eq("contract_id",contractId).eq("type",5);
        List<FileDTO> fileDTOS5 = new ArrayList<>();
        List<Annex> remarkAnnexs = annexMapper.selectList(annexQueryWrapper5);
        for (Annex remarkAnnex : remarkAnnexs) {
            FileDTO fileDTO = new FileDTO();
            BeanUtil.copyProperties(remarkAnnex,fileDTO);
            fileDTOS5.add(fileDTO);
        }
        totalContractDTO.setRemarkAnnex(fileDTOS5);
        return CommonResult.success(totalContractDTO);
    }

    @Override
    public CommonResult getComboBox(){
        ComboBox comboBox = new ComboBox();
        List<ContractAccess> contractAccesses = contractAccessMapper.selectList(null);
        comboBox.setContractAccesses(contractAccesses);
        List<ContractType> contractTypes = contractTypeMapper.selectList(null);
        comboBox.setContractTypes(contractTypes);
        List<Department> departments = departmentMapper.selectList(null);
        comboBox.setDepartments(departments);
        List<EmployerType> employerTypes = employerTypeMapper.selectList(null);
        comboBox.setEmployerTypes(employerTypes);
        List<ProjectType> projectTypes = projectTypeMapper.selectList(null);
        comboBox.setProjectTypes(projectTypes);
        return CommonResult.success(comboBox);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult addComboBox(ArrayList<String> contractAccesses, ArrayList<Integer> contractAccessesDel, ArrayList<String> contractTypes, ArrayList<Integer> contractTypesDel, ArrayList<String> departments, ArrayList<Integer> departmentsDel, ArrayList<String> employerTypes, ArrayList<Integer> employerTypesDel, ArrayList<String> projectTypes, ArrayList<Integer> projectTypesDel) {
        if(contractAccesses!=null){
            for (String contractAccess : contractAccesses) {
                ContractAccess contractAccess1 = new ContractAccess();
                contractAccess1.setAccess(contractAccess);
                contractAccessMapper.insert(contractAccess1);
            }
        }
        if(contractAccessesDel!=null){
            for (Integer id: contractAccessesDel) {
                contractAccessMapper.deleteById(id);
            }
        }
        if(contractTypes!=null){
            for (String contractType : contractTypes) {
                ContractType contractType1 = new ContractType();
                contractType1.setTypeName(contractType);
                contractTypeMapper.insert(contractType1);
            }
        }
        if(contractTypesDel!=null){
            for (Integer id : contractTypesDel) {
                contractTypeMapper.deleteById(id);
            }
        }
        if(departments!=null){
            for (String department : departments) {
                Department department1 = new Department();
                department1.setDepartment(department);
                departmentMapper.insert(department1);
            }
        }
        if(departmentsDel!=null){
            for (Integer id : departmentsDel) {
                departmentMapper.deleteById(id);
            }
        }
        if(employerTypes!=null){
            for (String employerType : employerTypes) {
                EmployerType employerType1 = new EmployerType();
                employerType1.setType(employerType);
                employerTypeMapper.insert(employerType1);
            }
        }
        if(employerTypesDel!=null){
            for (Integer id : employerTypesDel) {
                employerTypeMapper.deleteById(id);
            }
        }
        if(projectTypes!=null){
            for (String projectType : projectTypes) {
                ProjectType projectType1 = new ProjectType();
                projectType1.setType(projectType);
                projectTypeMapper.insert(projectType1);
            }
        }
        if(projectTypesDel!=null){
            for (Integer id : projectTypesDel) {
                projectTypeMapper.deleteById(id);
            }
        }
        return CommonResult.success("修改成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult update(TotalContractInformation totalContractInformation, String[] urls,
                               MultipartFile[] contractAnnex, MultipartFile[] supplementaryAgreement, MultipartFile[] bidAnnex,  MultipartFile[] awardsAnnex,MultipartFile[] acceptanceReport,MultipartFile[] remarkAnnex)
            throws RuntimeException, IOException {
        //删除文件
        for (String url : urls) {
            QueryWrapper<Annex> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("contract_id",totalContractInformation.getContractId());
            queryWrapper.eq("file",url);
            annexMapper.delete(queryWrapper);
        }
        //如果用户有新添加文件，插入数据库
        saveFile(contractAnnex,totalContractInformation,1);
        saveFile(supplementaryAgreement,totalContractInformation,4);
        saveFile(bidAnnex,totalContractInformation,3);
        saveFile(awardsAnnex,totalContractInformation,2);
        saveFile(acceptanceReport,totalContractInformation,5);
        saveFile(remarkAnnex,totalContractInformation,6);
        //更新合同信息
        UpdateWrapper<TotalContractInformation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("contract_id",totalContractInformation.getContractId());
        if(totalContractInformationMapper.update(totalContractInformation,updateWrapper)!=0){
            //说明修改成功
            return CommonResult.success("修改成功");
        }
        return CommonResult.fail("修改失败");
    }


    private void saveFile(MultipartFile[] file,TotalContractInformation totalContractInformation,Integer type) throws IOException {
        if(file==null){
            return;
        }
        for (MultipartFile multipartFile : file) {
            if(!multipartFile.isEmpty()){
                //将文件信息插入表中
                Annex annex = new Annex();
                annex.setContractId(totalContractInformation.getContractId());
                annex.setType(type);
                annex.setFile(env.getProperty("httppath")+env.getProperty("filepath")+multipartFile.getOriginalFilename());
                multipartFile.transferTo(new File(env.getProperty("filepath")+multipartFile.getOriginalFilename()));
                annexMapper.insert(annex);
            }
        }
    }

    private void copyProperties(TotalContractInformation totalContractInformation, Contract contract,Cooperator cooperator,CompletionInformation completionInformation){
        BeanUtils.copyProperties(contract,totalContractInformation);
        BeanUtils.copyProperties(cooperator,totalContractInformation);
        BeanUtils.copyProperties(completionInformation,totalContractInformation);
    }

}
