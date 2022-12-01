package com.sharedone.sharedone.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sharedone.sharedone.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public List<Product> productList(Product product) {
		return sst.selectList("productns.productList", product);
	}

	@Override
	public Product productDetail(String productCD) {
		return sst.selectOne("productns.productDetail", productCD);
	}

	@Override
	public int productUpdate(Product product) {
		return sst.update("productns.productUpdate", product);
	}

	@Override
	public int productInsert(Product product) {
		return sst.insert("productns.productInsert", product);
	}

	@Override
	public int totalByProductGroup(String productGroup) {
		return sst.selectOne("productns.totalByProductGroup", productGroup);
	}

	@Override
	public String delList(String productCD) {
		return sst.selectOne("productns.delList", productCD);
	}

	@Override
	public int deleteProduct(String delList, String productCD) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delList", delList);
		map.put("productCD", productCD);
		return sst.update("productns.deleteProduct", map);
	}
}
