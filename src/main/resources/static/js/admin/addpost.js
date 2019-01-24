$(function () {
    var postId = getQueryString('postId');

    var postCategoryUrl = "/postCategory/getlist";
    var getPostUrl='/post/getone?postId='+postId;
    var getPostDetailUrl='/postDetail/get?postId='+postId;
    var postUrl = '/post/add';
    var getAuthorUrl='/user/getauthorlist';
    var isEdit=false;

    if (postId){
        isEdit=true;
        postUrl='/post/modify?postId='+postId;
    }

    getpostAuthor();
    getpostCategory();

    if(isEdit){
        getpost();
    }

    function getpost() {
        $.getJSON(getPostUrl, function (data) {
            if (data.state == 200) {
                $('#input_post_title').val(data.data.postTitle);
                $("#input_post_category").val(data.data.postCategoryId);
                $('#input_post_priority').val(data.data.priority);
                mdui.updateSliders();
                new mdui.Select('#input_post_category');
            }
        });

        $.getJSON(getPostDetailUrl, function (data) {
            if (data.state == 200) {
                $("#input_post_author").val(data.data.userId);
                editor.txt.html(data.data.postContent);
                new mdui.Select('#input_post_author');
            }
        });
    }


    function getpostAuthor() {
        $.getJSON(getAuthorUrl, function (data) {
            if (data.state == 200) {
                var html = '';

                data.data.map(function (item, index) {
                    html += '<option value="' + item.userId + '">'+item.username+'</option>';
                });
                $('#input_post_author').html(html);
                if (!isEdit) {
                    new mdui.Select('#input_post_author');
                }
            }
        });
    }

    function getpostCategory() {
        $.getJSON(postCategoryUrl, function (data) {
            if (data.state == 200) {
                var html1 = '';

                data.data.map(function (item, index) {
                    html1 += '<option value="' + item.postCategoryId + '">'+item.postCategoryName+'</option>';
                });
                $('#input_post_category').html(html1);
                if (!isEdit) {
                    new mdui.Select('#input_post_category');
                }
            }
        });
    }

    $('#submit').click(function () {
        var postCategoryId=$("#input_post_category").find("option:selected").val();
        var authorId=$("#input_post_author").find("option:selected").val();
        var priority =$("#input_post_priority").val();
        var postTitle=$("#input_post_title").val();
        var postContent=editor.txt.html();
        var postSummary=editor.txt.text();
        var file=$('#file')[0].files[0];

        var formData =new FormData();
        formData.append('postCategoryId',postCategoryId);
        formData.append('authorId',authorId);
        formData.append('priority',priority);
        formData.append('postTitle',postTitle);
        formData.append('postContent',postContent);
        formData.append('postSummary',postSummary);
        formData.append('file',file);

        $.ajax({
            url: postUrl,
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