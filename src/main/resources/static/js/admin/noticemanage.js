var getNoticeUrl="/notice/getlist?pageIndex=1&pageSize=6&priority=-1";
$(function () {

    getList(getNoticeUrl);


    var getAuthorUrl='/user/getauthorlist';

    getNoticeAuthor(getAuthorUrl);

    $("#input_post_author").on('change',function(){
        changeList(getNoticeUrl);
        $('#pagination-demo').twbsPagination('destroy');
    });

});



function changeList(getNoticeUrl) {
    var authorId=$("#input_post_author").find("option:selected").val();
    var queryParm='';
    if (authorId>0){
        queryParm +='&authorId='+authorId;
    }
    getList(getNoticeUrl+queryParm);
}




function openDialog(noticeId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/notice/delete?noticeId='+noticeId,
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


function  getList(getNoticeUrl) {

    var html= "";
    var count;
    $.getJSON(getNoticeUrl, function (data) {

        if (data.state==200) {
            var List =data.data.records;
            var FirstIndex=(data.data.current-1)*(data.data.size)+1;
            List.map(function (item,index) {
                var Tipcontent= '公告标题:'+item.noticeTitle+'<br/>'+
                    '修改时间:'+item.lastEditTime+'<br/>'+
                    '创建时间:'+item.createTime+'<br/>'

                html += ''+'<div class="mdui-panel-item">' +
                    ' <div class="mdui-panel-item-header">'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-1">'+(index+FirstIndex)+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">'+item.noticeTitle+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">'+item.priority+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">'+item.user.username+'</div>'+
                    '<div class="mdui-panel-item-summary mdui-col-xs-2">'+item.lastEditTime+'</div>'+
                    '<a  id="tip'+item.noticeId+'" class="mdui-btn mdui-col-xs-1" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                    '<a  href="/admin/addnotice?noticeId='+item.noticeId+'" id="modify'+item.noticeId+'" class="mdui-btn mdui-col-xs-1">修改</a>'+
                    '<a  onclick="openDialog('+item.noticeId+')"  data-id="'+item.noticeId+'" class="mdui-btn mdui-col-xs-1">删除</a>'+
                    '<i class="mdui-panel-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>'+
                    '</div>'+
                    '  <div class="mdui-panel-item-body">\n' +
                    '<img src="'+item.thumb.thumbSrc+'"/>'+
                    '<pre>' +
                    item.noticeContent+
                    '</pre>'+
                    '                <div class="mdui-panel-item-actions">\n' +
                    '                    <button class="mdui-btn mdui-ripple">cancel</button>\n' +
                    '                    <button class="mdui-btn mdui-ripple">save</button>\n' +
                    '                </div>' +
                    '            </div>'+
                    '</div>';


            });
            $('#notice_table').html(html);

            count=data.data.pages;
            $('#pagination-demo').twbsPagination({
                totalPages:count,
                visiblePages: 4,
                next: 'Next',
                prev: 'Prev',
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page) + ' content here';
                    var changePostUrl="/notice/getlist?pageIndex="+page+"&pageSize=6&priority=-1";
                    changeList(changePostUrl);
                }
            });

        }
    })
}

function getNoticeAuthor(getAuthorUrl) {
    $.getJSON(getAuthorUrl, function (data) {
        if (data.state == 200) {
            var html1 = '<option value="0">不限作者</option>';

            data.data.map(function (item, index) {
                html1 += '<option value="' + item.userId + '">'+item.username+'</option>';
            });
            $('#input_post_author').html(html1);
            new mdui.Select('#input_post_author');

        }
    });
}