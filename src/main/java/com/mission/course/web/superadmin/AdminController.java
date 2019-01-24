package com.mission.course.web.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mission
 * @date 2018/10/14 0014-17:22
 */
@Controller
@RequestMapping("admin")
public class AdminController {

  @GetMapping("index")
  public String index(){
    return "ftl/index";
  }

  @GetMapping("sitemanage")
  public String sitemanage(){
    return "ftl/sitemanage";
  }


  @GetMapping("filemanage")
  public String filemanage(){
    return "ftl/filemanage";
  }


  @GetMapping("addfile")
  public String addfile(){
    return "ftl/addfile";
  }

  @GetMapping("filecatelist")
  public String filecatelist(){
    return "ftl/filecatelist";
  }

  @GetMapping("addfilecate")
  public String addfilecate(){
    return "ftl/addfilecate";
  }

  @GetMapping("photomanage")
  public String photomanage(){
    return "ftl/photomanage";
  }

  @GetMapping("addphoto")
  public String addphoto(){
    return "ftl/addphoto";
  }

  @GetMapping("albummanage")
  public String albummanage(){
    return "ftl/albummanage";
  }

  @GetMapping("addalbum")
  public String addalbum(){
    return "ftl/addalbum";
  }

  @GetMapping("postmanage")
  public String postmanage(){
    return "ftl/postmanage";
  }
  @GetMapping("addpost")
  public String addpost(){
    return "ftl/addpost";
  }

  @GetMapping("authormanage")
  public String authormanage(){
    return "ftl/authormanage";
  }


  @GetMapping("addauthor")
  public String addauthor(){
    return "ftl/addauthor";
  }

  @GetMapping("postcatelist")
  public String postcatelist(){
    return "ftl/postcatelist";
  }

  @GetMapping("addpostcate")
  public String addpostcate(){
    return "ftl/addpostcate";
  }

  @GetMapping("noticemanage")
  public String noticemanage(){
    return "ftl/noticemanage";
  }

  @GetMapping("addnotice")
  public String addnotice(){
    return "ftl/addnotice";
  }

  @GetMapping("videomanage")
  public String videomanage(){
    return "ftl/videomanage";
  }

  @GetMapping("addvideo")
  public String addvideo(){
    return "ftl/addvideo";
  }
}
