<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广东海洋大学精品课程</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel=" icon" href="/favicon.ico">
    <link href="../css/yoann.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-bootstrap/0.5pre/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
    <link rel="stylesheet" href="/css/common/pagination.css">
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.1/jquery.twbsPagination.min.js"></script>
    <script src="/js/admin/menu.js"></script>
    <script src="/js/common/common.js"></script>
    <script src="/js/admin/index.js"></script>
</head>

<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded">

<!--导航栏-->
<div class="mdui-appbar mdui-appbar-fixed">
    <div class="mdui-toolbar mdui-color-indigo">
        <a href="javascript:;" class="mdui-btn mdui-btn-icon" mdui-drawer="{target: '#left-drawer'}"><i class="mdui-icon material-icons">menu</i></a>
        <a id ="site_title" href="javascript:;" class="mdui-typo-headline">精品课程</a>
        <a id ="site_headline" href="javascript:;" class="mdui-typo-title">广东海洋大学</a>
        <div class="mdui-toolbar-spacer"></div>
                <#if Session.user?exists>
                 <button class="mdui-btn mdui-color-blue" mdui-menu="{target: '#example-1'}">${Session.user.username}</button>
                 <ul class="mdui-menu" id="example-1">
                     <li class="mdui-menu-item">
                         <a href="javascript:;" class="mdui-ripple">Settings</a>
                     </li>
                     <li class="mdui-divider"></li>
                     <li class="mdui-menu-item">
                         <a href="/logout" class="mdui-ripple">Logout</a>
                     </li>
                 </ul>
                <#else>
             <a href="/login" class="mdui-btn mdui-btn-dense mdui-color-theme-accent mdui-ripple">登录</a>

                </#if>
        <a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">search</i></a>
    </div>
</div>

<!--左侧栏-->
<div class="mdui-drawer" id="left-drawer">
    <ul class="mdui-list" mdui-collapse="{accordion: true}" style="max-width: 360px;">
        <li id="menu_home" class="mdui-list-item mdui-ripple">
            <i class="mdui-list-item-icon mdui-icon material-icons">home</i>
            <div class="mdui-list-item-content">Home</div>
        </li>
        <li class="mdui-subheader">功能模块</li>
        <li id="menu_sitemanage" class="mdui-list-item mdui-ripple">
            <i class="mdui-list-item-icon mdui-icon material-icons">dashboard</i>
            <div class="mdui-list-item-content">站点配置</div>
        </li>
        <li  class="mdui-collapse-item">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">folder_open</i>
                <div class="mdui-list-item-content">文件模块</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <ul class="mdui-collapse-item-body mdui-list mdui-list-dense mdui-color-grey-50" style="">
                <li id="menu_filemanage" class="mdui-list-item mdui-ripple">文件管理</li>
                <li id="menu_addfile" class="mdui-list-item mdui-ripple">添加文件</li>
                <li id="menu_filecatelist" class="mdui-list-item mdui-ripple">文件分类</li>
                <li id="menu_addfilecate" class="mdui-list-item mdui-ripple">添加文件分类</li>
            </ul>
        </li>
        <li class="mdui-collapse-item">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">photo</i>
                <div class="mdui-list-item-content">图片模块</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <ul class="mdui-collapse-item-body mdui-list mdui-list-dense mdui-color-grey-50">
                <li id="menu_photomanage" class="mdui-list-item mdui-ripple">图片管理</li>
                <li id="menu_addphoto" class="mdui-list-item mdui-ripple">添加图片</li>
                <li id="menu_albummanage" class="mdui-list-item mdui-ripple">相册管理</li>
                <li id="menu_addalbum" class="mdui-list-item mdui-ripple">添加相册</li>
            </ul>
        </li>
        <li class="mdui-collapse-item">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">library_books</i>
                <div class="mdui-list-item-content">文章模块</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <ul class="mdui-collapse-item-body mdui-list mdui-list-dense mdui-color-grey-50" style="">
                <li id="menu_postmanage" class="mdui-list-item mdui-ripple">文章管理</li>
                <li id="menu_addpost" class="mdui-list-item mdui-ripple">添加文件</li>
                <li id="menu_authormanage" class="mdui-list-item mdui-ripple">作者管理</li>
                <li id="menu_addauthor" class="mdui-list-item mdui-ripple">添加作者</li>
                <li id="menu_postcatelist" class="mdui-list-item mdui-ripple">文章分类</li>
                <li id="menu_addpostcate" class="mdui-list-item mdui-ripple">添加文章分类</li>
            </ul>
        </li>
        <li class="mdui-collapse-item">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">assignment</i>
                <div class="mdui-list-item-content">公告模块</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <ul class="mdui-collapse-item-body mdui-list mdui-list-dense mdui-color-grey-50">
                <li id="menu_noticemanage" class="mdui-list-item mdui-ripple">公告管理</li>
                <li id="menu_addnotice" class="mdui-list-item mdui-ripple">添加公告</li>
            </ul>
        </li>
        <li class="mdui-collapse-item">
            <div class="mdui-collapse-item-header mdui-list-item mdui-ripple">
                <i class="mdui-list-item-icon mdui-icon material-icons">subscriptions</i>
                <div class="mdui-list-item-content">视频模块</div>
                <i class="mdui-collapse-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
            </div>
            <ul class="mdui-collapse-item-body mdui-list mdui-list-dense mdui-color-grey-50">
                <li id="menu_videomanage" class="mdui-list-item mdui-ripple">视频管理</li>
                <li id="menu_addvideo" class="mdui-list-item mdui-ripple">添加视频</li>
            </ul>
        </li>
    </ul>
