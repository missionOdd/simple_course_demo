package com.mission.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * 图片流封装
 * @author mission
 * @date 2018/8/28 0028-16:56
 */
@Getter
@Setter
@AllArgsConstructor
public class ImageHolder {
  private String imageName;
  private InputStream image;


}
