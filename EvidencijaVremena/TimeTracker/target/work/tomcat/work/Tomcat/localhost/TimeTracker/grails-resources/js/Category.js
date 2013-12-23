function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
             .toString(16)
             .substring(1);
};

function guid() {
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
         s4() + '-' + s4() + s4() + s4();
}

$(document).ready(function () {

    $("#dataGridCategories").flexigrid({
            url: 'getCategories',
            dataType: 'json',
            colModel: [
            { display: 'ID', name: 'id', width: 50, sortable: false, align: 'center' },
            { display: 'Type', name: 'type', width: 180, sortable: false, align: 'left' },
            { display: 'Name', name: 'name', width: 230, sortable: false, align: 'left' },
         { display: 'Actions', name: 'actions', width:150, sortable: false, align:'center' }
            
        ],
            sortname: "id",
            sortorder: "asc",
            usepager: true,
            title: 'Categories',
            useRp: true,
            rp: 50,
            showTableToggleBtn: false,
            width: 700,
            height: 400
        });


    // validate signup form on keyup and submit
    $("#CategoryForm").validate({
        rules: {
            
            Name: {
                required: true,
                minlength: 2,
                maxlength: 50
            }
        },
        messages: {
            
            Name: {
                required: "This field is required.",
                minlength: "Must consist of at least 2 characters.",
                maxlength: "Must consist of at moast 50 characters."
            }
        }
    });
});