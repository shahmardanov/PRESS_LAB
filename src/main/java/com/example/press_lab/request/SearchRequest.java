package com.example.press_lab.request;


import com.example.press_lab.specification.PageCriteria;
import com.example.press_lab.specification.SearchCriteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SearchRequest {

    private List<SearchCriteria> searchCriteriaList;
    private PageCriteria pageCriteria;
}
