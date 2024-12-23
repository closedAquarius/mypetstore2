package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.persistence.ProductDao;
import csu.web.mypetstore.persistence.impl.ItemDaoImpl;
import csu.web.mypetstore.persistence.impl.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetDetailsServlet extends HttpServlet {
    ProductDao productDao = new ProductDaoImpl();

    ItemDao itemDao = new ItemDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryID = req.getParameter("categoryId");
        List<Product> productList=productDao.getProductListByCategory(categoryID);
        List<Item> itemList = itemDao.getItemListByProduct(productList.get(0).getProductId());
        for(int i=1; i<productList.size(); i++){
            Product product = productList.get(i);
            List<Item> items=itemDao.getItemListByProduct(product.getProductId());
            for(int j=0; j<items.size(); j++){
                Item item = items.get(j);
                itemList.add(item);
            }
        }
        String result = JSON.toJSONString(itemList);
        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}
