<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广东海洋大学精品课程</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel=" icon" href="/favicon.ico">
    <link href="../css/yoann.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="/js/admin/menu.js"></script>
    <script src="/js/common/common.js"></script>
    <script src="/js/admin/postcatelist.js"></script>
</head>

<body class="mdui-drawer-body-left mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded">

<!--导航栏-->
<div class="mdui-appbar mdui-appbar-fixed">
    <div class="mdui-toolbar mdui-color-indigo">
        <a href="javascript:;" class="mdui-btn mdui-btn-icon" mdui-drawer="{target: '#left-drawer'}"><i class="mdui-icon material-icons">menu</i></a>
        <a id ="site_title" href="javascript:;" class="mdui-typo-headline">精品课程</a>
        <a id ="site_headline" href="javascript:;" class="mdui-typo-title">广东海洋大学</a>
        <div class="mdui-toolbar-spacer"></div>
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
    <a class="yoann mdui-text-center mdui-img-fluid mdui-hidden-sm-down" style="position:absolute;left:45%;top:40%;">精品课程-文章</a>
    <div id="headline_img"></div>

</div>
<!--中间内容-->
<div class="mdui-container">
    <div class="mdui-table-fluid">
        <table class="mdui-table mdui-table-hoverable">
            <thead>
            <tr>
                <th>#</th>
                <th>分类名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="post_cate_table">
            </tbody>
        </table>
    </div>
</div>
<br/><br/><br/>
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