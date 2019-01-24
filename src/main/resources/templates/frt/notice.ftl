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
    <style type="text/css">
        .toolbar {
            border: 1px solid #ccc;
        }
        .text {
            border: 1px solid #ccc;
            height: 120px;
        }
    </style>
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.1/jquery.twbsPagination.min.js"></script>
    <script src="//unpkg.com/wangeditor/release/wangEditor.min.js"></script>
    <script src="/js/frontend/menu.js"></script>
    <script src="/js/common/common.js"></script>
    <script src="/js/frontend/notice.js"></script>
</head>

<body class="mdui-drawer-body-right mdui-appbar-with-toolbar mdui-theme-primary-indigo mdui-theme-accent-pink mdui-loaded">

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


<!--左侧栏-->

<div class="mdui-drawer mdui-drawer-right" id="drawer">

    <div class="mdui-typo-title">相关公告</div>
    <div class="mdui-divider-dark"></div>
    <ul class="mdui-list" id="notice_list">

    </ul>
</div>

<!--网站头-->
<div class="main-section mdui-color-theme">
    <a class="yoann mdui-text-center mdui-img-fluid mdui-hidden-sm-down" style="position:absolute;left:40%;top:40%;">精品课程-公告</a>
    <div id="headline_img"  align="center"></div>

</div>
<!--中间内容-->
<div class="mdui-container">
    <div class="mdui-panel" mdui-panel>
        <div class="mdui-panel-item mdui-panel-item-open">
            <div class="mdui-panel-item-header">
                <div class="mdui-panel-item-summary">公告内容</div>
                <div class="mdui-chip">
                    <span class="mdui-chip-icon"><i class="mdui-icon material-icons">person</i></span>
                    <span class="mdui-chip-title notice_author">Example</span>
                </div>
            </div>

            <div class="mdui-panel-item-body">
                <h1 id="notice_title" class="doc-title mdui-text-color-theme" style=" text-align:center;">标题</h1>

                <div class="doc-intro-module">
                    <h5 class="notice_author"  style=" text-align:center;">作者</h5>

                    <p style=" text-align:right;"><a id="notice_createTime">创建时间</a></p>
                </div>
                <div id="notice_content" class="mdui-typo">无内容</div>

                <button id="like_btn" class="mdui-fab mdui-color-theme-accent mdui-ripple"><i class="mdui-icon material-icons">thumb_up</i></button>
                &nbsp; &nbsp; &nbsp; &nbsp;
                <div class="mdui-chip">
                    <span id="like_count" class="mdui-chip-title">点赞人数</span>
                </div>
                &nbsp; &nbsp; &nbsp; &nbsp;
                <div class="mdui-chip">
                    <span id="visit_count" class="mdui-chip-title">阅读次数</span>
                </div>
                &nbsp; &nbsp; &nbsp; &nbsp;
                <div class="mdui-chip">
                    <span id="comment_count" class="mdui-chip-title">评论条数</span>
                </div>


            </div>
        </div>




        <div class="mdui-panel-item">
            <div class="mdui-panel-item-header">
                <div class="mdui-panel-item-summary">评论</div>
            </div>
            <div class="mdui-panel-item-body mdui-row-xs-3 mdui-row-sm-4 mdui-row-md-5 mdui-row-lg-6 mdui-row-xl-7 mdui-grid-list">

                <div id="comment_card">
                    <div class="mdui-panel" mdui-panel>
                        <div class="mdui-panel-item">
                            <div class="mdui-panel-item-header">
                                <div class="mdui-panel-item-summary">First</div>
                                <div class="mdui-chip">
                                    <button  class="mdui-btn  mdui-ripple">回复</button>
                                </div>
                            </div>
                            <div class="mdui-panel-item-body">
                                <p>First content</p>
                                <p>First content</p>
                                <p>First content</p>

                                <div class="mdui-panel" mdui-panel>
                                    <div class="mdui-panel-item">
                                        <div class="mdui-panel-item-header">First</div>
                                        <div class="mdui-panel-item-body">
                                            <p>First content</p>
                                            <p>First content</p>
                                            <p>First content</p>

                                            <div class="mdui-panel" mdui-panel>
                                                <div class="mdui-panel-item">
                                                    <div class="mdui-panel-item-header">First</div>
                                                    <div class="mdui-panel-item-body">
                                                        <p>First content</p>
                                                        <p>First content</p>
                                                        <p>First content</p>
                                                    </div>
                                                </div>
                                                <div class="mdui-panel-item">
                                                    <div class="mdui-panel-item-header">Second</div>
                                                    <div class="mdui-panel-item-body">
                                                        <p>Second content</p>
                                                        <p>Second content</p>
                                                        <p>Second content</p>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="mdui-panel-item">
                                        <div class="mdui-panel-item-header">Second</div>
                                        <div class="mdui-panel-item-body">
                                            <p>Second content</p>
                                            <p>Second content</p>
                                            <p>Second content</p>
                                        </div>
                                    </div>
                                </div>

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

    <#if Session.user?exists>
                <div id="editor" class="toolbar" >
                </div>
                <div id="to_user"  class="mdui-chip">

                </div>
                <div id="editor_text" class="text" >
                    <p>说说<b>你的想法</b> 一下吧...</p>
                </div>
                <button id="submit" class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-theme-accent" style="float:right ;">发表评论</button>
      <input id="from_user" type="hidden" value="${Session.user.userId}" />
    <#else>
             请先<a href="/login" class=" mdui-ripple">登录</a>才可以评论

    </#if>


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
<#if Session.user?exists>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor','#editor_text');
    editor.create()
</script>
</#if>
</html>
