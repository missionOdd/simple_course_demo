var thumbCategoryId;
$(function () {

    thumbCategoryId =getQueryString('thumbCategoryId');

    var getUrl;
    getCategory();

    $("#input_photo_cate").on('change',function(){
        thumbCategoryId=$("#input_photo_cate").find("option:selected").val();
        if (thumbCategoryId>0) {
            getPhotolist();
            $('#pagination-demo').twbsPagination('destroy');
        }else {
            window.location.href='/admin/photomanage';
        }
    });



    getPhotolist();
    function  getPhotolist(page) {
        if (page==null){
            getUrl="/thumb/getlist?pageIndex=1&pageSize=5";
        } else {
            getUrl="/thumb/getlist?pageIndex="+page+"&pageSize=5";
        }

        if (thumbCategoryId) {
            getUrl+='&thumbCategoryId='+thumbCategoryId;
        }


        var html= "";
        var count;
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
                var List =data.data.records;
                var FirstIndex=(data.data.current-1)*(data.data.size)+1;
                List.map(function (item,index) {
                    if (item.thumbCategory){
                    var Tipcontent= '图片标题:'+item.thumbName+'<br/>'+
                        '相册:'+item.thumbCategory.thumbCategoryName+'<br/>'+
                        '相册描述:'+item.thumbCategoryDesc+'<br/>'+
                        '创建时间:'+ item.createTime;


                    html += ''+'<tr>' +
                        '<td>'+(index+FirstIndex)+'</td>'+
                        '<td><img src="'+item.thumbSrc+'"/></td>'+
                        '<td>'+item.thumbName+'</td>'+
                        '<td>'+getNameBySrc(item.thumbSrc)+'</td>'+
                        '<td>'+item.thumbCategory.thumbCategoryName+'</td>'+
                        '<td>' +
                        '<a  id="tip'+item.thumbId+'" href="'+item.thumbSrc+'" class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                        '<a href="/admin/addphoto?thumbId='+item.thumbId+'" id="modify'+item.thumbId+'" class="mdui-btn">修改</a>'+
                        '<a  onclick="openDialog('+item.thumbId+')" id="delete'+item.thumbId+'" class="mdui-btn">删除</a>'+
                        '</td>'+
                        '</tr>';
                    }else {
                        var Tipcontent= '图片标题:'+item.thumbName+'<br/>'+
                            '相册:空<br/>'+
                            '相册描述: 空<br/>'+
                            '创建时间:'+ item.createTime;


                        html += ''+'<tr>' +
                            '<td>'+(index+FirstIndex)+'</td>'+
                            '<td><img src="'+item.thumbSrc+'"/></td>'+
                            '<td>'+item.thumbName+'</td>'+
                            '<td>'+getNameBySrc(item.thumbSrc)+'</td>'+
                            '<td>空</td>'+
                            '<td>' +
                            '<a  id="tip'+item.thumbId+'" href="'+item.thumbSrc+'" class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                            '<a href="/admin/addphoto?thumbId='+item.thumbId+'" id="modify'+item.thumbId+'" class="mdui-btn">修改</a>'+
                            '<a  onclick="openDialog('+item.thumbId+')" id="delete'+item.thumbId+'" class="mdui-btn">删除</a>'+
                            '</td>'+
                            '</tr>';
                    }


                });
                $('#photo_table').html(html);


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

function getNameBySrc(name) {
    return filename=name.split('\\')[name.split('\\').length-1].split('(')[0]
}



function openDialog(thumbId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/thumb/delete?thumbId='+thumbId,
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