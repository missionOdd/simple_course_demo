var thumbCategoryId;
$(function () {

    thumbCategoryId =getQueryString('thumbCategoryId');
    var getUrl;

    getCategory();

    $("#input_photo_cate").on('change',function(){
       thumbCategoryId=$("#input_photo_cate").find("option:selected").val();
       if (thumbCategoryId>0) {
           getPhotolist();
       }else {
           window.location.href='/frontend/photo';
       }
    });


    getPhotolist();
    function  getPhotolist(page) {
        if (page==null){
            getUrl="/thumb/getlist?pageIndex=1&pageSize=12&parentId=0";
        } else {
            getUrl="/thumb/getlist?pageIndex="+page+"&pageSize=12&parentId=0";
        }

        if (thumbCategoryId) {
            getUrl+='&thumbCategoryId='+thumbCategoryId;
        }


        var html= "";
        var count;
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
                var List =data.data.records;
                List.map(function (item,index) {
                    if (item.thumbCategory&&item.parent){
                    html +=''+
                        '<figure class="mdui-col">' +
                        '   <div class="img-dv"><a href="'+item.parent.thumbSrc+'" data-size="1920x1080"><img src="'+item.thumbSrc+'"></a></div>\n' +
                        '    <figcaption style="display:none;">图名:'+item.thumbName+'         相册:'+item.thumbCategory.thumbCategoryName+'            创建日期:'+item.createTime+'</figcaption>\n' +
                        '</figure>';

                    }


                });
                $('.my-gallery').html(html);

                count=data.data.pages;
                if (count>0){
                $('#pagination-demo').twbsPagination({
                    totalPages:count,
                    visiblePages: 4,
                    next: 'Next',
                    prev: 'Prev',
                    onPageClick: function (event, page) {
                        $('#page-content').text('Page ' + page) + ' content here';
                        getPhotolist(page);
                    }
                });
                }


            }
        });
    }


});


function getCategory() {
    var getThumbCategoryUrl="/thumbCategory/getlist";
    $.getJSON(getThumbCategoryUrl, function (data) {
        if (data.state == 200) {
            var html = '<option value="0">所有类别</option>';

            data.data.map(function (item, index) {
                html += '<option value="' + item.thumbCategoryId + '">'+item.thumbCategoryName+'</option>';
            });
            $('#input_photo_cate').html(html);
            if (thumbCategoryId) {
                $('#input_photo_cate').val(thumbCategoryId);
            }
                new mdui.Select('#input_photo_cate');

        }
    });
}