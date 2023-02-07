package servlet_product_controllor;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet_product_dao.Productdao;
import servlet_product_dto.Product;

public class ProductCont extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String brand=req.getParameter("brand");
	long price=Long.parseLong(req.getParameter("price"));
	String manufacturer=req.getParameter("manufacturer");
	String state=req.getParameter("state");
	 
	Product product=new Product();
	product.setBrand(brand);
	product.setManufacturer(manufacturer);
	product.setName(name);
	product.setState(state);
	if(state.equals("KAR")) {
	   ServletContext context=getServletContext();
	   double cgst=Double.parseDouble(context.getInitParameter("CGST"));
	   ServletConfig config=getServletConfig();
	   double sgst=Double.parseDouble(config.getInitParameter("KAR"));
	   double karprice=price+(cgst+sgst)*price;
	   product.setPrice(karprice);
	}
	else {
		  ServletContext context=getServletContext();
		   double cgst=Double.parseDouble(context.getInitParameter("CGST"));
		   ServletConfig config=getServletConfig();
		   long sgst=Long.parseLong(config.getInitParameter("TN"));
		   double tnprice=price+(cgst+sgst)*price;
		   product.setPrice(tnprice);
	}
	Productdao productdao=new Productdao();
	productdao.saveProduct(product);
}
}
