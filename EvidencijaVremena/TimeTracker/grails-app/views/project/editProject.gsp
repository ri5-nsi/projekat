<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Add User</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'Project.js')}" type="text/javascript"></script>
        
        <script src="${resource(dir: 'js', file: 'ui.multiselect.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'Project.js')}" type="text/javascript"></script>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'ui.multiselect.css')}" type="text/css">
    </head>
    <body>
        <g:form name="UserForm" url="[controller:'Project',action:'editProjectData']">
        <script type="text/javascript">
            $(document).ready(function () {
                $("#proUsers").css("height", "300px");
                $("#proUsers").css("width", "350px");
                $("#proUsers").multiselect();
            });
        </script>
        <h2>Project</h2>
<input data-val="true" data-val-number="The field ProjectID must be a number." data-val-required="The ProjectID field is required." id="ProjectID" name="ProjectID" type="hidden" value="${project.ProjectID}">
<div class="control-group">
    <label class="control-label" for="Name">Name</label>
    <div class="controls">
        <input data-val="true" data-val-length="Must be between 2 and 50 charaters." data-val-length-max="50" data-val-length-min="2" data-val-required="This is required" id="Name" name="Name" type="text" value="${project.Name}">
        <span class="field-validation-valid" data-valmsg-for="Name" data-valmsg-replace="true"></span>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="Description">Description</label>
    <div class="controls">
        <textarea cols="20" id="Description" name="Description" rows="2">${project.Description}</textarea>
        <span class="field-validation-valid" data-valmsg-for="Description" data-valmsg-replace="true"></span>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="projectStatus">Status</label>
    <div class="controls">
        <g:select name="ProjectStatus" from="${["New", "Closed", "Active", "Finished"]}" value="${project.ProjectStatus}" />
        <span class="field-validation-valid" data-valmsg-for="projectStatus" data-valmsg-replace="true"></span>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="proUsers">Users</label>
    <div class="controls">
        <g:select name="ProUsers" id="proUsers"
            style="height: 300px; width: 350px;"  
            from="${user}"
            value="1"
            optionKey="UserID"
            multiple="true" />
        <span class="field-validation-valid" data-valmsg-for="proUsers" data-valmsg-replace="true"></span>
    </div>
</div>        
    <div id="ProjectVersionHolder">
        <h4>Versions</h4>
    </div>
    <div>
        <a id="AddProjectVersion" class="btn btn-small btn-success"><i class="icon-plus icon-white"></i> Add New Version</a>
    </div>
    <div id="ProjectComponentHolder">
        <h4>Components</h4>

    </div>
    <div>
        <a id="AddProjectComponent" class="btn btn-small btn-success"><i class="icon-plus icon-white"></i> Add New Component</a>
    </div>
    <p>
        <input class="btn btn-primary" type="submit" value="Save">
    </p>
    </g:form>
    <div>
        <a class="btn btn-primary" href="/Project">Back to Projects</a>
    </div>
    </body>
</html>



