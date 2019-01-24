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
    <link href="//cdn.bootcss.com/photoswipe/4.1.2/photoswipe.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/photoswipe/4.1.2/default-skin/default-skin.css" rel="stylesheet">
    <style>
        * {margin: 0;padding: 0;}
        .clearfix::before, .clearfix::after {
            display: block;
            content: '';
            visibility: hidden;
            height: 100%;
            clear: both;
        }
        body {padding-top: 2%;}
        .my-gallery {width:96%;margin: 0 auto;}
        .my-gallery .img-dv {width:100%;margin-bottom: 1%;}
        .my-gallery .img-dv a {display:block;width:100%;text-align: center}
        .my-gallery .img-dv a img {width:100%;}
    </style>
    <script src="/js/frontend/menu.js"></script>
    <script src="/js/common/common.js"></script>
    <script src="/js/frontend/photo.js"></script>
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
    <a class="yoann mdui-text-center mdui-img-fluid mdui-hidden-sm-down" style="position:absolute;left:40%;top:40%;">精品课程-相册</a>
    <div id="headline_img"  align="center"></div>

</div>
<!--中间内容-->
<div class="mdui-container">
    <div>
        <label class="mdui-textfield-label">相册</label>
        <select id="input_photo_cate" class="mdui-select">
            <option value="1">State 1</option>
            <option value="2">State 2</option>
            <option value="3">State 3</option>
            <option value="4">State 4</option>
        </select>
    </div>

    <div class="mdui-panel" mdui-panel>


        <div class="mdui-panel-item mdui-panel-item-open">
            <div class="mdui-panel-item-header">
                <div id="photo_cate_name" class="mdui-panel-item-summary">图片相册</div>
            </div>
            <div class="mdui-panel-item-body mdui-row-xs-3 mdui-row-sm-2 mdui-row-md-3 mdui-row-lg-4 mdui-row-xl-6 mdui-grid-list">

                <!--如果有多个data-pswp-uid 它的值是不能重复的-->
                <div  class="my-gallery" data-pswp-uid="1">
                    <figure>
                        <div class="img-dv"><a href="/upload/files/更新/5fcdb87b8f89166ca3435bbe_rw_1920.jpg" data-size="1920x1080"><img src="img/img1.jpg"></a></div>
                        <figcaption style="display:none;">在这里可增加图片描述</figcaption>
                    </figure>

                </div>


                <#--photoSwipe 内容-->
                <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="pswp__bg"></div>
                    <div class="pswp__scroll-wrap">
                        <div class="pswp__container">
                            <div class="pswp__item"></div>
                            <div class="pswp__item"></div>
                            <div class="pswp__item"></div>
                        </div>
                        <div class="pswp__ui pswp__ui--hidden">
                            <div class="pswp__top-bar">
                                <div class="pswp__counter"></div>
                                <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                               <button class="pswp__button pswp__button&#45;&#45;share" title="Share"></button>
                                <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
                                <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                                <div class="pswp__preloader">
                                    <div class="pswp__preloader__icn">
                                        <div class="pswp__preloader__cut">
                                            <div class="pswp__preloader__donut"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                                <div class="pswp__share-tooltip"></div>
                            </div>
                            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
                            </button>
                            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
                            </button>
                            <div class="pswp__caption">
                                <div class="pswp__caption__center"></div>
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
<script src="//cdn.bootcss.com/photoswipe/4.1.2/photoswipe.min.js"></script>
<script src="//cdn.bootcss.com/photoswipe/4.1.2/photoswipe-ui-default.min.js"></script>
<script type="text/javascript" src="/js/common/initPhotoSwipe.js"></script>
</html>