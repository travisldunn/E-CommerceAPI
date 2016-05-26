package data;

import java.io.Serializable;


public class CompostiteKey implements Serializable
{
	private int shoppingCart_id;	
	private int products_id;
	public CompostiteKey(){
		
	}
	public CompostiteKey(int shoppingCart_id, int products_id)
	{
		
		this.shoppingCart_id = shoppingCart_id;
		this.products_id = products_id;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + products_id;
		result = prime * result + shoppingCart_id;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompostiteKey other = (CompostiteKey) obj;
		if (products_id != other.products_id)
			return false;
		if (shoppingCart_id != other.shoppingCart_id)
			return false;
		return true;
	}
	
}
