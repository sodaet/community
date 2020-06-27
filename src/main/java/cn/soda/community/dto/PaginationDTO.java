package cn.soda.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.page = page;
        this.totalPage = totalPage;

        pages.add(page);
        for (int i=1; i<= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page-i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        if (page == 1 || page == 0) { //是否展示上一页
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        if (page == totalPage) { //是否展示下一页
            showNext = false;
        } else {
            showNext = true;
        }

        if (pages.contains(1) || pages.contains(0)) { //是否展示第一页
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        if (pages.contains(totalPage)) { //是否展示最后一页
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
