<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Add Category</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'Category.js')}" type="text/javascript"></script>
    </head>
    <body>
        <g:form name="CategoryForm" url="[controller:'Category',action:'addCategory']">
        <h2>Add Category</h2>
        
        <div class="control-group">
            <label class="control-label" for="Name">Name</label>
            <div class="controls">
                <input data-val="true" data-val-length="Must be between 2 and 50 charaters." data-val-length-max="50" data-val-length-min="2" id="Name" name="Name" type="text" value="${category.Name}">
                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
            </div>
        </div>
        
        <p>
            <input class="btn btn-primary" type="submit" value="Save" />
        </p>
        
    </g:form>
    
    </body>
</html>
