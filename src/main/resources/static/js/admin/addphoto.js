$(function () {

    var thumbId=getQueryString('thumbId');

    var getCategoryUrl = "/thumbCategory/getlist";


    var addUrl = '/thumb/add';

    var getOneUrl ='/thumb/getone?thumbId='+thumbId;
    var isEdit =false;

    if (thumbId){
        isEdit=true;
        addUrl='/fileCategory/modify?thumbId='+thumbId;

    }
    getCategory();


    function getOne(){
        $.getJSON(getOneUrl, function (data) {
            if (data.state == 200) {
                $('#input_photo_title').val(data.data.thumbName);
                $('#input_photo_text').text(data.data.thumbDesc);
                $('#input_photo_cate').val(data.data.thumbCategoryId);
                new mdui.Select('#input_photo_cate');
            }
        });
    }



    function getCategory() {
        $.getJSON(getCategoryUrl, function (data) {
            if (data.state == 200) {
                var html = '';

                data.data.map(function (item, index) {
                    html += '<option value="' + item.thumbCategoryId + '">'+item.thumbCategoryName+'</option>';
                });
                $('#input_photo_cate').html(html);
                if (!isEdit) {
                    new mdui.Select('#input_photo_cate');
                }else {
                    getOne();
                }
            }
        });
    }


    $('#submit').click(function () {
        var thumbCategoryId=$("#input_photo_cate").find("option:selected").val();
        var thumbName =$("#input_photo_title").val();
        var thumbDesc=$("#input_photo_text").val();
        var file=$('#file')[0].files[0];
        var formData =new FormData();;
        formData.append('thumbCategoryId',thumbCategoryId);
        formData.append('thumbName',thumbName);
        formData.append('thumbDesc',thumbDesc);
        formData.append('file',file);

        $.ajax({
            url: addUrl,
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

