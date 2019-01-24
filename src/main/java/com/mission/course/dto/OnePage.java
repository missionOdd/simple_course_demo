package com.mission.course.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mission.course.entity.Comment;
import com.mission.course.entity.Post;
import com.mission.course.entity.PostDetail;
import com.mission.course.entity.Visit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 文章页封装
 * @author mission
 * @date 2018/10/7 0007-15:03
 */
@Data
public class OnePage {

  @ApiModelProperty(value = "文章")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Post post;

  @ApiModelProperty(value = "文章明细")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PostDetail postDetail;


  @ApiModelProperty(value = "文章评论")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Comment> comment;

  @ApiModelProperty(value = "文章访问")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Visit> visit;

}
