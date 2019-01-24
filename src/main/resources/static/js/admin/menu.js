function  getHead() {
    $.getJSON("/site/get", function (data) {
        if (data.state==200) {
            var headlineSrc=data.data.headlineImg;
            $('#headline_img').html('<img  class="mdui-img-fluid" src="'+headlineSrc+'" style="background: no-repeat fixed center/cover"/>');
            document.title =data.data.siteTitle;
            $('#site_title').html(data.data.siteTitle);
            $('#site_headline').html(data.data.headline);
        }
    });
}
$(function () {

    getHead();

    $('#menu_home').click(function () {
        $(window).attr('location','/admin/index');
    })
    $('#menu_sitemanage').click(function () {
        $(window).attr('location','/admin/sitemanage');
    })

    $('#menu_filemanage').click(function () {
        $(window).attr('location','/admin/filemanage');
    })
    $('#menu_addfile').click(function () {
        $(window).attr('location','/admin/addfile');
    })

    $('#menu_filecatelist').click(function () {
        $(window).attr('location','/admin/filecatelist');
    })
    $('#menu_addfilecate').click(function () {
        $(window).attr('location','/admin/addfilecate');
    })

    $('#menu_photomanage').click(function () {
        $(window).attr('location','/admin/photomanage');
    })
    $('#menu_addphoto').click(function () {
        $(window).attr('location','/admin/addphoto');
    })

    $('#menu_albummanage').click(function () {
        $(window).attr('location','/admin/albummanage');
    })
    $('#menu_addalbum').click(function () {
        $(window).attr('location','/admin/addalbum');
    })

    $('#menu_postmanage').click(function () {
        $(window).attr('location','/admin/postmanage');
    })
    $('#menu_addpost').click(function () {
        $(window).attr('location','/admin/addpost');
    })

    $('#menu_authormanage').click(function () {
        $(window).attr('location','/admin/authormanage');
    })
    $('#menu_addauthor').click(function () {
        $(window).attr('location','/admin/addauthor');
    })
    $('#menu_postcatelist').click(function () {
        $(window).attr('location','/admin/postcatelist');
    })
    $('#menu_addpostcate').click(function () {
        $(window).attr('location','/admin/addpostcate');
    })
    $('#menu_noticemanage').click(function () {
        $(window).attr('location','/admin/noticemanage');
    })
    $('#menu_addnotice').click(function () {
        $(window).attr('location','/admin/addnotice');
    })

    $('#menu_videomanage').click(function () {
        $(window).attr('location','/admin/videomanage');
    })

    $('#menu_addvideo').click(function () {
        $(window).attr('location','/admin/addvideo');
    })


});