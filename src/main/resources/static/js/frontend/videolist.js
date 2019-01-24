var userId;
var getUrl;
var getAuthorUrl='/user/getauthorlist';
$(function () {

    userId=getQueryString('userId');


    getAuthor();
    getList();

    $("#input_video_author").on('change',function(){
        changeUrl();
        $('#pagination-demo').twbsPagination('destroy');
    });



});

function changeUrl() {
    userId=$("#input_video_author").find("option:selected").val();

        getList();
        $('#pagination-demo').twbsPagination('destroy');


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
            videoList.map(function (item,index) {

                if (item.user){

                    html += ''+
                        '<div class="mdui-col-sm-6 mdui-col-md-4">'+
                        '        <div class="mdui-card">\n' +
                        '            <div class="mdui-card-media">\n' +
                        '                <img src="' + item.thumbSrc + '"/>\n' +
                        '                <div class="mdui-card-menu">\n' +
                        '                    <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-primary">\n' +
                        '                <div class="mdui-card-primary-title">' + item.videoName + '</div>\n' +
                        '                <div class="mdui-card-primary-subtitle">发布人:' + item.user.username + '<p>描述:' + item.videoDesc+ '</p><p>时间:' + item.createTime+ '</p></div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-actions">\n' +
                        '                <a  href="/frontend/video?videoId='+item.videoId +'"  class="mdui-btn mdui-col-xs-1">打开</a>' +
                        '                 <a  href="'+item.videoSrc+'" class="mdui-btn mdui-col-xs-1">链接</a>'+
                        '            </div>\n' +
                        '            </div>\n' +
                        '        </div>\n';
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
        $('#video_card').html(html);
    })
}

