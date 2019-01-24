var fileCategoryId;
var fileTypeId;
var getUrl;
var fileTypeUrl = "/fileType/getlist";
var fileCategoryUrl = "/fileCategory/getlist";
$(function () {

    fileTypeId=getQueryString('fileTypeId')

    fileCategoryId=getQueryString('fileCategoryId')

    getList();

    getfileType();

    getfileCategory();

    $("#input_file_category").on('change',function(){
        changeUrl()
        $('#pagination-demo').twbsPagination('destroy');
    });

    $("#input_file_type").on('change',function(){
        changeUrl()
        $('#pagination-demo').twbsPagination('destroy');
    });


});

function changeUrl() {
    fileCategoryId=$("#input_file_category").find("option:selected").val();
    fileTypeId=$("#input_file_type").find("option:selected").val();
    getList();
}


function openDialog(fileId) {
    mdui.confirm('确定删除吗', function(){
        $.ajax({
            type:"DELETE",
            url:'/file/delete?fileId='+fileId,
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


function  getList(page) {

    if (page==null){
        getUrl="/file/getlist?pageIndex=1&pageSize=4"
    } else {
        getUrl="/file/getlist?pageIndex="+page+"&pageSize=4";
    }

    if (fileCategoryId&&fileCategoryId>0) {
        getUrl +='&fileCategoryId='+fileCategoryId;
    }

    if (fileTypeId&&fileTypeId>0) {
        getUrl +='&fileTypeId='+fileTypeId;
    }

    var html= "";
    var count;
    $.getJSON(getUrl, function (data) {

        if (data.state==200) {
            var fileList =data.data.records;
            var FirstIndex=(data.data.current-1)*(data.data.size)+1;
            fileList.map(function (item,index) {
                var Tipcontent= '文件名称:'+item.fileName+'<br/>'+
                    '修改时间:'+item.lastEditTime+'<br/>'+
                    '创建时间:'+item.createTime+'<br/>'+
                    '文件描述:'+ item.fileDesc+'<br/>'+
                    '文件类别:'+ item.fileCategory.fileCategoryName+'<br/>'+
                    '文件格式:'+item.fileType.fileTypeName;

                if (item.thumb){

                html += ''+'<tr>' +
                    '<td>'+(index+FirstIndex)+'</td>'+
                    '<td><img src="'+item.thumb.thumbSrc+'"></td>'+
                    '<td>'+item.fileTitle+'</td>'+
                    '<td>'+item.fileCategory.fileCategoryName+'</td>'+
                    '<td>'+item.fileType.fileTypeName+'</td>'+
                    '<td>' +
                    '<a  id="tip'+item.fileId+'" href="'+item.fileSrc+'" download class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                    '<a  href="/admin/addfile?fileId='+item.fileId+'" id="modify'+item.fileId+'" class="mdui-btn">修改</a>'+
                    '<a  onclick="openDialog('+item.fileId+')"  data-id="'+item.fileId+'" class="mdui-btn">删除</a>'+
                    '<a  href="'+item.fileSrc+'" class="mdui-btn copy-btn">链接</a>'+
                    '</td>'+
                    '</tr>';
                }else {
                    html += ''+'<tr>' +
                        '<td>'+(index+FirstIndex)+'</td>'+
                        '<td>无缩略图</td>'+
                        '<td>'+item.fileTitle+'</td>'+
                        '<td>'+item.fileCategory.fileCategoryName+'</td>'+
                        '<td>'+item.fileType.fileTypeName+'</td>'+
                        '<td>' +
                        '<a  id="tip'+item.fileId+'" href="'+item.fileSrc+'" download class="mdui-btn" mdui-tooltip="{content: \''+Tipcontent+'\'}">详情</a>' +
                        '<a  href="/admin/addfile?fileId='+item.fileId+'" id="modify'+item.fileId+'" class="mdui-btn">修改</a>'+
                        '<a  onclick="openDialog('+item.fileId+')"  data-id="'+item.fileId+'" class="mdui-btn">删除</a>'+
                        '<a  href="'+item.fileSrc+'" class="mdui-btn copy-btn">链接</a>'+
                        '</td>'+
                        '</tr>';
                }


            });


            count=data.data.pages;
            if (count) {
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
        $('#file_table').html(html);
    })
}


function getfileType() {
    $.getJSON(fileTypeUrl, function (data) {
        if (data.state == 200) {
            var html = '<option value="0">所有种类</option>';

            data.data.map(function (item, index) {
                html += '<option value="' + item.fileTypeId + '">'+item.fileTypeName+'</option>';
            });
            $('#input_file_type').html(html);
            if (fileTypeId) {
                $('#input_file_type').val(fileTypeId)
            }
                new mdui.Select('#input_file_type');

        }
    });
}

function getfileCategory() {
    $.getJSON(fileCategoryUrl, function (data) {
        if (data.state == 200) {
            var html1 = '<option value="0">所有类别</option>';

            data.data.map(function (item, index) {
                html1 += '<option value="' + item.fileCategoryId + '">'+item.fileCategoryName+'</option>';
            });
            $('#input_file_category').html(html1);
            if (fileCategoryId){
                $('#input_file_category').val(fileCategoryId);
            }
                new mdui.Select('#input_file_category');

        }
    });
}
