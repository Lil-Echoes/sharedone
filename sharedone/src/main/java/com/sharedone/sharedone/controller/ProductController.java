package com.sharedone.sharedone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharedone.sharedone.model.Product;
import com.sharedone.sharedone.service.ProductService;

import net.sf.json.JSONArray;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	@RequestMapping("main")
	public String main () {
		return "/main";
	}
	
	@RequestMapping("product")
	public String product(Product product, Model model, String cdnm, String productGroup) {
		
		product.setCdnm(cdnm);
		product.setProductGroup(productGroup);
		
		List<Product> productList = ps.productList(product);
		
		List<Product> productAllList = ps.productAllList();
		
		model.addAttribute("productList", productList);
		model.addAttribute("productAllList", productAllList);
		
		return "/nolay/product";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "productInsert")
	@ResponseBody
	public Map<String, Object> productInsert(@RequestParam String data, Model model, Product product) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> info = new ArrayList<Map<String,Object>>();
		    info = JSONArray.fromObject(data);
		    
		    for (Map<String, Object> productInfo : info) {
		    	System.out.println(productInfo.get("productNM"));
		    	String productNM = (String) productInfo.get("productNM");
		        String unit = (String) productInfo.get("unit");
		        String productGroup = (String) productInfo.get("productGroup");
		        
		        product.setProductNM(productNM);
		        product.setUnit(unit);
		        product.setProductGroup(productGroup);
		        
		        
		        int totalProduct = ps.totalProduct();
		  	  	
		        
		        String productCD = "";
		  	  	
	        	productCD = "P"+String.format("%05d",totalProduct+1);
	        	product.setProductCD(productCD);
		        
		        ps.productInsert(product);
		        
		    }  
		     result.put("result", true);
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("result", false);
		}
		
		return result;
	}
	
	@RequestMapping(value = "productDelete", produces = "text/html;charset=utf-8")
	public String productDelete (Model model, @RequestParam("selectChk") String[] selectChk) {
		
		String[] selectDelete =selectChk;
		String[] delList= new String[selectDelete.length];
		
		System.out.println(selectDelete);
		
		if (selectDelete[0].equals("selectAll")) {
			for (int i = 1; i < selectDelete.length; i++) {
				System.out.println(selectDelete[i]);
				if (ps.delList(selectDelete[i]).equals("n")) {
					delList[i-1] = "y";
				} else if (ps.delList(selectDelete[i]).equals("y")) {
					delList[i-1] = "n";
				}
			}
		} else {
			for (int i = 0; i < selectDelete.length; i++) {
				if (ps.delList(selectDelete[i]).equals("n")) {
					System.out.println(selectDelete[i]);
					delList[i] = "y";
				} else if (ps.delList(selectDelete[i]).equals("y")) {
					delList[i] = "n";
				}
			}
		}
		
		int result=0;

		if (selectDelete[0].equals("selectAll")) {
			for (int i = 1; i < selectDelete.length; i++) {
				result=ps.deleteProduct(delList[i-1], selectDelete[i]);
			}
		} else { 
			for (int i = 0; i < selectDelete.length; i++) {
				result=ps.deleteProduct(delList[i], selectDelete[i]);
			}
		}
		
		System.out.println(result);
		model.addAttribute("result", result);
		
		return "/nolay/product";
	}
	
	
	@RequestMapping("productUpdate")
	public String productUpdate(Product product, Model model, String productCD, String type, String value) {
		
		System.out.println(productCD);
		System.out.println(type);
		System.out.println(value);
		
		if (type.equals("productNM")) {
			System.out.println("productNM");
			product.setProductNM(value);
		} else if (type.equals("unit")) {
			product.setUnit(value);
		} else if (type.equals("productGroup")) {
			product.setProductGroup(value);
		}
		
		product.setProductCD(productCD);
		
		System.out.println(product);
		
		String result = String.valueOf(ps.productUpdate(product));
		
		return "/nolay/product";
	}
	
	
	
	
	
	
	
}
