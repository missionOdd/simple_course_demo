$(function () {

    var postCategoryId=getQueryString('postCategoryId');


    var thumbCategoryUrl = '/postCategory/addorupdate';

    var getOneUrl ='/postCategory/getone?postCategoryId='+postCategoryId;
    var isEdit =false;

    if (postCategoryId){
        isEdit=true;
        thumbCategoryUrl='/postCategory/addorupdate?postCategoryId='+postCategoryId;
    }
    if (isEdit){
        getOne();
    }

    function getOne(){
        $.getJSON(getOneUrl, function (data) {
            if (data.state == 200) {
                $('#input_post_cate_title').val(data.data.postCategoryName);
                $('#input_post_cate_text').val(data.data.postCategoryDesc);

            }
        });
    }





    $('#submit').click(function () {
        var postCategoryName =$("#input_post_cate_title").val();
        var postCategoryDesc=$("#input_post_cate_text").val();
        var formData =new FormData();;
        formData.append('postCategoryName',postCategoryName);
        formData.append('postCategoryDesc',postCategoryDesc);

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
