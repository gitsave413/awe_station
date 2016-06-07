package cg.beylhond.soft.ifaces;

import javax.ejb.Local;

import cg.beylhond.soft.entite.ClasseMere;

@Local
public interface IDaoMereLocal <T extends ClasseMere> extends IDaoMere<ClasseMere>
{
	
}
