package com.ruoyi.advertise.service;

import com.ruoyi.advertise.domain.MySuggestion;

import java.util.List;

public interface ISuggestionService {
    void saveSuggestion(MySuggestion suggestion);

    List<MySuggestion> getAllSuggestion();
}
