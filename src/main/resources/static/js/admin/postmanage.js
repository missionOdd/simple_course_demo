var authorId;
var postCategoryId;
var postCategoryUrl = "/postCategory/getlist";
var getAuthorUrl='/user/getauthorlist';
var getUrl;
$(function () {

    getList();

    getpostAuthor();

    getpostCategory();

    $("#input_post_author").on('change',function () {
        changeUrl();
        $('#pagination-demo').twbsPagination('destroy');
    })

    $("#input_post_category").on('change',function () {
        changeUrl();
        $('#pagination-demo').twbsPagination('destroy');
    })

});



function changeUrl() {
    authorId=$("#input_post_author").find("option:selected").val();
    postCategoryId=$("#input_post_category").find("option:selected").val();
    getList();
}



function openDialog(postId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/post/delete?postId='+postId,
            dataType:"json",
            success: function (data) {
                if (data.state == 200) {
                    mdui.snackbar({
                        message: '提交成功',
                        position: 'top'
                    });
                    window.location.reload();

                } else {
                    mdui.snackbar({
                        message: '提交失败',
                        position: 'top'
                    });
                }
            }
        });
    });
}


function  getList(page) {

    if (page==null){
        getUrl="/post/getlist?pageIndex=1&pageSize=5"
    } else {
        getUrl="/post/getlist?pageIndex="+page+"&pageSize=5";
    }


    if (authorId&&authorId>0) {
        getUrl +='&authorId='+authorId;
    }
    if (postCategoryId&&postCategoryId>0) {
        getUrl +='&postCategoryId='+postCategoryId;
    }


    var html= "";
    var count;
    $.getJSON(getUrl, function (data) {
        if (data.state==200) {
            var List =data.data.records;
            var FirstIndex=(data.data.current-1)*(data.data.size)+1;
            List.map(function (item,index) {
                if (item.thumb&&item.postCategory) {
                    var Tipcontent = '文章标题:' + item.postTitle + '<br/>' +
                        '修改时间:' + item.lastEditTime + '<br/>' +
                        '创建时间:' + item.createTime + '<br/>' +
                        '文章类别:' + item.postCategory.postCategoryName + '<br/>' +
                        '类别描述:' + item.postCategory.postCategoryDesc;
                    html += '' + '<div class="mdui-panel-item">' +
                        ' <div class="mdui-panel-item-header">' +
                        '<div class="mdui-panel-item-summary mdui-col-xs-1">' + (index + FirstIndex) + '</div>' +
                        '<div class="mdui-panel-item-summary mdui-col-xs-2">' + item.postTitle + '</div>' +
                        '<div class="mdui-panel-item-summary mdui-col-xs-2">' + item.priority + '</div>' +
                        '<div class="mdui-panel-item-summary mdui-col-xs-2">' + item.postCategory.postCategoryName + '</div>' +
                        '<div class="mdui-panel-item-summary mdui-col-xs-2">' + item.lastEditTime + '</div>' +
                        '<a  id="tip' + item.postId + '" class="mdui-btn mdui-col-xs-1" mdui-tooltip="{content: \'' + Tipcontent + '\'}">详情</a>' +
                        '<a  href="/admin/addpost?postId=' + item.postId + '" id="modify' + item.postId + '" class="mdui-btn mdui-col-xs-1">修改</a>' +
                        '<a  onclick="openDialog(' + item.postId + ')"  data-id="' + item.postId + '" class="mdui-btn mdui-col-xs-1">删除</a>' +
                        '<i class="mdui-panel-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>' +
                        '</div>' +
                        '  <div class="mdui-panel-item-body">\n' +
                        '<img src="' + item.thumb.thumbSrc + '"/>' +
                        '<pre>' +
                        item.postSummary +
                        '</pre>' +
                        '                <div class="mdui-panel-item-actions">\n' +
                        '                    <button class="mdui-btn mdui-ripple">cancel</button>\n' +
                        '                    <a href="/frontend/post?postId=' + item.postId + '" class="mdui-btn mdui-ripple">查看</a>\n' +
                        '                </div>' +
                        '            </div>' +
                        '</div>';
                }


            });

            count=data.data.pages;
            if (count>0) {
            $('#pagination-demo').twbsPagination({
                totalPages:count,
                visiblePages: 4,
                next: 'Next',
                prev: 'Prev',
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page) + ' content here';
                    getList(page);
                }
            });
            }

        }
        $('#post_table').html(html);
    })
}


function getpostAuthor() {
    $.getJSON(getAuthorUrl, function (data) {
        if (data.state == 200) {
            var html = '<option value="0">不限作者</option>';

            data.data.map(function (item, index) {
                html += '<option value="' + item.userId + '">'+item.username+'</option>';
            });
            $('#input_post_author').html(html);
            if (authorId) {
                $('#input_post_author').val(authorId);
            }
                new mdui.Select('#input_post_author');

        }
    });
}

function getpostCategory() {
    $.getJSON(postCategoryUrl, function (data) {
        if (data.state == 200) {
            var html1 = '<option value="0">所有分类</option>';

            data.data.map(function (item, index) {
                html1 += '<option value="' + item.postCategoryId + '">'+item.postCategoryName+'</option>';
            });
            $('#input_post_category').html(html1);
            if (postCategoryId) {
                $('#input_post_category').val(postCategoryId);
            }
                new mdui.Select('#input_post_category');

        }
    });
}
