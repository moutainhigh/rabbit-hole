package com.github.lotus.chaos.biz.modules.lang.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class UnsplashPagingDto {

    /**
     * total : 0
     * total_pages : 0
     * results : []
     */

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("results")
    private List<UnsplashPhotoDto> results = Collections.emptyList();
}
