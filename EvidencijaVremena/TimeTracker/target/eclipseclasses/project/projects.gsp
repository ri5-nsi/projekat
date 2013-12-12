<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Projects</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'Project.js')}" type="text/javascript"></script>
        
        <script src="${resource(dir: 'js', file: 'flexigrid.js')}" type="text/javascript"></script>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'flexigrid.pack.css')}" type="text/css">
    </head>
    <body>
        <h2>Projects</h2>
        <a class = "btn btn-primary" href="addproj" >Create New</a>
        <br/>
        <table id="dataGridProjects" style="display:none"></table>


        <script type="text/javascript">
            $("#dataGridProjects").flexigrid({
                url: 'getProjects',
                dataType: 'json',
                colModel: [
                { display: 'ID', name: 'id', width: 50, sortable: false, align: 'center', },
                { display: 'Name', name: 'name', width: 250, sortable: false, align: 'left' },
                { display: 'Description', name: 'description', width: 220, sortable: false, align: 'left' },
                { display: 'Status', name: 'status', width: 150, sortable: false, align: 'left' },
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
                height: 500
            });
        </script>
    </body>
</html>
