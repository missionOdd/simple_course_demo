$(function () {
    var getPostUrl="/post/getlist?pageIndex=1&pageSize=6&priority=-1";
    var getNoticeUrl="/notice/getlist?pageIndex=1&pageSize=6&priority=-1";
    var getThumbCategoryUrl="/thumbCategory/getlist";
    getPostList(getPostUrl);
    getThumbCategory(getThumbCategoryUrl);
    getNoticeList(getNoticeUrl);

});




function  getPostList(getUrl) {

    var html= "";
    $.getJSON(getUrl, function (data) {

        if (data.state==200) {
            var List =data.data.records;
            List.map(function (item,index) {
                if (item.thumb){
                    html += ''+
                        '<div class="mdui-col-sm-6">'+
                        '        <div class="mdui-card">\n' +
                        '            <div class="mdui-card-media">\n' +
                        '                <img src="'+item.thumb.thumbSrc+'"/>\n' +
                        '                <div class="mdui-card-menu">\n' +
                        '                    <button class="mdui-btn mdui-btn-icon mdui-text-color-white"><i class="mdui-icon material-icons">share</i></button>\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-primary">\n' +
                        '                <div class="mdui-card-primary-title">'+item.postTitle+'</div>\n' +
                        '                <div class="mdui-card-primary-subtitle">分类'+item.postCategory.postCategoryName+'</div>\n' +
                        '            </div>\n' +
                        '            <div class="mdui-card-content">'+item.postSummary+'</div>\n' +
                        '            <div class="mdui-card-actions">\n' +
                        '                <a  href="/frontend/post?postId='+item.postId+'"  data-id="'+item.postId+'" class="mdui-btn mdui-col-xs-1">打开</a>' +
                        '            </div>\n' +
                        '            </div>\n' +
                        '        </div>\n';
                }

            });
            $('#post_card').html(html);


        }
    })
}




function  getNoticeList(getUrl) {


    var html= "";
    $.getJSON(getUrl, function (data) {

        if (data.state==200) {
            var List =data.data.records;
            var FirstIndex=(data.data.current-1)*(data.data.size)+1;
            List.map(function (item,index) {
                var Tipcontent= '公告标题:'+item.noticeTitle+'<br/>'+
                    '修改时间:'+item.lastEditTime+'<br/>'+
                    '创建时间:'+item.createTime+'<br/>'
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
                        '                <a  href="/frontend/notice?noticeId='+item.noticeId +'"  data-id="' + item.noticeId + '" class="mdui-btn mdui-col-xs-1">打开</a>' +
                        '            </div>\n' +
                        '            </div>\n' +
                        '        </div>\n';
                }
            });
            $('#notice_card').html(html);


        }
    });
}



function  getThumbCategory(getUrl) {
    var html= "";
    $.getJSON(getUrl, function (data) {
        if (data.state==200) {
            data.data.map(function (item,index) {

                html += ''+
                    '                <div class="mdui-col-sm-4">\n' +
                    '                    <div class="mdui-grid-tile">\n' +
                    '                        <a href="/frontend/photo?thumbCategoryId='+item.thumbCategoryId +'"><img src="'+item.thumbCategorySrc+'"/></a>\n' +
                    '                        <div class="mdui-grid-tile-actions">\n' +
                    '                            <div class="mdui-grid-tile-text">\n' +
                    '                                <div class="mdui-grid-tile-title">'+item.thumbCategoryName+'</div>\n' +
                    '                                <div class="mdui-grid-tile-subtitle"><i class="mdui-icon material-icons">grid_on</i>'+item.thumbCategoryDesc+'</div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>\n'


            });
            $('#thumb_card').html(html);

        }
    });
}
