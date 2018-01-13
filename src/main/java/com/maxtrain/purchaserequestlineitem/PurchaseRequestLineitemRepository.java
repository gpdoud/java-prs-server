package com.maxtrain.purchaserequestlineitem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxtrain.utility.JsonResponse;

public interface PurchaseRequestLineitemRepository extends CrudRepository<PurchaseRequestLineitem, Integer>{
	public Iterable<PurchaseRequestLineitem> findAllByPurchaseRequestId(int id);
}
