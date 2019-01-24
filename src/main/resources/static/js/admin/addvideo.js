$(function () {
    var videoId = getQueryString('videoId');



    var getUrl='/video/getone?videoId='+videoId;
    var videoUrl = '/video/add';
    var isEdit=false;
    var flag=false;

    if (videoId){
        isEdit=true;
        videoUrl='/video/modify?videoId='+videoId;
    }
    getAuthor();


    function getvideo() {
        $.getJSON(getUrl, function (data) {
            if (data.state == 200) {
               $('#input_video_name').val(data.data.videoName);
                $('#input_video_desc').val(data.data.videoDesc);
                $("#input_video_author").val(data.data.userId);
                new mdui.Select('#input_video_author');
            }
        });
    }



    function getAuthor() {
        var getAuthorUrl='/user/getauthorlist';
        $.getJSON(getAuthorUrl, function (data) {
            if (data.state == 200) {
                var html = '';

                data.data.map(function (item, index) {
                    html += '<option value="' + item.userId + '">'+item.username+'</option>';
                });
                $('#input_video_author').html(html);
                if (!isEdit) {
                    new mdui.Select('#input_video_author');
                }else {
                    getvideo();
                }


            }
        });
    }



    $('#submit').click(function () {
        var userId=$("#input_video_author").find("option:selected").val();
        var videoName =$("#input_video_name").val();
        var videoDesc=$("#input_video_desc").val();
        var files=[];
           files[0]=$('#file')[0].files[0];
        files[1]=$('#file_img')[0].files[0];
        var formData =new FormData();
        formData.append('videoName',videoName);
        formData.append('userId',userId);
        formData.append('videoDesc',videoDesc);
        formData.append('files',files[0]);
        formData.append('files',files[1]);

        $.ajax({
            url: videoUrl,
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
                    setTimeout(function () {
                        window.location.reload();
                    },2000)

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

})

