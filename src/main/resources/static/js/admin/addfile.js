$(function () {
    var fileId = getQueryString('fileId');
    var fileTypeUrl = "/fileType/getlist";

    var fileCategoryUrl = "/fileCategory/getlist";
    var getUrl='/file/getone?fileId='+fileId;
    var fileUrl = '/file/upload';
    var isEdit=false;

    if (fileId){
        isEdit=true;
        fileUrl='/file/modify?fileId='+fileId;
    }

    getfileType();
    getfileCategory();

    if(isEdit){
        getfile();
    }

    function getfile() {
        $.getJSON(getUrl, function (data) {
            if (data.state == 200) {
               $('#input_file_name').val(data.data.fileName);
                $('#input_file_title').val(data.data.fileTitle);
                $('#input_file_desc').val(data.data.fileDesc);
                $("#input_file_category").val(data.data.fileCategoryId);
                $("#input_file_type").val(data.data.fileTypeId);
                new mdui.Select('#input_file_category');
                new mdui.Select('#input_file_type');
            }
        });
    }


    function getfileType() {
        $.getJSON(fileTypeUrl, function (data) {
            if (data.state == 200) {
                var html = '';

                data.data.map(function (item, index) {
                    html += '<option value="' + item.fileTypeId + '">'+item.fileTypeName+'</option>';
                });
                $('#input_file_type').html(html);
                if (!isEdit) {
                    new mdui.Select('#input_file_type');
                }
            }
        });
    }

        function getfileCategory() {
            $.getJSON(fileCategoryUrl, function (data) {
                if (data.state == 200) {
                    var html1 = '';

                    data.data.map(function (item, index) {
                        html1 += '<option value="' + item.fileCategoryId + '">'+item.fileCategoryName+'</option>';
                    });
                    $('#input_file_category').html(html1);
                    if (!isEdit) {
                        new mdui.Select('#input_file_category');
                    }
                }
            });
        }

    $('#submit').click(function () {
        var fileCategoryId=$("#input_file_category").find("option:selected").val();
        var fileTypeId=$("#input_file_type").find("option:selected").val();
        var fileName =$("#input_file_name").val();
        var fileTitle=$("#input_file_title").val();
        var fileDesc=$("#input_file_desc").val();
        var files=[];
           files[0]=$('#file')[0].files[0];
        files[1]=$('#file_img')[0].files[0];
        var formData =new FormData();;
        formData.append('fileCategoryId',fileCategoryId);
        formData.append('fileTypeId',fileTypeId);
        formData.append('fileName',fileName);
        formData.append('fileTitle',fileTitle);
        formData.append('fileDesc',fileDesc);
        formData.append('files',files[0]);
        formData.append('files',files[1]);

        $.ajax({
            url: fileUrl,
            type: 'POST',
            cache:false,
            data: formData,
            processData: false,
            contentType: false,
            dataType:"json",
            success: function (data) {
                if (data.state==200) {
                    mdui.snackbar({
                        message: '提交成功',
                        position: 'top'
                    });

                } else {
                    mdui.snackbar({
                        message: '提交失败',
                        position: 'top'
                    });
                }
            }
    })

    })

    $('#reset').click(function () {
        window.location.reload();
    })

})