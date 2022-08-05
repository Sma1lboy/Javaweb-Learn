function delFruit(id) {
    if(confirm('sure to delete?')) {
        window.location.href="fruit.do?id=" + id +"&operate=delete";
    }
}
function page(pageNo) {
    // if(pageNo < 1) {
    //     pageNo = 1;
    // }
    window.location.href="fruit.do?pageNo=" + pageNo;

}