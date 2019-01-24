$(function () {

    var thumbCategoryId=getQueryString('thumbCategoryId');


    var thumbCategoryUrl = '/thumbCategory/add';

    var getOneUrl ='/thumbCategory/getone?thumbCategoryId='+thumbCategoryId;
    var isEdit =false;

    if (thumbCategoryId){
        isEdit=true;
        thumbCategoryUrl='/thumbCategory/modify?thumbCategoryId='+thumbCategoryId;
    }
    if (isEdit){
        getOne();
    }

    function getOne(){
        $.getJSON(getOneUrl, function (data) {
            if (data.state == 200) {
                $('#input_photo_cate_title').val(data.data.thumbCategoryName);
                $('#input_photo_cate_text').val(data.data.thumbCategoryDesc);

            }
        });
    }





    $('#submit').click(function () {
        var thumbCategoryName =$("#input_photo_cate_title").val();
        var thumbCategoryDesc=$("#input_photo_cate_text").val();
        var file=$('#file')[0].files[0];
        var formData =new FormData();;
        formData.append('thumbCategoryName',thumbCategoryName);
        formData.append('thumbCategoryDesc',thumbCategoryDesc);
        formData.append('file',file);

        $.ajax({
            url: thumbCategoryUrl,
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

