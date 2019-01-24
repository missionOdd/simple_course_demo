<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>广东海洋大学精品课程</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel=" icon" href="/favicon.ico">
    <link href="../../css/yoann.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-bootstrap/0.5pre/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
    <link rel="stylesheet" href="/css/common/pagination.css">
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.1/jquery.twbsPagination.min.js"></script>
    <script src="/js/frontend/menu.js"></script>
    <script src="/js/common/common.js"></script>
    <script src="/js/frontend/noticelist.js"></script>
</head>

<body class=" "mdui-bottom-nav-fixed mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded">

<!--导航栏-->
<div class="mdui-appbar mdui-appbar-fixed mdui-appbar-scroll-toolbar-hide">
    <div class="mdui-toolbar mdui-color-indigo ">
        <a id ="site_title" href="javascript:;" class="mdui-typo-headline">精品课程</a>
        <a id ="site_headline" href="javascript:;" class="mdui-typo-title">广东海洋大学</a>
        <div class="mdui-tab  mdui-appbar-scroll-toolbar-hide">
            <a href="/frontend/index/" class="mdui-ripple mdui-ripple-white">首页</a>
            <a href="/frontend/postlist/" class="mdui-ripple mdui-ripple-white">文章</a>
            <a href="/frontend/noticelist/" class="mdui-ripple mdui-ripple-white">公告</a>
            <a href="/frontend/albumlist/" class="mdui-ripple mdui-ripple-white">图片</a>
            <a href="/frontend/videolist/" class="mdui-ripple mdui-ripple-white">视频</a>
            <a href="/frontend/filelist/" class="mdui-ripple mdui-ripple-white">文件</a>
            <a href="/frontend/about/" class="mdui-ripple mdui-ripple-white">关于</a>
        </div>
        <div class="mdui-toolbar-spacer">
        <#if Session.user?exists>
            <#if Session.user.role=='author' || Session.user.role=='admin'>
                <a href="/admin/index/" class="mdui-ripple mdui-ripple-white">管理</a>
            </#if>
                 <button class="mdui-btn mdui-color-theme-accent" mdui-menu="{target: '#example-1'}">${Session.user.username}</button>
                 <ul class="mdui-menu" id="example-1">
                     <li class="mdui-menu-item">
                         <div class="mdui-list-item-avatar"><img src="${Session.user.thumb.thumbSrc}"/></div>${Session.user.username}
                     </li>
                     <li class="mdui-divider"></li>
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
        <div class="mdui-textfield mdui-textfield-expandable mdui-float-right">
            <button class="mdui-textfield-icon mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">search</i></button>
            <input class="mdui-textfield-input" type="text" placeholder="Search"/>
            <button class="mdui-textfield-close mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">close</i></button>
        </div>
        </div>

    </div>

</div>


<!--网站头-->
<div class="main-section mdui-color-theme">
    <a class="yoann mdui-text-center mdui-img-fluid mdui-hidden-sm-down" style="position:absolute;left:40%;top:40%;">精品课程-公告</a>
    <div id="headline_img"  align="center"></div>

</div>
<!--中间内容-->
<div class="mdui-container">

    <div>
        <label class="mdui-textfield-label">作者</label>
        <select id="input_post_author" class="mdui-select">
            <option value="1">State 1</option>
            <option value="2">State 2</option>
            <option value="3">State 3</option>
            <option value="4">State 4</option>
        </select>
    </div>

    <div class="mdui-panel" mdui-panel>
        <div class="mdui-panel-item mdui-panel-item-open">
            <div class="mdui-panel-item-header">
                <div class="mdui-panel-item-summary">所有公告</div>
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

        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <ul id="pagination-demo" class="pagination-sm"></ul>
                    </div>
                </div>
                <div id="page-content" class="page-content">1</div>
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
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</html>