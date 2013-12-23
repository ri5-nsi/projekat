<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title>NoteTheTime</title>


        <link rel="stylesheet" href="${resource(dir: 'css', file: 'flexigrid.pack.css')}" type="text/css">
    </head>
    <body>
        <h2>Note The Time</h2>
        <br/>
        <div class="chooseWeek" >
        	
        	 <g:link class="create" action="index2"><g:img  dir="css/images" file="prev.gif" width="80" height="80"/></g:link>
        	  07.12.2013 - 14.12.2013
        	 <g:link class="create" action="index2"><g:img  dir="css/images" file="next.gif" width="80" height="80"/></g:link>
        </div>
         
         <div>
         
			<dl class="lijevo" >
				<dt>Projekti</dt>
				 <dd>
				 	<ul>
				 		<g:each in="${ projekti }" var="projekat" >				 		
				 		<li>"amar" </li>
				 		<li>"dvaput" </li>
				 		</g:each>
				 	</ul>
				 </dd>
			</dl>
         </div>
          <a class = "btn btn-primary" href="addnote" >Note The Time</a>
        
        
        <table id="dataGridWeek" style="display:none"></table>

        
    </body>
</html>