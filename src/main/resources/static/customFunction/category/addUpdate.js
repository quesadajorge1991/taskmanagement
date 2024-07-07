
function addUpdateCategory(categoryId, categoryName) {
    var Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });
    var categoryName = categoryName;
    var categoryId = categoryId;
    var category = {
        categoryId: categoryId,
        categoryName: categoryName
    }
    if (categoryName != "") { //validar que el pueblo a insertar no este vacio
        $.ajax({
            url: '/category/addCategory',
            dataType: 'json',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(category),
            success: function (data) {
                Toast.fire({
                    title: data.msgtitle,
                    text: data.msgbody,
                    icon: data.msgtype

                })
                $('#categoryName').val('');
            },
            error: function (data) {
                Toast.fire({
                    title: data.msgtitle,
                    text: data.msgbody,
                    icon: data.msgtype
                })
            }
        });
    } else {
        Toast.fire({
            title: title,
            text: body,
            icon: type
        })
    }
}
