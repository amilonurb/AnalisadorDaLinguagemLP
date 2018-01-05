package compilador.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(".lp");
	}

	@Override
	public String getDescription() {
		return "*.lp";
	}
}