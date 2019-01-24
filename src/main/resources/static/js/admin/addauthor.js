$(function () {

    var userId=getQueryString('userId');



    var addUrl = '/user/createuser';

    var getOneUrl ='/user/getone?userId='+userId;
    var isEdit =false;

    if (userId){
        isEdit=true;
        addUrl='/user/modify?userId='+userId;

    }

    if (isEdit){
        getOne();
    }

    function getOne(){
        $.getJSON(getOneUrl, function (data) {
            if (data.state == 200) {
                $('#input_username').val(data.data.username);
                $('#input_email').val(data.data.email);
            }
        });
    }






    $('#submit').click(function () {
        var password=$("#input_password").val();
        var username =$("#input_username").val();
        var email=$("#input_email").val();
        var file=$('#file')[0].files[0];
        var verifyCodeActual=$('#j_captcha').val();
        var formData =new FormData();
        formData.append('password',password);
        formData.append('username',username);
        formData.append('email',email);
        formData.append('file',file);
        formData.append('role',"author");
        formData.append('verifyCodeActual',verifyCodeActual);

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
                    $('#captcha_img').click();

                } else {
                    mdui.snackbar({
                        message: '提交失败',
                        position: 'top'
                    });
                }
            }
        })

    });
    $('#reset').click(function () {
        window.location.reload();
    })
});

