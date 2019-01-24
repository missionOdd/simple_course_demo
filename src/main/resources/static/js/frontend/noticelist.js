$(function () {

    var getNoticeUrl="/notice/getlist?pageIndex=1&pageSize=6&priority=-1";

    var getAuthorUrl='/user/getauthorlist';

    getNoticeAuthor(getAuthorUrl);
    getNoticeList(getNoticeUrl);

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
    getNoticeList(getNoticeUrl+queryParm);
}



function  getNoticeList(getUrl) {


    $.getJSON(getUrl, function (data) {
        var html= '';
        if (data.state==200) {

            var List =data.data.records;
            List.map(function (item,index) {
                if (item.thumb) {
                    html += '' +
                        '<div class="mdui-col-sm-6 mdui-col-md-4">'+
                        '        <div class="mdui-card">\n' +
                        '            <div class="mdui-card-media">\n' +
                        '                <img src="' + item.thumb.thumbSrc + '"/>\n' +
                        '                <div class="mdui-card-menu">\n' +
                        '                    <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-primary">\n' +
                        '                <div class="mdui-card-primary-title">' + item.noticeTitle + '</div>\n' +
                        '                <div class="mdui-card-primary-subtitle">发布人:' + item.user.username + '</div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-actions">\n' +
                        '                <a  href="/frontend/notice?noticeId='+item.noticeId +'"  class="mdui-btn mdui-col-xs-1">打开</a>' +
                        '            </div>\n' +
                        '            </div>\n' +
                        '        </div>\n';
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
                    var changePostUrl="/notice/getlist?pageIndex="+page+"&pageSize=6&priority=-1";
                    changeList(changePostUrl);
                }
            });
            }
        }
        $('#notice_card').html(html);

    });
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