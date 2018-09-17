package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private int page = 1, size = 10;

    public static PageDTO of(){
        return new PageDTO();
    }


    public PageDTO setPage(int page) {
        this.page = page;
        return this;
    }

    public PageDTO setSize(int size) {
        this.size = size;
        return this;
    }


}

