package com.ruoyi.web.controller.advertise;

import com.ruoyi.advertise.domain.AdvCompany;
import com.ruoyi.advertise.service.impl.CompanyService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/advertise/company/data")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @PreAuthorize("@ss.hasPermi('advertise:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdvCompany advCompany)
    {
        startPage();
        List<AdvCompany> list = companyService.selectCompanyByParams(advCompany);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('advertise:company:add')")
    @Log(title = "应用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AdvCompany company)
    {
        company.setCreateBy(getUsername());
        return toAjax(companyService.insertCompany(company));
    }

    @PreAuthorize("@ss.hasPermi('advertise:company:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(companyService.selectCompanyById(id));
    }

    @PreAuthorize("@ss.hasPermi('advertise:company:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        companyService.deleteCompanyById(id);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('advertise:company:edit')")
    @Log(title = "应用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AdvCompany company)
    {
        company.setUpdateBy(getUsername());
        return toAjax(companyService.updateCompany(company));
    }


}
