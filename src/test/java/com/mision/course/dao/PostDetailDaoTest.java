package com.mision.course.dao;

import com.mision.course.BaseTest;
import com.mission.course.dao.PostDetailMapper;
import com.mission.course.entity.PostDetail;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author mission
 * @date 2018/10/7 0007-14:57
 */
@Slf4j
public class PostDetailDaoTest extends BaseTest {

  @Resource
  private PostDetailMapper postDetailMapper;


  @Resource
  DataFactory df;

  @Test
  @Ignore
  public void testAdd(){
    PostDetail postDetail = new PostDetail();
    postDetail.setPostStatus(1);

    postDetail.setVersion(1);
    for (int i = 0; i <5 ; i++) {
      postDetail.setPostContent(df.getRandomWord(23));
      postDetailMapper.insert(postDetail);
    }



  }
}
