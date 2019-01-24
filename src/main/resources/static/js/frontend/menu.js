$(function () {

    getHead();


});
function  getHead() {
    $.getJSON("/site/get", function (data) {
        if (data.state==200) {
            var headlineSrc=data.data.headlineImg;
            $('#headline_img').html('<img  class="mdui-img-fluid" src="'+headlineSrc+'" />');
            document.title =data.data.siteTitle;
            $('#site_title').html(data.data.siteTitle);
            $('#site_headline').html(data.data.headline);
        }
    });
}



