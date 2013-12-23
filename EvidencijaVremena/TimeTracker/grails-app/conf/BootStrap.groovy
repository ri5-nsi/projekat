import timetracker.Project;
import timetracker.NoteTime;


class BootStrap {

    def init = { servletContext ->
		/*Project proj = new Project();
		
		def projekti = proj.GetListOfAllProjects();
		
		def noteTime = new NoteTime(
			summary: "nest",
			details: "nest 2"
			
			);
		
		for (projekat in projekti)
		{
			noteTime.addToProjekti(projekat);
		}
		noteTime.save(failOnError: true);*/
		
		
    }
    def destroy = {
    }
}
