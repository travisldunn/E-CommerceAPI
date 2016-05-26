package data;

public class testHolder
{
private int id;
private productsEntitie products;
private int quantity;
private shoppingCartEntitie shoppingCart_id;

public testHolder(int quantity, productsEntitie products)
{
	super();

	this.quantity = quantity;
	this.products =  products;
}




public testHolder(int id, productsEntitie products, int quantity, shoppingCartEntitie shoppingCart_id)
{
	super();
	this.id = id;
	this.products = products;
	this.quantity = quantity;
	this.shoppingCart_id = shoppingCart_id;
}

/**
 * @return the id
 */
public int getId()
{
	return id;
}
/**
 * @return the productsEntitie
 */

/**
 * @return the quantity
 */
public int getQuantity()
{
	return quantity;
}
/**
 * @param id the id to set
 */
public void setId(int id)
{
	this.id = id;
}
/**
 * @param productsEntitie the productsEntitie to set
 */

/**
 * @param quantity the quantity to set
 */
public void setQuantity(int quantity)
{
	this.quantity = quantity;
}

/**
 * @return the products
 */
public productsEntitie getProducts()
{
	return products;
}

/**
 * @param products the products to set
 */
public void setProducts(productsEntitie products)
{
	this.products = products;
}


public shoppingCartEntitie getShoppingCart_id()
{
	return shoppingCart_id;
}

/**
 * @param shoppingCart_id the shoppingCart_id to set
 */
public void setShoppingCart_id(shoppingCartEntitie shoppingCart_id)
{
	this.shoppingCart_id = shoppingCart_id;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString()
{
	return "testHolder [id=" + id + ", products=" + products + ", quantity=" + quantity + ", shoppingCart_id="
			+ shoppingCart_id + "]";
}






}
