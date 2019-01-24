$(function () {

    var siteModifyUrl="/site/modify";

    var siteGetUrl="/site/get";

    var headlineSrc ='';

    getSite();
    function  getSite() {
        $.getJSON(siteGetUrl, function (data) {
            if (data.state==200) {
                $('#input_site_title').val(data.data.siteTitle);
                $('#input_headline').val(data.data.headline);
                headlineSrc=data.data.headlineImg;
                $('#head_img').html('<br/><img src="'+data.data.headlineImg+'" width="300">');

            }
        });
    }
    var $$ = mdui.JQ;


    $('#submit').click(function () {

        var site =new FormData();
        site.append("siteTitle", $('#input_site_title').val());
        site.append("headline",$('#input_headline').val());
        site.append("file",$('#file')[0].files[0]);

        $.ajax({
            url: siteModifyUrl,
            type: 'PUT',
            cache:false,
            data: site,
            processData: false,
            contentType: false,
            dataType:"json",
            success: function (data) {
                if (data.state==200) {
                        mdui.snackbar({
                            message: '提交成功',
                            position: 'top'
                        });
                    getSite();
                    getHead();

                } else {
                    mdui.snackbar({
                        message: '提交失败',
                        position: 'top'
                    });
                }
            }
        });
    });

    $('#reset').click(function () {
        getSite();
    })

});