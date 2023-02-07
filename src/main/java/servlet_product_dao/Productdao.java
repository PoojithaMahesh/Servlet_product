package servlet_product_dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import servlet_product_dto.Product;

public class Productdao {
public EntityManager getEMEntityManager() {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
	return entityManagerFactory.createEntityManager();
	
}
public void saveProduct(Product product) {
	EntityManager entityManager=getEMEntityManager();
    EntityTransaction entityTransaction=entityManager.getTransaction();
    entityTransaction.begin();
    entityManager.persist(product);
    entityTransaction.commit();
}
}
