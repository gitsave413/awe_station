package cg.beylhond.soft.ifaces;

import javax.ejb.Remote;

import cg.beylhond.soft.entite.ClasseMere;

@Remote
public interface IDaoMereRemote<T extends ClasseMere> extends IDaoMere<ClasseMere>
{
	
}
