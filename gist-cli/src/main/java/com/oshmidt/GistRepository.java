package com.oshmidt;


import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

public abstract class GistRepository {
	
	abstract List<Gist> readGists();
    abstract void writeGists(List<Gist> gists);    
    abstract void writeFiles(Gist gist);
	abstract Map<Gist, List<GistFile>> readFiles();

}
