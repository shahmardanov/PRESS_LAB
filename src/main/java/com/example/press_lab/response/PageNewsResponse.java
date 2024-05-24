package com.example.press_lab.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageNewsResponse {

    private List<NewsResponse> newsResponseList;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer lastPageNumber;

    private Long totalElements;

    private boolean hasNextPage;


}
