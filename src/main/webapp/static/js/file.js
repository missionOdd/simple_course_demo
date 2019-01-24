$(function () {

    var getUrl="/file/getlist?pageIndex=1&pageSize=2";


    getFilelist();
    function  getFilelist() {
        var html= '';
        $.getJSON(getUrl, function (data) {
            if (data.state==200) {
               var fileList =data.data.records;
                fileList.map(
                    function (item,index) {
                        var uid =258+index;
                        html+='\n' +
                            '              <!-- Unnamed (Table Cell) -->\n' +
                            '              <div id="u'+uid+'" class="ax_default table_cell">\n' +
                            '                <img id="u'+uid+'_img" class="img " src="images/主页/u258.png"/>\n' +
                            '                <div id="u'+uid+'_text" class="text ">\n' +
                            '                  <p><span>'+item.fileTitle+'</span></p>\n' +
                            '                </div>\n' +
                            '              </div>\n' +
                            '\n' +
                            '              <!-- Unnamed (Table Cell) -->\n' +
                            '              <div id="u'+uid+1+'" class="ax_default table_cell">\n' +
                            '                <img id="u259_img" class="img " src="images/主页/u258.png"/>\n' +
                            '                <div id="u259_text" class="text ">\n' +
                            '                  <p><span>'+item.fileCategory.fileCategoryName+'</span></p>\n' +
                            '                </div>\n' +
                            '              </div>\n' +
                            '\n' +
                            '              <!-- Unnamed (Table Cell) -->\n' +
                            '              <div id="u260" class="ax_default table_cell">\n' +
                            '                <img id="u260_img" class="img " src="images/主页/u258.png"/>\n' +
                            '                <div id="u260_text" class="text ">\n' +
                            '                  <p><span>'+item.fileType.fileTypeName+'</span></p>\n' +
                            '                </div>\n' +
                            '              </div>\n' +
                            '\n' +
                            '              <!-- Unnamed (Table Cell) -->\n' +
                            '              <div id="u261" class="ax_default table_cell">\n' +
                            '                <img id="u261_img" class="img " src="images/主页/u261.png"/>\n' +
                            '              </div>'

                    });
                $('#u257').after(html);

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