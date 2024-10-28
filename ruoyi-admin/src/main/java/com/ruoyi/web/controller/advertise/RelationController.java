package com.ruoyi.web.controller.relation;

import com.ruoyi.advertise.domain.AdvRelation;
import com.ruoyi.advertise.service.impl.RelationService;
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
@RequestMapping("/advertise/relation/data")
public class RelationController extends BaseController {


    @Autowired
    private RelationService relationService;

    @PreAuthorize("@ss.hasPermi('advertise:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdvRelation advRelation)
    {
        startPage();
        List<AdvRelation> list = relationService.selectRelationByParams(advRelation);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('advertise:relation:add')")
    @Log(title = "应用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AdvRelation relation)
    {
        relation.setCreateBy(getUsername());
        return toAjax(relationService.insertRelation(relation));
    }

    @PreAuthorize("@ss.hasPermi('advertise:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(relationService.selectRelationById(id));
    }

    @PreAuthorize("@ss.hasPermi('advertise:relation:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        relationService.deleteRelationById(id);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('advertise:relation:edit')")
    @Log(title = "应用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AdvRelation relation)
    {
        relation.setUpdateBy(getUsername());
        return toAjax(relationService.updateRelation(relation));
    }
}
