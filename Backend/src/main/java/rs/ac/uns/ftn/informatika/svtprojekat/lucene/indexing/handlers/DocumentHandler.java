package rs.ac.uns.ftn.informatika.svtprojekat.lucene.indexing.handlers;


import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.io.File;

public abstract class DocumentHandler {
    public abstract Post getIndexUnitPost(File file);

    public abstract Community getIndexUnitCommuntiy(File file);

    public abstract String getText(File file);

}
