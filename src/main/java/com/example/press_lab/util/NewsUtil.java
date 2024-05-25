package com.example.press_lab.util;

import com.example.press_lab.specification.PageCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class NewsUtil {

    public static Pageable getDefaultPageable(PageCriteria pageCriteria) {
        var pageNumber = pageCriteria.getPageNumber() == null ? 0 : pageCriteria.getPageNumber();
        var pageSize = pageCriteria.getPageSize() == null ? 10 : pageCriteria.getPageSize();
        var sort = pageCriteria.getSort() == null ? new String[]{"id", "asc"} : pageCriteria.getSort();

        Pageable pageable;
        switch (sort[1]) {
            case "asc" -> pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort[0]));
            case "desc" -> pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort[0]).descending());
            default -> throw new RuntimeException("Please enter the valid sort direction like {asc,desc}");
        }
        return pageable;
    }


}
