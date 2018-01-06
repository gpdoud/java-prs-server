package com.maxtrain.purchaserequestlineitem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxtrain.utility.JsonResponse;

public interface PurchaseRequestLineitemRepository extends CrudRepository<PurchaseRequestLineitem, Integer>{

}
