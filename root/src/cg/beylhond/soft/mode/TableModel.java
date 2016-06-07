package cg.beylhond.soft.mode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import cg.beylhond.soft.entite.ClasseMere;

public class TableModel<T extends ClasseMere> extends LazyDataModel<T>
{
	private List<T> datas;
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	public T getRowData(String rowKey)
	{
		int id = Integer.parseInt(rowKey);
		
		for (int i = 0; i < datas.size(); i++) 
		{
			ClasseMere item = new ClasseMere();
			
			if(id == item.getId())
			{
				return  (T) item;
			}
		}
		
		return null;
	}
	
	@Override
	public int getRowCount() 
	{
		return datas.size();
	}
	
	@Override
	public Object getRowKey(ClasseMere object)
	{
		return object.getId();
	}

	//*******************************************************************
	
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) 
	{
		this.datas = datas;
	}
	
	@Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) 
    {
		//liste locale
        List<T> data_in = new ArrayList<T>();
 
        //filter
        for(T item : datas)
        {
            boolean match = true;
 
            if (filters != null)
            {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();)
                {
                    try
                    {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(item.getClass().getField(filterProperty).get(item));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString()))
                        {
                            match = true;
                        }
                    else 
                    {
                            match = false;
                            break;
                    }
                    } 
                    catch(Exception e) 
                    {
                        match = false;
                    }
                }
            }
 
            if(match)
            {
                data_in.add(item);
            }
        }
 
        //sort
        if(sortField != null)
        {
            Collections.sort(data_in, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data_in.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) 
        {
            try 
            {
                return data_in.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e)
            {
                return data_in.subList(first, first + (dataSize % pageSize));
            }
        }
        else
        {
            return data_in;
        }
    } 
	 
}
