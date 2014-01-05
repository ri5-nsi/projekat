<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Edit Comment</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'Category.js')}" type="text/javascript"></script>
    </head>
    <body>
        <g:form name="CategoryForm" url="[controller:'NoteTime',action:'editujComment']">
        <h2>Edit Comment</h2>
        
        <div class="control-group">
            <label class="control-label" for="Name">New Comment</label>
            <div class="controls">
                <input data-val="true" data-val-length="Must be between 2 and 50 charaters." data-val-length-max="50" data-val-length-min="2" id="text" name="text" type="text" value="${category.Name}">
                <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
            </div>
        </div>
        <input type="hidden" name="CommentID" value="${ params.CommentID}" />
          
        <p>
            <input class="btn btn-primary" type="submit" value="Save Changes" />
        </p>
        
    </g:form>
    
    </body>
</html>