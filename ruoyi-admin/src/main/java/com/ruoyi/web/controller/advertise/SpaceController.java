package com.ruoyi.web.controller.company;

import com.ruoyi.advertise.domain.AdvSpace;
import com.ruoyi.advertise.service.impl.SpaceService;
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
@RequestMapping("/advertise/space/data")
public class SpaceController extends BaseController {

    @Autowired
    private SpaceService spaceService;

    @PreAuthorize("@ss.hasPermi('advertise:space:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdvSpace advSpace)
    {
        startPage();
        List<AdvSpace> list = spaceService.selectSpaceByParams(advSpace);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('advertise:space:add')")
    @Log(title = "广告位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AdvSpace space)
    {
        space.setCreateBy(getUsername());
        return toAjax(spaceService.insertSpace(space));
    }

    @PreAuthorize("@ss.hasPermi('advertise:space:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(spaceService.selectSpaceById(id));
    }

    @PreAuthorize("@ss.hasPermi('advertise:space:remove')")
    @Log(title = "广告位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        spaceService.deleteSpaceById(id);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('advertise:space:edit')")
    @Log(title = "广告位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AdvSpace space)
    {
        space.setUpdateBy(getUsername());
        return toAjax(spaceService.updateSpace(space));
    }

}
