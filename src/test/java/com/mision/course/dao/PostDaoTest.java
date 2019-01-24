package com.mision.course.dao;
import com.mision.course.BaseTest;
import com.mission.course.dao.PostMapper;
import com.mission.course.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author mission
 * @date 2018/10/7 0007-14:46
 */
@Slf4j
public class PostDaoTest extends BaseTest {

  @Resource
  private PostMapper postMapper;

  @Resource
  DataFactory df;
  @Test
  @Ignore
  public void testAdd(){

    Post post = new Post();


    for (int i = 0; i <6; i++) {
    post.setPostTitle(df.getRandomText(8));
    post.setPriority(df.getNumberBetween(1,7));
    post.setPostStatus(1);
    post.setLastEditTime(LocalDateTime.now());
    post.setCreateTime(LocalDateTime.now());
    post.setPostSummary(df.getRandomText(15));
    post.setVersion(1);

      postMapper.insert(post);
    }

  }

  @Test
  @Ignore
  public void testselect(){
    Post post = new Post();
    post.setPostId(4);
     // List<Post> postList= postMapper.getPostList(post);
    //  log.info("\n {}",postList);
  }
}
