

$(function () {

    var PostUrl="/post/getlist?pageIndex=1&pageSize=6&priority=-1";
    var postCategoryUrl = "/postCategory/getlist";
    var getAuthorUrl='/user/getauthorlist';
    getPostList(PostUrl);
    getpostAuthor(getAuthorUrl);
    getpostCategory(postCategoryUrl);



    $("#input_post_author").on('change',function(){
        changeList(PostUrl);
        $('#pagination-demo').twbsPagination('destroy');
    });


    $("#input_post_category").on('change',function(){
        changeList(PostUrl);
        $('#pagination-demo').twbsPagination('destroy');
    });
});


function changeList(getPostUrl) {
    var authorId=$("#input_post_author").find("option:selected").val();
    var postCategoryId=$("#input_post_category").find("option:selected").val();
    var queryParm='';
    if (authorId>0){
        queryParm +='&authorId='+authorId;

    }
    if (postCategoryId>0){
        queryParm +='&postCategoryId='+postCategoryId;
    }
    getPostList(getPostUrl+queryParm);
}

function  getPostList(getUrl) {

    $.getJSON(getUrl, function (data) {
        var html='';
        if (data.state==200) {

            var List =data.data.records;
            List.map(function (item,index) {
                if (item.thumb){
                    html += '' +
                        '<div class="mdui-col-sm-6">' +
                        '        <div class="mdui-card">\n' +
                        '            <div class="mdui-card-media">\n' +
                        '                <img src="' + item.thumb.thumbSrc + '"/>\n' +
                        '                <div class="mdui-card-menu">\n' +
                        '                    <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-primary">\n' +
                        '                <div class="mdui-card-primary-title">' + item.postTitle + '</div>\n' +
                        '                <div class="mdui-card-primary-subtitle">分类:' + item.postCategory.postCategoryName + '</div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-content">' + item.postSummary + '</div>\n' +
                        '            <div class="mdui-card-actions">\n' +
                        '                <p>创建时间:' + item.createTime + '</p  class="mdui-btn mdui-col-xs-1">'+
                        '              <a  href="/frontend/post?postId='+item.postId +'"  data-id="' + item.postId + '" class="mdui-btn mdui-col-xs-1">打开</a>'

                    jQuery.ajax({
                        type: "get",
                        async: false,
                        url: "/visit/get?postId="+item.postId,
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        cache: false,
                        success: function (data) {
                            if (data.state==200) {
                                html += '' +
                                    '                <a   class="mdui-btn mdui-col-xs-3">阅读次数:' + data.data.visit + '</a>' +
                                    '                <a   class="mdui-btn mdui-col-xs-3">点赞人数:' + data.data.like + '</a>' ;
                            }

                        }
                    });
                    jQuery.ajax({
                        type: "get",
                        async: false,
                        url: "/comment/getcount?postId="+item.postId,
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        cache: false,
                        success: function (data) {
                            if (data.state==200) {
                                html += '' +
                                    '                <a   class="mdui-btn mdui-col-xs-3">评论条数:'+data.count+'</a>' +
                                    '            </div>\n' +
                                    '            </div>\n' +
                                    '        </div>\n';
                            }

                        }
                    });


                }

            });

            var count=data.data.pages;
            if (count>0){
            $('#pagination-demo').twbsPagination({
                totalPages:count,
                visiblePages: 4,
                next: 'Next',
                prev: 'Prev',
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page) + ' content here';
                    var changePostUrl="/post/getlist?pageIndex="+page+"&pageSize=6&priority=-1";
                    changeList(changePostUrl);
                }
            });
            }
        }
        $('#post_card').html(html);
    })


}

function getpostAuthor(getAuthorUrl) {
    $.getJSON(getAuthorUrl, function (data) {
        if (data.state == 200) {
            var html = '<option value="0">不限作者</option>';

            data.data.map(function (item, index) {
                html += '<option value="' + item.userId + '">'+item.username+'</option>';
            });
            $('#input_post_author').html(html);
                new mdui.Select('#input_post_author');

        }
    });
}

function getpostCategory(postCategoryUrl) {
    $.getJSON(postCategoryUrl, function (data) {
        if (data.state == 200) {
            var html1 = '<option value="0">所有分类</option>';

            data.data.map(function (item, index) {
                html1 += '<option value="' + item.postCategoryId + '">'+item.postCategoryName+'</option>';
            });
            $('#input_post_category').html(html1);
                new mdui.Select('#input_post_category');
        }
    });
}



