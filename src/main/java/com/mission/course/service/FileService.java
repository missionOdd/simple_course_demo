package com.mission.course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mission.course.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
public interface FileService extends IService<File> {

  List<File> getFileList(Page page,File fileCondition);

  File saveFile(MultipartFile[] files, File file,String sign);


  boolean deteleFile(Integer id);
}
