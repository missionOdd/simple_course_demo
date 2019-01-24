var PostListUrl="/post/getlist?pageIndex=1&pageSize=5";
var postId = getQueryString("postId");
var parentId;
$(function () {


    var getPostUrl='/post/getone?postId='+postId;
    var getPostDetailUrl='/postDetail/get?postId='+postId;
    var getCommentUrl='/comment/get?pageIndex=1&pageSize=6&postId='+postId;

    getPost(getPostUrl,getPostDetailUrl);

    getNewPostList();
    getVisit(postId);

    getCommentList(getCommentUrl);

    $('#like_btn').click(function () {
        addLiKe(postId)
    });

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
        formData.append('postId',postId);
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


function getPost(getPostUrl,getPostDetailUrl) {
    $.getJSON(getPostUrl, function (data) {
        if (data.state == 200) {
            $('#post_title').text(data.data.postTitle);
            $("#post_category").text(data.data.postCategory.postCategoryName);
            $('#post_createTime').text(data.data.createTime);
            getPostList(PostListUrl+'&authorId='+data.data.userId,"作者相关文章");
            getPostList(PostListUrl+'&postCategoryId='+data.data.postCategoryId,"分类相关文章");
        }
    });

    $.getJSON(getPostDetailUrl, function (data) {
        if (data.state == 200) {
            $(".post_author").text('作者：'+data.data.user.username);
            $("#post_content").html(data.data.postContent);
            new mdui.Select('#input_post_author');
        }
    });
}



function  getPostList(getUrl,listTitle) {

    var html='<li class="mdui-subheader">'+listTitle+'</li>';
    $.getJSON(getUrl, function (data) {
        if (data.state==200) {

            var List =data.data.records;
            List.map(function (item,index) {
                if (item.thumb){
                    html += '' +
                        '     <li class="mdui-list-item mdui-ripple">\n' +
                        '            <a href="/frontend/post?postId='+item.postId+'" class="mdui-list-item-content">'+item.postTitle+'</a>\n'+
                    '</li>';

                }
            });
            $('#post_list').append(html);
        }
    });
}




function  getNewPostList() {

     var  getNewPostUrl="/post/getlist?pageIndex=1&pageSize=5&priority=-1"


    var html= "";
    $.getJSON(getNewPostUrl, function (data) {

        if (data.state==200) {
            var List =data.data.records;
            var FirstIndex=(data.data.current-1)*(data.data.size)+1;
            List.map(function (item,index) {
                var Tipcontent= '文章标题:'+item.postTitle+'<br/>'+
                    '修改时间:'+item.lastEditTime+'<br/>'+
                    '创建时间:'+item.createTime+'<br/>'+
                    '文章类别:'+ item.postCategory.postCategoryName+'<br/>'+
                    '类别描述:'+ item.postCategory.postCategoryDesc;

                html += ''+'<div class="mdui-panel-item">' +
                    ' <div class="mdui-panel-item-header">'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-1">'+(index+FirstIndex)+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">'+item.postTitle+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">分类:'+item.postCategory.postCategoryName+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">'+item.createTime+'</div>'+
                    '<i class="mdui-panel-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>'+
                    '</div>'+
                    '  <div class="mdui-panel-item-body">\n' +
                    '<pre>' +
                    item.postSummary+
                    '</pre>'+
                    '                <div class="mdui-panel-item-actions">\n' +
                    '                    <a href="/frontend/post?postId='+item.postId+'" class="mdui-btn mdui-ripple">查看</a>\n' +
                    '                </div>' +
                    '            </div>'+
                    '</div>';

            });
            $('#new_post_list').html(html);

        }
    })
}


function getVisit(postId) {
    jQuery.ajax({
        type: "get",
        async: false,
        url: "/visit/get?postId=" +postId,
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



function addLiKe(postId) {
    jQuery.ajax({
        type: "POST",
        async: false,
        url: "/visit/like?postId=" +postId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.state==200){
                getVisit(postId);
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
                        var changePostUrl="/comment/get?pageIndex="+page+"&pageSize=6&postId="+postId;
                        getCommentList(changePostUrl);
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
                '<span  class="mdui-chip-delete"><i class="mdui-icon material-icons">cancel</i></span>';
    $('#to_user').html(text);
}