var userId;
var getUrl;
var getAuthorUrl='/user/getauthorlist';
$(function () {

    userId=getQueryString('userId');


    getAuthor();
    getList();

    $("#input_video_author").on('change',function(){
        changeUrl()
        $('#pagination-demo').twbsPagination('destroy');
    });



});

function changeUrl() {
    userId=$("#input_video_author").find("option:selected").val();
        getList();

}


function openDialog(videoId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/video/delete?videoId='+videoId,
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



function getAuthor() {
    $.getJSON(getAuthorUrl, function (data) {
        if (data.state == 200) {
            var html = '<option value="0">不限作者</option>';

            data.data.map(function (item, index) {
                html += '<option value="' + item.userId + '">'+item.username+'</option>';
            });
            $('#input_video_author').html(html);
            if (userId) {
                $('#input_video_author').val(userId);
            }
            new mdui.Select('#input_video_author');

        }
    });
}


function  getList(page) {

    if (page==null){
        getUrl="/video/getlist?pageIndex=1&pageSize=4"
    } else {
        getUrl="/video/getlist?pageIndex="+page+"&pageSize=4";
    }

    if (userId&&userId>0) {
        getUrl +='&userId='+userId;
    }

    var html= "";
    var count;
    $.getJSON(getUrl, function (data) {

        if (data.state==200) {
            var videoList =data.data.records;
            var FirstIndex=(data.data.current-1)*(data.data.size)+1;
            videoList.map(function (item,index) {

                if (item.user){

                html += ''+'<tr>' +
                    '<td>'+(index+FirstIndex)+'</td>'+
                    '<td><img src="'+item.thumbSrc+'"></td>'+
                    '<td>'+item.videoName+'</td>'+
                    '<td>'+item.user.username+'</td>'+
                    '<td>'+item.videoDesc+'</td>'+
                    '<td>' +
                    '<a  href="/admin/addvideo?videoId='+item.videoId+'" id="modify'+item.videoId+'" class="mdui-btn">修改</a>'+
                    '<a  onclick="openDialog('+item.videoId+')"  data-id="'+item.videoId+'" class="mdui-btn">删除</a>'+
                    '<a  href="'+item.videoSrc+'" class="mdui-btn copy-btn">链接</a>'+
                    '</td>'+
                    '</tr>';
                }


            });
            count=data.data.pages;
            if (count>0){
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
        $('#video_table').html(html);
    })
}



