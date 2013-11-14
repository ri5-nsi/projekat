<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
                
                <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
                <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.min.css')}" type="text/css">
                <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.10.3.custom.min.css')}" type="text/css">
                <link rel="stylesheet" href="${resource(dir: 'css', file: 'Site.css')}" type="text/css">

                <script src="${resource(dir: 'js', file: 'jquery-1.9.1.min.js')}" type="text/javascript"></script>
                <script src="${resource(dir: 'js', file: 'jquery.validate1.min.js')}" type="text/javascript"></script>
                <script src="${resource(dir: 'js', file: 'jquery-ui-1.10.3.custom.min.js')}" type="text/javascript"></script>

                <g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                  <div class="container-fluid">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="../">Project Management</a>
                    <div class="nav-collapse collapse">
                          <p class="navbar-text pull-right">
                            <a href="#">LogOn</a> |
                            <a href="#">User</a>
                            <img border="1" vspace="2" hspace="2" height="40px" width="40px" src="">
                            <a href="#" class="navbar-link">Logged User Name</a>

                          </p>
                      <ul class="nav">
                          <li class="active"><a href="../">Home</a></li>
                        <li><a href="../Project/index">Project</a></li>
                        <li><a href="../User/index">User</a></li>
                      </ul>
                    </div><!--/.nav-collapse -->
                  </div>
                </div>
            </div>
            <div style="height: auto !important; margin:0 auto -20px; margin-top:60px;" class="container-fluid">
                  <div id="row-fluid">
                      <g:layoutBody/>
                  </div>
            </div>
            <script src="${resource(dir: 'js', file: 'bootstrap.min.js')}" type="text/javascript"></script>
            <g:javascript library="application"/>
	</body>
</html>
