<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Users</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'User.js')}" type="text/javascript"></script>
        
        <script src="${resource(dir: 'js', file: 'flexigrid.js')}" type="text/javascript"></script>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'flexigrid.pack.css')}" type="text/css">
    </head>
    <body>
        <h2>Users</h2>
        <a class = "btn btn-primary" href="addusr" >Create New</a>
        <br/>
        <table id="dataGridUsers" style="display:none"></table>

        <div id="dialog-confirm" style="display:none;" title="Delete User?">
                <span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
                User will be permanently deleted. Are you sure?
        </div>
    </body>
</html>
