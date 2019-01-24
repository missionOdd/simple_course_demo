$(function () {

    var getUrl="/fileCategory/getlist";


    getList();
    function  getList() {
        var html= "";
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
                data.data.map(function (item,index) {
                    var Tipcontent= '文件类别名称:'+item.fileCategoryName+'<br/>'+
                        '文件类别描述:'+item.fileCategoryDesc+'<br/>'

                    html += ''+'<tr>' +
                        '<td>'+(index+1)+'</td>'+
                        '<td>'+item.fileCategoryName+'</td>'+
                        '<td>' +
                        '<a  id="tip'+item.fileCategoryId+'" class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                        '<a href="/admin/addfilecate?fileCategoryId='+item.fileCategoryId+'" id="modify'+item.fileCategoryId+'" class="mdui-btn">修改</a>'+
                        '<a  onclick="openDialog('+item.fileCategoryId+')" id="delete'+item.fileCategoryId+'" class="mdui-btn">删除</a>'+
                        '</td>'+
                        '</tr>';

                });
                $('#file_cate_table').html(html);

            }
        });
    }

});

function openDialog(fileCategoryId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/fileCategory/delete?fileCategoryId='+fileCategoryId,
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
