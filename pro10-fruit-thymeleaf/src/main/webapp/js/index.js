function delFruit(id) {
    if(confirm('sure to delete?')) {
        window.location.href="del.do?id=" + id;
    }
}