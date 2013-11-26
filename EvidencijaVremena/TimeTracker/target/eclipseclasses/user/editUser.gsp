<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Add User</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'User.js')}" type="text/javascript"></script>
    </head>
    <body>
        <g:form name="UserForm" url="[controller:'User',action:'editUserData']">
            <h2>Change Data</h2>
            <input data-val="true" data-val-number="The field UserID must be a number." data-val-required="The UserID field is required." id="UserID" name="UserID" type="hidden" value="${user.UserID}">
            <div class="control-group">
                <label class="control-label" for="FirstName">Firstname</label>
                <div class="controls">
                    <input data-val="true" data-val-length="Must be between 2 and 50 charaters." data-val-length-max="50" data-val-length-min="2" data-val-required="This is required" id="FirstName" name="FirstName" type="text" value="${user.FirstName}">
                    <span class="field-validation-valid" data-valmsg-for="FirstName" data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="LastName">Lastname</label>
                <div class="controls">
                    <input data-val="true" data-val-length="Must be between 2 and 50 charaters." data-val-length-max="50" data-val-length-min="2" id="LastName" name="LastName" type="text" value="${user.LastName}">
                    <span class="field-validation-valid" data-valmsg-for="LastName" data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="userStatus">Status</label>
                <div class="controls">
                    <g:select name="UserStatus" from="${["Created", "Unactive", "Banned", "Active"]}" value="${user.UserStatus}" />
                    <span class="field-validation-valid" data-valmsg-for="userStatus" data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="Email">Email</label>
                <div class="controls">
                    <input data-val="true" data-val-regex="Please enter a valid e-mail address" data-val-regex-pattern="^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$" data-val-required="This is required" id="Email" name="Email" type="text" value="${user.Email}">
                    <span class="field-validation-valid" data-valmsg-for="Email" data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="RepeatPassword">Permissions</label>
                <div class="controls">
                    <g:checkBox name="AddUser" value="${true}" checked="${user.AddUser}" /> Add Users <br>
                    <g:checkBox name="EditUser" value="${true}" checked="${user.EditUser}" /> Edit Users <br>

                    <g:checkBox name="AddProject" value="${true}" checked="${user.AddProject}" /> Add Projects <br>
                    <g:checkBox name="EditProject" value="${true}" checked="${user.EditProject}" /> Edit Projects <br>

                    <div style="display:none;"><input checked="checked" data-val="true" data-val-required="The Admin field is required." id="Admin" name="Admin" type="checkbox" value="true"><input name="Admin" type="hidden" value="false"> View statistics <br></div>
                </div>
            </div>
            <p>
                <input class="btn btn-primary" type="submit" value="Change Data">
            </p>
        </g:form>
        <g:form name="UserForm" url="[controller:'User',action:'editUserPassword']">
            <h2>Change Password</h2>
            <input data-val="true" data-val-number="The field UserID must be a number." data-val-required="The UserID field is required." id="UserID" name="UserID" type="hidden" value="${user.UserID}">
            <div class="control-group">
                <label class="control-label" for="Password">Password</label>
                <div class="controls">
                    <input data-val="true" data-val-required="This is required" id="Password" name="Password" type="password">
                    <span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="RepeatPassword">Repeat Password</label>
                <div class="controls">
                    <input data-val="true" data-val-equalto="&amp;#39;Repeat Password&amp;#39; and &amp;#39;Password&amp;#39; do not match." data-val-equalto-other="*.Password" data-val-required="This is required" id="RepeatPassword" name="RepeatPassword" type="password">
                    <span class="field-validation-valid" data-valmsg-for="RepeatPassword" data-valmsg-replace="true"></span>
                </div>
            </div>        
            <p>
                <input class="btn btn-primary" type="submit" value="Change Password">
            </p>
        </g:form>
    </body>
</html>