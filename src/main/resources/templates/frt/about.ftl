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
    <STYLE type=text/css>
    TD {
        FONT-SIZE: 17px; FONT-FAMILY: "宋体","Arial Narrow","Times New Roman"
    }

    .p1 {
        FONT-SIZE: 17pt; LINE-HEIGHT: 17pt
    }
    .p2 {
        FONT-SIZE: 17pt; COLOR: #c0c0c0; LINE-HEIGHT: 17pt
    }
    .style1 {
        FONT-SIZE: 18px; COLOR: #666666
    }
    .style4 {
        COLOR: #000000
    }
    .style5 {
        FONT-SIZE: 13px
    }
    </STYLE>
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.1/jquery.twbsPagination.min.js"></script>
    <script src="/js/frontend/menu.js"></script>
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
    <a class="yoann mdui-text-center mdui-img-fluid mdui-hidden-sm-down" style="position:absolute;left:40%;top:40%;">精品课程-关于</a>
    <div id="headline_img"  align="center"></div>

</div>
<!--中间内容-->
<div class="mdui-container">
    <div class="mdui-panel" mdui-panel>
        <div class="mdui-panel-item mdui-panel-item-open">
            <div class="mdui-panel-item-header">
                <div class="mdui-panel-item-summary">关于</div>
            </div>
            <div id="notice_card" class="mdui-panel-item-body mdui-grid-list">


                <div align="center">
                    <center>
                        <table height="1" width="608">
                            <tbody>
                            <tr>
                                <td align="justify" width="600" valign="top" height="846">
                                    <table width="100%" border="0" height="129">
                                        <tbody>
                                        <tr>
                                            <td width="600" height="125" valign="top"><font size="2">
                                                <p>　</font><table BORDER="1" CELLSPACING="0" CELLPADDING="0" WIDTH="228" align="right" height="90" id="table1">
                                                <tr>
                                                    <td WIDTH="25" rowspan="3" valign="middle" height="88"><b>
                                                        <p align="center">校<br>
                                                            <br>
                                                            办</b></td>
                                                    <td WIDTH="200" height="30"><b>
                                                        <p align="center">受 控 文 件</b></td>
                                                </tr>
                                                <tr>
                                                    <td WIDTH="200" height="30"><b>
                                                        <p align="center">&nbsp;编号
                                                            GDOU-T-10-150</b></td>
                                                </tr>
                                                <tr>
                                                    <td WIDTH="200" height="30">
                                                        <b>&nbsp; 日期 2006.05.17</b></td>
                                                </tr>
                                            </table>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                    </center>
                    </center>
                    <P align=center><span style="letter-spacing: 8pt">
	<font face="方正小标宋简体" color="#FF0000" style="font-size: 35pt">广东海洋大学文件</font></span></P>
                    <center>
                        <table border="0">
                            <tbody>
                            <tr>
                                <td width="600" height="30"></td>
                            </tr>
                            </tbody>
                        </table>
                        <p style="line-height: 200%; margin-top: 0px; margin-bottom: 0px" align="center"><span style="mso-bidi-font-size: 10.0pt; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA">校教务〔2006〕</span>56号</p>
                        <font size="2">
                            <hr color="#ff0000" width="600">
                        </font>
                        <table width="100%" border="0" height="30">
                            <tbody>
                            <tr>
                                <td width="600" height="26"></td>
                            </tr>
                            </tbody>
                        </table>
                        <p ALIGN="CENTER" style="margin-top: 0; margin-bottom: 0">
                            <font face="方正小标宋简体" style="font-size: 17pt">印发《广东海洋大学精品课程管理暂行办法》的通知</font></p>
                        <div align="center">
                            <table border="0" cellspacing="1" width="570" height="39">
                                <tr>
                                    <td height="35"></td>
                                </tr>
                            </table>
                        </div>
                    </center>
                    <p ALIGN="left" style="line-height: 160%; text-indent: 0; margin-top: 0; margin-bottom: 0">各单位、部门：</p>
                    <p ALIGN="left" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0">现将《广东海洋大学精品课程管理暂行办法》印发给你们，请认真遵照执行。</p>
                    <p align="left" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0">本办法不印发文字稿，请在学校主页——校内信息——学校文件——教学管理从本通知中查阅。<font FACE="黑体" LANG="ZH-CN" SIZE="5"></p>
                    </font><center>
                    <table border="0" height="83" width="600">
                        <tr>
                            <td width="580" valign="middle" height="44" align="center" colspan="3"></td>
                        </tr>
                        <tr>
                            <td width="297" valign="middle" height="2" align="center"></td>
                            <td width="264" valign="middle" height="2" align="center">
                                <p>广&nbsp; 东&nbsp;&nbsp;海&nbsp;&nbsp;洋 &nbsp;大&nbsp;&nbsp;学</p>
                            </td>
                            <td width="19" valign="middle" height="2" align="center"></td>
                        </tr>
                    </table>
                    <table border="0" width="600">
                        <tr>
                            <td height="10"></td>
                        </tr>
                    </table>
                    <table border="0" width="600">
                        <tr>
                            <td width="293" align="center" valign="middle"></td>
                            <td width="268" align="center" valign="middle">
                                <p align="center">二ＯＯ六年五月十七日</td>
                            <td width="19" align="center" valign="middle"></td>
                        </tr>
                    </table>
                    <table width="100%" border="0" height="31">
                        <tbody>
                        <tr>
                            <td width="600" height="27"></td>
                        </tr>
                        </tbody>
                    </table>
                    <p style="MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px; LINE-HEIGHT: 100%" align="left"><font face="黑体">主题词：</font><font face="方正小标宋简体">教学
                        精品课程 管理办法&nbsp; 印发通知</font></p>
                    <hr size="1">
                    <div align="center">
                        <table border="0" cellpadding="0" cellspacing="0" width="565" height="23">
                            <tr>
                                <td width="376" height="23">广东海洋大学校长办公室</td>
                </center>
                    <td width="160" height="23">
                        <p align="left">2006年5月17日印</td>
                    </tr>
                    </table>
                </div>
                <center>
                    <hr SIZE="1" width="600">
                    <div align="center">
                        <table border="0" cellpadding="0" cellspacing="0" width="565" height="23">
                            <tr>
                                <td width="350" height="23">录入：吴增学</td>
                </center>
                <td width="201" height="23">
                    <p align="left">校对：周&nbsp; 玲、刘&nbsp; 岭</p>
                </td>
                </tr>
                </table>
            </div>
            <center>
                <hr SIZE="1" width="600">
                <p style="line-height: 100%; margin-right: 25; margin-top: 0px; margin-bottom: 0px" align="right">（共印70份）&nbsp;</p>
                <p style="line-height: 100%; margin-right: 25; margin-top: 0px; margin-bottom: 0px" align="right">　</p>
                <p style="line-height: 100%; margin-right: 25; margin-top: 0px; margin-bottom: 0px" align="right"></center></td>
            </tr>
            <tr>
                <td align="justify" width="600" valign="top" height="59">
                    <p ALIGN="CENTER" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0">
                        <font face="方正小标宋简体" style="font-size: 17pt">广东海洋大学精品课程管理暂行办法</font></p>
                    <font FACE="Times New Roman" SIZE="5">
                        <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0">　</p>
                    </font>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">第一条
                        为进一步加强我校精品课程建设，深入推动教学内容和课程体系的改革，不断提高教学水平和人才培养质量，结合我校课程建设的实际情况，制定本办法。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">第二条
                        校级精品课程申报原则上每年组织1次，与省级精品课程的申报同步进行。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">第三条
                        申报精品课程须具备以下条件：</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（一）连续开设3年以上的公共课、学科基础课、专业基础课或者专业方向课，具有长期坚持课程建设的传统；</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（二）课程负责人应为学术和道德水准高、教学经验丰富、具副教授以上职称的在编教师，应主讲本门课程，并具体组织实施课程建设；</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（三）课程组成员中一般要有35岁以下的年轻教师参加，并形成合理的结构；</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（四）教学理念先进，深入开展教学改革，在教学中注重能力培养，教学效果得到公认；</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（五）必须是网络课程，与该门课程有关的教学大纲、授课教案、习题、实验指导、参考文献目录、主讲教师现场教学录像均应已上校园网。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">第四条
                        评审办法具体如下：</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（一）课程负责人根据精品课程建设的有关要求，研究确定本课程具体的建设目标和建设内容，填写《广东海洋大学精品课程申报表》（请从教务处主页下载），由课程负责人报送所在单位，所在单位按照精品课程建设的有关要求，结合课程建设实际，对各类项目的申报材料进行审查，择优报送教务处，同时报送电子文档。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（二）组织专家对申报的课程建设项目进行初评，评审采取网上评审的方式，评审结果提交学校教学指导委员会评审，推荐产生校级精品课程和申报高一级精品课程的候选课程名单。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（三）公示评审结果，报主管校领导批准。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">第五条
                        入选精品课程的管理按以下办法进行：</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（一）入选国家级精品课程者，学校初期资助经费每门按国家经费1∶1配套；入选省级精品课程者，其目标是创建国家级精品课程，每门课程学校初期资助经费1.5万元；入选校级精品课程者，其目标是创建省级精品课程，每门课程学校初期资助经费0.8万元。精品课程建设项目在申报高一级项目入选后，按高一级项目对待，经费按最高额计，不重复拨付。精品课程建设经费要保证专款专用，视每年建设情况追加建设经费。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（二）所有入选各级精品课程的课程，每年需对照申报表内容撰写精品课程建设报告，其课程资料（网上资料）更新率每年不低于10%。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（三）每年对精品课程进行一次复查。复查不合格的课程限期整改，整改期满仍不合格的，取消“精品课程”称号。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">（四）精品课程建设过程中，课程负责人或课程组成员有重大调整变化时，须由所在单位或课程负责人采取措施以保证项目继续进行，并提出调整意见，由所在单位签署意见后，报教务处批准。</font></p>
                    <p ALIGN="JUSTIFY" style="line-height: 160%; text-indent: 33; margin-top: 0; margin-bottom: 0"><font LANG="ZH-CN">第六条
                        本办法自2006年6月1日起施行，由教务处负责解释。</font></p>
                    <p>　</td>
            </tr>
            </tbody>
            </table>
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
<script>(function(i,s,o,g,r,a,m){i["DaoVoiceObject"]=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;a.charset="utf-8";m.parentNode.insertBefore(a,m)})(window,document,"script",('https:' == document.location.protocol ? 'https:' : 'http:') + "//widget.daovoice.io/widget/APP_ID.js","daovoice");</script>
<script>
    daovoice('init', {
        app_id: "8997440a"
    });
    daovoice('update');


</script>

</html>