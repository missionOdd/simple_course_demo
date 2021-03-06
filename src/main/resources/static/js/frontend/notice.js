var noticeId = getQueryString('noticeId');
var parentId;
$(function () {

    var getnoticeUrl = '/notice/getone?noticeId=' + noticeId;

    var getNoticeListUrl="/notice/getlist?pageIndex=1&pageSize=6&priority=-1";

    var getCommentUrl='/comment/get?pageIndex=1&pageSize=6&noticeId='+noticeId;

    getnotice(getnoticeUrl);

    getNoticetList(getNoticeListUrl,"最近公告");

    getVisit(noticeId);

    getCommentList(getCommentUrl);

    $('#like_btn').click(function () {
        addLiKe(noticeId)
    })

    $('#to_user').click(function () {
        $('#to_user').html('');
        editor.txt.html('');
        parentId=null;
    })


    $('#submit').click(function () {
        var userId=$("#from_user");
        var messages=editor.txt.html();


        var formData =new FormData();
        formData.append('userId',userId);
        formData.append('noticeId',noticeId);
        if (parentId>0) {
            formData.append('parentId',parentId);
        }
        formData.append('messages',messages);

        $.ajax({
            url: '/comment/add',
            type: 'POST',
            cache:false,
            data: formData,
            processData: false,
            contentType: false,
            dataType:"json",
            success: function (data) {
                if (data.state==200) {
                    mdui.snackbar({
                        message: '提交成功',
                        position: 'top'
                    });
                    getCommentList(getCommentUrl);

                } else {
                    mdui.snackbar({
                        message: '提交失败',
                        position: 'top'
                    });
                }
            }
        })

    })

});


function getnotice(getnoticeUrl) {
    $.getJSON(getnoticeUrl, function (data) {
        if (data.state == 200) {
            $('#notice_title').text(data.data.noticeTitle);
            $(".notice_author").text('作者：'+data.data.user.username);
            $('#notice_createTime').text(data.data.createTime);
            $('#notice_content').html(data.data.noticeContent);

        }
    });

}



function  getNoticetList(getUrl,listTitle) {

    var html='<li class="mdui-subheader">'+listTitle+'</li>';
    $.getJSON(getUrl, function (data) {
        if (data.state==200) {

            var List =data.data.records;
            List.map(function (item,index) {
                if (item.thumb){
                    html += '' +
                        '     <li class="mdui-list-item mdui-ripple">\n' +
                        '            <a href="/frontend/notice?noticeId='+item.noticeId+'" class="mdui-list-item-content">'+item.noticeTitle+'</a>\n'+
                    '</li>';

                }
            });
            $('#notice_list').append(html);
        }
    });
}




function getVisit(noticeId) {
    jQuery.ajax({
        type: "get",
        async: false,
        url: "/visit/get?noticeId=" +noticeId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.state==200){
                $('#visit_count').text('阅读次数:'+data.data.visit);
            $('#like_count').text('点赞人数:'+data.data.like);
            }

        }
    });
}

function addLiKe(noticeId) {
    jQuery.ajax({
        type: "POST",
        async: false,
        url: "/visit/like?noticeId=" +noticeId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.state==200){
                getVisit(noticeId);
            }

        }
    });
}



function  getCommentList(getUrl) {

    var html='';
    $.getJSON(getUrl, function (data) {
        if (data.state==200) {
            $('#comment_count').text('评论条数:'+data.data.total);
            var List =data.data.records;
            html =commentHtmlList(List);

            var count=data.data.pages;
            if (count>0){
                $('#pagination-demo').twbsPagination({
                    totalPages:count,
                    visiblePages: 4,
                    next: 'Next',
                    prev: 'Prev',
                    onPageClick: function (event, page) {
                        $('#page-content').text('Page ' + page) + ' content here';
                        var changeNoticeUrl="/comment/get?pageIndex="+page+"&pageSize=6&noticeId="+noticeId;
                        getCommentList(changeNoticeUrl);
                    }
                });
            }

        }
        $('#comment_card').html(html);
        mdui.mutation();
    });
}


function commentHtmlList(List) {
    var html='';
    List.map(function (item,index) {

        html += '' +
            '<div class="mdui-panel" mdui-panel>'+
            '<div class="mdui-panel-item mdui-panel-item-open">\n' +
            '         <div class="mdui-panel-item-header">\n' +
            '           <div class="mdui-list-item-avatar"><img src="'+item.user.thumb.thumbSrc+'"/></div>'+
            '                            <div class="mdui-panel-item-summary">'+item.user.username+'</div>\n' +
            '                            <div class="mdui-chip">\n' +
            '                                <button onclick="reply('+item.commentId+',\''+item.user.username+'\')" class="mdui-btn  mdui-ripple">回复</button>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                        <div class="mdui-panel-item-body">\n' +
            '                            <p>'+item.messages+'</p>\n';

        if (item.children){
            html+=commentHtmlList(item.children)+'</div></div></div>\n';
        }else {
            html +='</div></div></div>\n';
        }
    });
    return html;

}

function reply(ToId,ToUser) {
    parentId=ToId;
    editor.txt.html('@'+ToUser);
    var text= '<span class="mdui-chip-title">@'+ToUser+'</span>'+
        '<span  class="mdui-chip-delete"><i class="mdui-icon material-icons">cancel</i></span>'
    $('#to_user').html(text);
}