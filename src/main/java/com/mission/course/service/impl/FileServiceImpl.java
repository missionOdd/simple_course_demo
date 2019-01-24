package com.mission.course.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mission.course.common.exception.MyException;
import com.mission.course.common.util.MyFileUtil;
import com.mission.course.dao.FileCategoryMapper;
import com.mission.course.dao.FileMapper;
import com.mission.course.dao.FileTypeMapper;
import com.mission.course.dto.FileHolder;
import com.mission.course.dto.ImageHolder;
import com.mission.course.entity.File;
import com.mission.course.entity.FileCategory;
import com.mission.course.entity.Thumb;
import com.mission.course.service.FileService;
import com.mission.course.service.ThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mission
 * @since 2018-10-05
 */
@Service
@Transactional
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

  @Resource
  private FileMapper fileMapper;

  @Resource
  private FileCategoryMapper fileCategoryMapper;

  @Resource
  private FileTypeMapper fileTypeMapper;

  @Resource
  private ThumbService thumbService;

  @Override
  public List<File> getFileList(Page page, File fileCondition) {

    return fileMapper.getFileList(page, fileCondition);
  }

  @Override
  public File saveFile(MultipartFile[] files, File file, String sign) {
    FileHolder fileHolder;
    ImageHolder imageHolder;
    try {
      fileHolder = MyFileUtil.getFileHolder(files[0]);
    } catch (IOException e) {
      throw new MyException("得不到文件流");
    }

    if (file.getFileTypeId() != null && file.getFileCategoryId() != null) {
      try {
        if (file.getFileId()!=null){
          File temp=this.getById(file.getFileId());
          Thumb thumbTemp=thumbService.getById(temp.getThumbId());
          MyFileUtil.deleteFileOrPath(temp.getFileSrc());
          thumbService.deleteThumb(thumbTemp);
        }
        FileCategory fileCategory = fileCategoryMapper.selectById(file.getFileCategoryId());
        String fileSrc = MyFileUtil.saveFile(fileHolder, fileCategory.getFileCategoryName());
        file.setFileSrc(fileSrc);
        file.setCreateTime(LocalDateTime.now());
        file.setLastEditTime(LocalDateTime.now());

      Thumb thumb = thumbService.saveThumb(files[1],null,sign);
      file.setThumbId(thumb.getThumbId());
      file.setThumb(thumb);
      if (file.insertOrUpdate()) {
        return file;
      }
      } catch (Exception e) {
        throw new MyException("保存文件错误");
      }
    }
    return null;
  }



  @Override
  public boolean deteleFile(Integer id) {
    File file =fileMapper.selectById(id);
    MyFileUtil.deleteFileOrPath(file.getFileSrc());
    Thumb thumb =new Thumb();
    thumb.setThumbId(file.getThumbId());
    if(fileMapper.deleteById(id)>0&&thumbService.deleteThumb(thumb)){
      return true;
    }
    return false;
  }
}