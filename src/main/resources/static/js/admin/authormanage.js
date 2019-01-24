$(function () {

    var getUrl="/user/getauthorlist";


    getList();
    function  getList() {
        var html= "";
        var toopTip=[];
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
                data.data.map(function (item,index) {
                    var Tipcontent= '角色:'+item.role+'<br/>'+
                        '最后登录时间:'+item.lastLoginTime+'<br/>'+
                        '创建时间:'+item.createTime+'<br/>'

                    html += ''+'<tr>' +
                        '<td>'+(index+1)+'</td>'+
                        '<td>'+item.username+'</td>'+
                        '<td>'+item.email+'</td>'+
                        '<td>' +
                        '<a  id="tip'+item.userId+'" class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                        '<a href="/admin/addauthor?userId='+item.userId+'" id="modify'+item.userId+'" class="mdui-btn">修改</a>'+
                        '<a  onclick="openDialog('+item.userId+')" id="delete'+item.userId+'" class="mdui-btn">删除</a>'+
                        '</td>'+
                        '</tr>';

                });
                $('#user_table').html(html);

            }
        });
    }

});

function openDialog(userId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/user/delete?userId='+userId,
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
