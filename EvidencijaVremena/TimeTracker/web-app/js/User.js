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

    $("#dataGridUsers").flexigrid({
            url: 'getUsers',
            dataType: 'json',
            colModel: [
            { display: 'ID', name: 'id', width: 50, sortable: false, align: 'center', },
            { display: 'First Name', name: 'firstName', width: 180, sortable: false, align: 'left' },
            { display: 'Last Name', name: 'lastName', width: 230, sortable: false, align: 'left' },
            { display: 'Username', name: 'username', width: 230, sortable: false, align: 'left' },
            { display: 'Email', name: 'email', width: 200, sortable: false, align: 'left' },
            { display: 'Status', name: 'status', width: 120, sortable: false, align: 'left' },
            { display: 'Actions', name: 'actions', width:150, sortable: false, align:'center' }
                    ],
            sortname: "id",
            sortorder: "asc",
            usepager: true,
            title: 'Users',
            useRp: true,
            rp: 50,
            showTableToggleBtn: false,
            width: 700,
            height: 400
        });


    // validate signup form on keyup and submit
    $("#UserForm").validate({
        rules: {
            FirstName: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            LastName: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            Email: {
                required: true,
                email: true
            },
            Username:{
                required: true,
                minlength:3,
                maxlength: 20
            },
            Password:{
                required: true,
                minlength:6,
                maxlength: 50
            },
            RepeatPassword:{
                required: true, 
                equalTo: "#Password", 
                minlength: 6
            }
        },
        messages: {
            FirstName: {
                required: "This field is required.",
                minlength: "Must consist of at least 2 characters.",
                maxlength: "Must consist of at moast 50 characters."
            },
            LastName: {
                required: "This field is required.",
                minlength: "Must consist of at least 2 characters.",
                maxlength: "Must consist of at moast 50 characters."
            },
            Email: {
                required: "This field is required.",
                email: "Email is not valid."
            },
            Username: {
                required: "This field is required.",
                minlength: "Must consist of at least 3 characters.",
                maxlength: "Must consist of at moast 20 characters."
            },
            Password: {
                required: "This field is required.",
                minlength: "Must consist of at least 6 characters.",
                maxlength: "Must consist of at moast 50 characters."
            },
            RepeatPassword: {
                required: "This field is required.",
                minlength: "Must consist of at least 6 characters.",
                equalTo: "Must match with Password field."
            }
        }
    });
});