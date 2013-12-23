import java.text.SimpleDateFormat;
import org.eclipse.jgit.revwalk.RevCommit;


public class RevCommitSpecial {
	private RevCommit commit;
	
	public RevCommitSpecial(RevCommit commit) {
		this.commit = commit;
	}
	
	public RevCommit getRevCommit () {
		return commit;
	}
	
	public String toString() {
		String s = new SimpleDateFormat("dd/MMM/yyyy, HH:mm").format(commit.getAuthorIdent().getWhen()).toString()
				+ "  " + commit.getAuthorIdent().getName() + ": ";
		if (commit.getShortMessage().length() > 30)
			s += commit.getShortMessage().substring(0, 29);
		else
			s += commit.getShortMessage();
		return s;
	}

}
