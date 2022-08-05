function delFruit(id) {
    if(confirm('sure to delete?')) {
        window.location.href="del.do?id=" + id;
    }
}
function page(pageNo) {
    // if(pageNo < 1) {
    //     pageNo = 1;
    // }
    window.location.href="index?pageNo=" + pageNo;

}