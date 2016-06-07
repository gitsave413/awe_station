package cg.beylhond.soft.mode;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import cg.beylhond.soft.entite.ClasseMere;

public class LazySorter implements Comparator<ClasseMere> 
{
	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder)
	{
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings("unchecked")
	public int compare(ClasseMere item1, ClasseMere item2)
	{
		try
		{
			Object value1 = ClasseMere.class.getField(this.sortField).get(item1);
			Object value2 = ClasseMere.class.getField(this.sortField).get(item2);

			@SuppressWarnings("rawtypes")
			int value = ((Comparable)value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		}
		catch(Exception e) 
		{
			throw new RuntimeException();
		}
	}
}
