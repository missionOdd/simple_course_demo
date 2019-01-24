$(function () {

    var fileCategoryId = getQueryString('fileCategoryId');


    var FileCategoryUrl = "/fileCategory/addorupdate?";

    var getOneUrl ='/fileCategory/getone?fileCategoryId='+fileCategoryId;
    var isEdit =false;

    if (fileCategoryId){
        isEdit=true;
        FileCategoryUrl='/fileCategory/addorupdate?fileCategoryId='+fileCategoryId+'&';
        getOne();
    }

    function getOne(){
        $.getJSON(getOneUrl, function (data) {
            if (data.state == 200) {
                $('#input_file_cate_name').val(data.data.fileCategoryName);
                $('#input_file_cate_desc').val(data.data.fileCategoryDesc);
            }
        });
    }

    $('#submit').click(function () {
        var ajaxUrl =FileCategoryUrl+'fileCategoryName='+$("#input_file_cate_name").val()+'&'+'fileCategoryDesc='+$("#input_file_cate_desc").val();


        $.ajax({
            url: ajaxUrl,
            type: 'POST',
            cache:false,
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


})