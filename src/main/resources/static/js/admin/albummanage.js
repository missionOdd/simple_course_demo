$(function () {

    var getUrl="/thumbCategory/getlist";


    getlist();
    function  getlist() {
        var html= "";
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
                data.data.map(function (item,index) {
                    var Tipcontent= '相册名称 :'+item.thumbCategoryName+'<br/>'+
                        '相册描述:'+item.thumbCategoryDesc+'<br/>';

                    html += ''+'<tr>' +
                        '<td>'+(index+1)+'</td>'+
                        '<td>'+item.thumbCategoryName+'</td>'+
                        '<td>'+item.thumbCategorySrc+'</td>'+
                        '<td>' +
                        '<a href="'+item.thumbCategorySrc+'" id="tip'+item.thumbCategoryId+'" class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                        '<a href="/admin/addalbum?thumbCategoryId='+item.thumbCategoryId+'" id="modify'+item.thumbCategoryId+'" class="mdui-btn">修改</a>'+
                        '<a  onclick="openDialog('+item.thumbCategoryId+')" id="delete'+item.thumbCategoryId+'" class="mdui-btn">删除</a>'+
                        '</td>'+
                        '</tr>';

                });
                $('#album_table').html(html);

            }
        });
    }


});

function openDialog(thumbCategoryId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/thumbCategory/delete?thumbCategoryId='+thumbCategoryId,
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
