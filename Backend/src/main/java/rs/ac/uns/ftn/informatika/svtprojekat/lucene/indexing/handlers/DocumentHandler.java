package rs.ac.uns.ftn.informatika.svtprojekat.lucene.indexing.handlers;


import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.io.File;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract Post getIndexUnit(File file);
	public abstract String getText(File file);

}
