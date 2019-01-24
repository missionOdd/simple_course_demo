package com.mission.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * 文件流封装
 * @author mission
 * @date 2018/10/7 0007-18:36
 */
@Getter
@Setter
@AllArgsConstructor
public class FileHolder {
  private String FileName;
  private InputStream fileInputStream;
}


