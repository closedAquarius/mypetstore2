$(function (){
    var isOverInfo = false;
    $('#userAddresses').on('click','.Button',function(){
        let addressId = $(this).data('addrid');
        let id = $(this).attr('id');
        let $div = $(this).closest('div');
        console.log($div);
        if(id==='choose'){
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/mypetstore/changeAddress?addressId='+addressId+'&isDelete='+id,
                success: function(data){

                        $('#firstname').val(data.firstName);
                        $('#lastname').val(data.lastName);
                        $('#address1').val(data.address1);
                        $('#address2').val(data.address2);
                        $('#city').val(data.city);
                        $('#state').val(data.state);
                        $('#zip').val(data.zip);
                        $('#country').val(data.country);
                        console.log(data)
                }
            });
        }else if(id==='delete') {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/mypetstore/changeAddress?addressId=' + addressId + '&isDelete=' + id,
                success: function (data) {
                    if (data == 'delete success') {
                        alert('delete success');
                        $div.remove();
                    }
                }
            });
        }else if(id==='setMain'){
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/mypetstore/changeAddress?addressId=' + addressId + '&isDelete=' + id,
                success: function (data) {
                    console.log(data);
                    if (data == 'set main success') {
                        alert('set main success');
                    }
                }
            });
        }
    });

    /*$('#userAddresses').on('mousemove','#address',function(event){
        let AutoAddress=$('#AutoAddress');
        if (AutoAddress.css('display') === 'block') {
            AutoAddress.css({
                left: event.pageX + 10,
                top: event.pageY + 10
            });
        }
    });*/

    $('#AutoAddress').on('mouseenter', function() {
        $(this).css('display', 'block'); // 确保 info 显示
        isOverInfo = true;
    });
    $('#AutoAddress').on('mouseleave', function(event) {
        let info=$('#info')
        if (info.css('display') === 'block') {
            info.css({
                left: event.pageX + 10,
                top: event.pageY + 10
            });
        }
        isOverInfo = false;
    });

    $('#userAddresses').on('mouseenter','#address',function (event) {
        let addressId = $(this).data('addrid');
        let id = 'getDetails';
        let AutoAddress=$('#AutoAddress');
        AutoAddress.html('');
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/mypetstore/changeAddress?addressId='+addressId+'&isDelete='+id,
            success: function (data) {
                AutoAddress.css({
                    display: 'block',
                    left: event.pageX + 10,
                    top: event.pageY + 10
                });
                let AutoAddressHTML='<li class="Auto">'+
                    +data.firstName+' '+data.lastName
                    +'</li>'
                    +'<li class="Auto">'
                    +data.address1+' | '+data.address2
                    +'</li>'
                    +'<li class="Auto">'
                    +data.city+' | '+data.state+' | '+data.zip+' | '+data.country
                    +'</li>'
                    +'<li class="Auto">'
                    +'<a class="Button" id="setMain" data-addrid="'+addressId+'">Set Main</a>';
                AutoAddress.html(AutoAddressHTML);
                AutoAddress.show();
            }
        });
    });

    $('#userAddresses').on('mouseleave','#address',function () {
        let AutoAddress=$('#AutoAddress');
        if (isOverInfo){
            AutoAddress.css({
                display: 'none',
            });
            AutoAddress.hide();
        }
    });
});