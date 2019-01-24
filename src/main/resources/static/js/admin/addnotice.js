$(function () {
    var noticeId = getQueryString('noticeId');


    var getnoticeUrl='/notice/getone?noticeId='+noticeId;
    var noticeUrl = '/notice/add';
    var getAuthorUrl='/user/getauthorlist';
    var isEdit=false;

    if (noticeId){
        isEdit=true;
        noticeUrl='/notice/modify?noticeId='+noticeId;
    }

    getnoticeAuthor();

    if(isEdit){
        getnotice();
    }

    function getnotice() {
        $.getJSON(getnoticeUrl, function (data) {
            if (data.state == 200) {
                $('#input_notice_title').val(data.data.noticeTitle);
                $('#input_notice_priority').val(data.data.priority);
                $("#input_notice_author").val(data.data.userId);
                editor.txt.html(data.data.noticeContent);
                new mdui.Select('#input_notice_author');
                mdui.updateSliders();
            }
        });

    }


    function getnoticeAuthor() {
        $.getJSON(getAuthorUrl, function (data) {
            if (data.state == 200) {
                var html = '';

                data.data.map(function (item, index) {
                    html += '<option value="' + item.userId + '">'+item.username+'</option>';
                });
                $('#input_notice_author').html(html);
                if (!isEdit) {
                    new mdui.Select('#input_notice_author');
                }
            }
        });
    }



    $('#submit').click(function () {
        var authorId=$("#input_notice_author").find("option:selected").val();
        var priority =$("#input_notice_priority").val();
        var noticeTitle=$("#input_notice_title").val();
        var noticeContent=editor.txt.html();
        var file=$('#file')[0].files[0];

        var formData =new FormData();
        formData.append('authorId',authorId);
        formData.append('priority',priority);
        formData.append('noticeTitle',noticeTitle);
        formData.append('noticeContent',noticeContent);
        formData.append('file',file);

        $.ajax({
            url: noticeUrl,
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