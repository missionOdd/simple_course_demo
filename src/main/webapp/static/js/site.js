$(function () {

    var siteModifyUrl="/site/modify";

    var siteGetUrl="/site/get";

    getSite();
    function  getSite() {
        $.getJSON(siteGetUrl, function (data) {
            if (data.state==200) {
                $('#u245_input').val(data.data.siteTitle);
                $('#u246_input').val(data.data.headline);

            }
        });
    }
    $('#u250').click(function () {

        var site =new FormData();
        site.append("siteTitle", $('#u245_input').val());
        site.append("headline",$('#u246_input').val());
        site.append("file",$('#u249_input')[0].files[0]);

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
                    alert('提交成功');
                    getSite();

                } else {
                    alert('提交失败');
                }
            }
        });
    });

    $('#u251').click(function () {
        getSite();
    })

});