package com.ruoyi.advertise.service.impl;

import com.ruoyi.advertise.domain.MySuggestion;
import com.ruoyi.advertise.mapper.SuggestionMapper;
import com.ruoyi.advertise.service.ISuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService implements ISuggestionService {
    @Autowired
    private SuggestionMapper suggestionMapper;

    @Override
    public void saveSuggestion(MySuggestion suggestion) {
        suggestionMapper.insertSuggestion(suggestion);
    }

    @Override
    public List<MySuggestion> getAllSuggestion() {
        return suggestionMapper.getAllSuggestion();
    }
}
