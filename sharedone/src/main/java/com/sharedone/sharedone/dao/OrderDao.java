package com.sharedone.sharedone.dao;

import java.sql.Date;
import java.util.List;

import com.sharedone.sharedone.model.Notice;
import com.sharedone.sharedone.model.Order;

public interface OrderDao {

	List<Order> orderList(Order order);

	Order selectOrderHeader(String soNo);

	List<Order> selectOrderItems(String soNo);

	Order selectByProductCD(String productCD);

	int addOrderDetail(String soNo, String productCD, int qty, int unitPrice);

	int removeOrderDetail(String soNo, String productCD);

	int addOrder(String soNo, String buyerCD, String soUser, Date requestDate, String currency, String pricingDate);

	int totalOrder();

	int approvalUpdate(String soNo, String status);
	
	List<Order> pendingApprovalList(Order order);

	int updateApproveOrRefer(Notice notice);

	int checkValidPrice(String productCD, String buyerCD, String currency);

	int validPrice(String productCD, String buyerCD, String currency);

	int defaultPrice(String productCD, String currency);

	int detailProductUpdate(String soNo, String productCD, int qty, int unitPrice);

	int detailProductDelete(String soNo);

	List<Order> orderAllList(Order order);

	int totalPrice(String soNo);

}
