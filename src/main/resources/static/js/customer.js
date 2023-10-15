$(document).ready(function() {
    $('.add-to-cart').click(function() {
        var idtable = parseInt($(this).data('idtable'));
        var idproduct = parseInt($(this).data('idproduct'));
        if (isNaN(idtable) || isNaN(idproduct)) {
            alert('Giá trị idtable hoặc idproduct không hợp lệ.');
            console.log(idtable);
            console.log(idproduct);
            return; // Dừng việc gửi yêu cầu AJAX
        }

        var data = {
            idtable: idtable,
            idproduct: idproduct
        };


        $.ajax({
            type: "Post",
            url: "/customer/addcart",
            data: data,
            success: function(product) {
                var productHtml = '<li class="list-group-item d-flex">';
                productHtml += '<div style="flex-grow: 1;">' + product.name + '</div>';
                productHtml += '<div style="flex-grow: 1; width: 25px;">';
                productHtml += '<input type="number" name="quantity" value="1" min="1" style="width:50px;" onblur="updateCart(this, ' + product.id + ', ' + idtable + ')">';
                productHtml += '</div>';
                productHtml += '<div style="flex-grow: 1;">' + product.price + '</div>';
                productHtml += '<div style="flex-grow: 1;">';
                productHtml += '<button type="button" class="btn btn-link p-0" onclick="removeCartItem(' + product.id + ', ' + idtable + ')">';
                productHtml += '<i class="bi bi-x-lg"></i>';
                productHtml += '</button>';
                productHtml += '</div>';
                productHtml += '</li>';

                // Thêm sản phẩm mới vào danh sách
                $('#list-product-in-cartitem').append(productHtml);

            }
        });

    });

    function updateCart(inputElement, productId, idtable) {
        // Xử lý cập nhật số lượng sản phẩm trong giỏ hàng và gửi dữ liệu lên máy chủ
        var newQuantity = $(inputElement).val();
        // Thực hiện AJAX để cập nhật giỏ hàng trên máy chủ
        // ...

        // Cập nhật tổng tiền và các thông tin khác ở đây (nếu cần)
    }

    function removeCartItem(productId, idtable) {
        // Xử lý việc xóa sản phẩm khỏi giỏ hàng và gửi dữ liệu lên máy chủ
        // ...

        // Xóa sản phẩm khỏi danh sách hiển thị trên trang
        $('#list-product-in-cartitem li').each(function() {
            var item = $(this);
            var itemProductId = item.data('product-id');
            if (itemProductId === productId) {
                item.remove();
            }
        });



    };



});