$(function () {

    var getUrl="/postCategory/getlist";


    getlist();
    function  getlist() {
        var html= "";
        var toopTip=[];
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
                data.data.map(function (item,index) {
                    var Tipcontent= '文章分类名称 :'+item.postCategoryName+'<br/>'+
                        '文章分类描述:'+item.postCategoryDesc+'<br/>'

                    html += ''+'<tr>' +
                        '<td>'+(index+1)+'</td>'+
                        '<td>'+item.postCategoryName+'</td>'+
                        '<td>' +
                        '<a  id="tip'+item.postCategoryId+'" class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                        '<a href="/admin/addpostcate?postCategoryId='+item.postCategoryId+'" id="modify'+item.postCategoryId+'" class="mdui-btn">修改</a>'+
                        '<a  onclick="openDialog('+item.postCategoryId+')" id="delete'+item.postCategoryId+'" class="mdui-btn">删除</a>'+
                        '</td>'+
                        '</tr>';

                });
                $('#post_cate_table').html(html);

            }
        });
    }


});

function openDialog(postCategoryId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/postCategory/delete?postCategoryId='+postCategoryId,
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
