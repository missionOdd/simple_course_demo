var obj =$('#pagination-demo').twbsPagination({
    totalPages: getList(),
    visiblePages: 4,
    next: 'Next',
    prev: 'Prev',
    onPageClick: function (event, page) {
        $('#page-content').text('Page ' + page) + ' content here';
        getList(page);
    }
});
$('#pagination-demo').twbsPagination('destroy');