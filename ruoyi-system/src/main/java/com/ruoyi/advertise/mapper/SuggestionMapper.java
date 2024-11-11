package com.ruoyi.advertise.mapper;

import com.ruoyi.advertise.domain.AdvCompany;
import com.ruoyi.advertise.domain.MySuggestion;

import java.util.List;

public interface SuggestionMapper {


    public int insertSuggestion(MySuggestion suggestion);

    List<MySuggestion> getAllSuggestion();

}
