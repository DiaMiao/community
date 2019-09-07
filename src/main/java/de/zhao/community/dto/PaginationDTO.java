package de.zhao.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 找个对象用来记录页面的其它重要信息。
 * 页码：用于判断分页显示逻辑。
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0)
                pages.add(0, page - i);
            if (page + i <= this.totalPage)
                pages.add(page + i);
        }


        //判断是否展示上一页按钮
        showPrevious = page != 1;

        //判断是否展示下一页按钮
        showNext = page != this.totalPage;

        //判断是否展示第一页按钮
        showFirstPage = !pages.contains(1);

        //判断是否展示最后一页按钮
        showEndPage = !pages.contains(this.totalPage);

    }
}