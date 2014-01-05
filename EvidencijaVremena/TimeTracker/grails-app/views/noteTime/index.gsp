<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>Users</title>
        <script src="${resource(dir: 'js', file: 'jquery.validate.min.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'User.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js', file: 'Project.js')}" type="text/javascript"></script>
        
        <script src="${resource(dir: 'js', file: 'flexigrid.js')}" type="text/javascript"></script>
       
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'flexigrid.pack.css')}" type="text/css">
    </head>
    <body>
    
     <div id='notethetime'> 
        <li>Projekat:        </li>           <select id="selectprojects">     		
        </select> <br>
        <li>Task:            </li>			 <select id="selecttasks">     		
        </select> <br>
        <li>Radni sati:      </li>			 <input id="taskhours">
        </input> <br>
        <li>Komentar:        </li>			 <textarea rows="4" cols="50" id="comment">
        </textarea> <br>
        <br>
        <input class = "btn btn-primary" id="inputtime" type="submit" value="Snimi">
        </input>
        <label id="lbltipAddedComment"></label>
     </div>
        
        <br/>
        
       
        
        <table id="dataGridProjects" style="display:none"></table>


        <script type="text/javascript">


     
        	
        	$(document).ready(function () {
        		
	        	$.post( "getProjectsDiv", function( data ) {
	        		for(var i=0; i<data['rows'].length; i++)
	        		{
	        			var opt = document.createElement('option');
	    				opt.value = data['rows'][i]['cell']['id'];
				    	opt.innerHTML = data['rows'][i]['cell']['name'];
					    $( "#selectprojects" ).append( opt);
					 } 
				});
				
				$( "#selectprojects").change(function() {
						$.post( "getTasksDiv", { projectid: document.getElementById("selectprojects").value }, function( data ) {
						 $( "#selecttasks" ).children().remove();
		        			 for(var i=0; i<data['rows'].length; i++)
		        			 {
		        			  var opt = document.createElement('option');
		    				  opt.value = data['rows'][i]['cell']['id'];
					    	  opt.innerHTML = data['rows'][i]['cell']['name'];	
					    	  			  
						      $( "#selecttasks" ).append( opt);
						  	 }
						  });	
				 });
					
				$("#inputtime").click(function(event) {
					$.post( "insertNoteTimeData", { hours_recorded: document.getElementById('taskhours').value, uuidtask: document.getElementById('selecttasks').value, timesheet: "nest", comment: document.getElementById("comment").value }, function( data ) {
						 	document.getElementById('lbltipAddedComment').innerHTML = "Vrijeme na tasku evidentirano"
						  });	
				});
						  	 
			});

        
        
            $("#dataGridProjects").flexigrid({
                url: 'getProjects',
                dataType: 'json',
                colModel: [
                { display: 'ID', name: 'id', width: 50, sortable: false, align: 'center', },
                { display: 'Date', name: 'date', width: 250, sortable: false, align: 'left' },
                { display: 'Hours', name: 'hours', width: 220, sortable: false, align: 'left' },
                { display: 'Name', name: 'name', width: 150, sortable: false, align: 'left' },
                { display: 'Comment', name: 'comment', width: 150, sortable: false, align: 'left' },
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
