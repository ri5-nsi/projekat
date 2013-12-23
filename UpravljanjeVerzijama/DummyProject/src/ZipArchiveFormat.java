import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.jgit.api.ArchiveCommand.Format;
import org.eclipse.jgit.lib.FileMode;
import org.eclipse.jgit.lib.ObjectLoader;

public final class ZipArchiveFormat implements Format<ZipOutputStream> {

	public ZipOutputStream createArchiveOutputStream(OutputStream s) throws IOException {
		return new ZipOutputStream(s);
	}

	public void putEntry(ZipOutputStream out, String path, FileMode mode, ObjectLoader loader) throws IOException {
		ZipEntry entry = new ZipEntry(path);
		out.putNextEntry(entry);
		out.write(loader.getBytes());
		out.closeEntry();
	}

	public Iterable<String> suffixes() {
		return Collections.singleton(".zip");
	}
}