</div>

<!--网站头-->
<div class="main-section mdui-color-theme">
    <a class="yoann mdui-text-center mdui-img-fluid mdui-hidden-sm-down" style="position:absolute;left:45%;top:40%;">精品课程-首页</a>
    <div id="headline_img"></div>

</div>
<!--中间内容-->
<div class="mdui-container">
    <div class="mdui-panel" mdui-panel>
        <div class="mdui-panel-item mdui-panel-item-open">
            <div class="mdui-panel-item-header">
                <div class="mdui-panel-item-summary">最新公告</div>
                <a  href="/admin/noticemanage"  class="mdui-btn mdui-col-xs-1">更多>></a>
            </div>
                <div id="notice_card" class="mdui-panel-item-body mdui-grid-list">


                <div class="mdui-card">
                    <div class="mdui-card-media">
                        <img src="//mdui-aliyun.cdn.w3cbus.com/docs/assets/docs/img/card.jpg"/>
                        <div class="mdui-card-menu">
                            <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>
                        </div>
                    </div>
                    <div class="mdui-card-primary">
                        <div class="mdui-card-primary-title">Title</div>
                        <div class="mdui-card-primary-subtitle">Subtitle</div>
                    </div>
                    <div class="mdui-card-content">子曰：「学而时习之，不亦说乎？有朋自远方来，不亦乐乎？人不知，而不愠，不亦君子乎？」</div>
                    <div class="mdui-card-actions">
                        <button class="mdui-btn mdui-ripple">action 1</button>
                        <button class="mdui-btn mdui-ripple">action 2</button>
                        <button class="mdui-btn mdui-btn-icon mdui-float-right"><i class="mdui-icon material-icons">expand_more</i></button>
                    </div>
                </div>


            </div>
        </div>
        <div class="mdui-panel-item mdui-panel-item-open">
            <div class="mdui-panel-item-header">
                <div class="mdui-panel-item-summary">最新文章</div>
                <a  href="/admin/postmanage"  class="mdui-btn mdui-col-xs-1">更多>></a>
            </div>
            <div id="post_card" class="mdui-panel-item-body mdui-row-3 mdui-grid-list">


                <div class="mdui-row">
                    <div class="mdui-card">
                        <div class="mdui-card-media">
                            <img src="//mdui-aliyun.cdn.w3cbus.com/docs/assets/docs/img/card.jpg"/>
                            <div class="mdui-card-menu">
                                <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>
                            </div>
                        </div>
                        <div class="mdui-card-primary">
                            <div class="mdui-card-primary-title">Title</div>
                            <div class="mdui-card-primary-subtitle">Subtitle</div>
                        </div>
                        <div class="mdui-card-content">子曰：「学而时习之，不亦说乎？有朋自远方来，不亦乐乎？人不知，而不愠，不亦君子乎？」</div>
                        <div class="mdui-card-actions">
                            <button class="mdui-btn mdui-ripple">action 1</button>
                            <button class="mdui-btn mdui-ripple">action 2</button>
                            <button class="mdui-btn mdui-btn-icon mdui-float-right"><i class="mdui-icon material-icons">expand_more</i></button>
                        </div>
                    </div>
                    <div class="mdui-card">
                        <div class="mdui-card-media">
                            <img src="//mdui-aliyun.cdn.w3cbus.com/docs/assets/docs/img/card.jpg"/>
                            <div class="mdui-card-menu">
                                <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>
                            </div>
                        </div>
                        <div class="mdui-card-primary">
                            <div class="mdui-card-primary-title">Title</div>
                            <div class="mdui-card-primary-subtitle">Subtitle</div>
                        </div>
                        <div class="mdui-card-content">子曰：「学而时习之，不亦说乎？有朋自远方来，不亦乐乎？人不知，而不愠，不亦君子乎？」</div>
                        <div class="mdui-card-actions">
                            <button class="mdui-btn mdui-ripple">action 1</button>
                            <button class="mdui-btn mdui-ripple">action 2</button>
                            <button class="mdui-btn mdui-btn-icon mdui-float-right"><i class="mdui-icon material-icons">expand_more</i></button>
                        </div>
                    </div>


                </div>
        </div>

    </div>



</div>

<!--底部-->
<div class="mdui-bottom-nav mdui-color-grey ">
    <div class="doc-footer-nav-text">
        <i class="mdui-icon material-icons">history</i>
        <label>Copyleft 2018 By Mission GNU GPL</label>
    </div>

</div>

</body>
</html>