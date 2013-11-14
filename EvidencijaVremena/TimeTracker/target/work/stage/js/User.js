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