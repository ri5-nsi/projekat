<!DOCTYPE html>
<html lang="en">
<head>
    <title>Project Management - LogIn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="HandheldFriendly" content="true" />
        
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.10.3.custom.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'Site.css')}" type="text/css">

    <script src="${resource(dir: 'js', file: 'jquery-1.9.1.min.js')}" type="text/javascript"></script>
    
</head>
<body style="background:#333; color:#FFF;">
    <div style="margin-top:60px;" class="container-fluid">
        <div id="row-fluid" style="text-align:center;">
            <g:form name="UserForm" url="[controller:'Login',action:'login']">
                <h2>Project Management</h2>
                <div>
                    <h3>Log On</h3>

                    <div class="control-group">
                        <label class="control-label" for="Username">Username</label>
                        <div class="controls">
                            <input id="Username" name="Username" type="text" value="">
                            <span class="field-validation-valid" data-valmsg-for="Username" data-valmsg-replace="true"></span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="Password">Password</label>
                        <div class="controls">
                            <input id="Password" name="Password" type="password">
                            <span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
                        </div>
                    </div>
                    <div class="row-fluid" style="float:left; margin:15px;">
                        <input class="btn btn-primary" type="submit" value="Login">
                    </div>
    
                    <h4 style="color:Red;"></h4>
                </div>
                
            </g:form>
        </div>
        <div id="footer">
            
        </div>
    </div>
</body>
</html>
