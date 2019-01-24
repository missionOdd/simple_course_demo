package com.mission.course.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mission
 * @date 2018/10/9 0009-18:54
 */
@Controller
@RequestMapping("frontend")
public class FrontendController {

  @GetMapping("index")
  public String Index(){
    return "frt/index";
  }


  @GetMapping("postlist")
  public String postlist(){
    return "frt/postlist";
  }
  @GetMapping("noticelist")
  public String noticelist(){
    return "frt/noticelist";
  }
  @GetMapping("albumlist")
  public String albumlist(){
    return "frt/albumlist";
  }

  @GetMapping("videolist")
  public String videolist(){
    return "frt/videolist";
  }

  @GetMapping("filelist")
  public String filelist(){
    return "frt/filelist";
  }

  @GetMapping("about")
  public String about(){
    return "frt/about";
  }


  @GetMapping("notice")
  public String Notice(){
    return "frt/notice";
  }



  @GetMapping("post")
  public String Post(){
    return "frt/post";
  }

  @GetMapping("photo")
  public String photo(){
    return "frt/photo";
  }

  @GetMapping("video")
  public String video(){
    return "frt/video";
  }

}
