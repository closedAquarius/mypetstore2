$(function (){
    /*$('#MainImageContent').on('mousemove', function(event) {
        if (!$('#info').is(':hover')){
            let info=$('#info')
            if (info.css('display') === 'block') {
                info.css({
                    left: event.pageX + 10,
                    top: event.pageY + 10
                });
            }
        }

    });*/

    $('#info').on('click','.productAutoItem',function(){
        const productId = $(this).data('productid');
        console.log(productId);
        window.location.href="http://localhost:8080/mypetstore/productForm?productId="+productId;
        $('#productAutoComplete').hide();
    });
    $('#info').on('click','.itemAutoItem',function(){
        const itemId = $(this).data('itemid');
        console.log(itemId);
        window.location.href="http://localhost:8080/mypetstore/itemForm?itemId="+itemId;
        $('#productAutoComplete').hide();
    });

    let isOverInfo = false;
    //添加 mouseenter 和 mouseleave 事件处理程序到 info 元素
    $('#info').on('mouseenter', function() {
        $(this).css('display', 'block'); // 确保 info 显示
        isOverInfo = true;
    });
    $('#info').on('mouseleave', function(event) {
        let info=$('#info')
        if (info.css('display') === 'block') {
            info.css({
                left: event.pageX + 10,
                top: event.pageY + 10
            });
        }
        isOverInfo = false;
    });

    $('map area').on('mouseleave', function(event) {
        let info=$('#info');
        if (!isOverInfo){
            info.hide();
        }

    });

    $('map area').on('mouseenter',function (event){
        let info=$('#info')
        info.html('');
        let categoryId=this.id;
        console.log(categoryId);
        if(categoryId=='BirdsBig'||categoryId=='BirdsSmall'){
            categoryId='Birds';
        }
        $.ajax({
            type:    'GET',
            url:     'http://localhost:8080/mypetstore/getDetails?categoryId='+categoryId,
            success :function (data){
                if(data !== null){
                    info.css({
                        display: 'block',
                        left: event.pageX + 10,
                        top: event.pageY + 10
                    });
                    let infoHTML='';
                    for(let i=0;i<data.length;i++){
                        infoHTML += '<li class="Auto"><span class=\"productAutoItem\" data-productId="'
                            +data[i].product.productId
                            +'">'
                            +data[i].product.productId
                            +'</span>'
                            +' | '
                            +'<span class=\"itemAutoItem\" data-itemId="'
                            +data[i].itemId
                            +'">'
                            +data[i].product.name
                            +"  "
                            +data[i].itemId
                            +'</span></li>';
                    }
                    info.html(infoHTML);
                    info.show();
                }
            }

        });
    });




});