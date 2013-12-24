import javax.swing.table.AbstractTableModel;

import org.eclipse.jgit.revwalk.RevCommit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class CommitHistoryTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private String[] columnNames = {"Datum",
        								"Autor",
                                        "E-mail",
                                        "Komentar"};
        
        private List<RevCommit> data;
        
        public CommitHistoryTableModel(Iterable<RevCommit> logs) {
        	data = new ArrayList<RevCommit>();
        	for (RevCommit rev : logs) {
        		data.add(rev);
        	}
        }
 
        public int getColumnCount() {
            return columnNames.length;
            
        }
 
        public int getRowCount() {
            return data.size();
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
        	switch(col)
        	{
        		case 0: return new SimpleDateFormat("dd/MMM/yyyy, HH:mm").format(data.get(row).getAuthorIdent().getWhen());
        		case 1: return data.get(row).getAuthorIdent().getName();
        		case 2: return data.get(row).getAuthorIdent().getEmailAddress();
        		case 3: return data.get(row).getFullMessage();
        		default: return null;
        	}
        }
        
        public List<RevCommit> getData() {
        	return data;
        }
    }
