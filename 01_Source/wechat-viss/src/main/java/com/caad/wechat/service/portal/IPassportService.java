package com.caad.wechat.service.portal;

import com.caad.wechat.model.viss.LoginModel;
import com.caad.wechat.model.viss.common.QueryAreaModel;
import org.springframework.web.bind.annotation.RequestParam;

public interface IPassportService {

     QueryAreaModel Login(@RequestParam("model") LoginModel model);

     String getName(@RequestParam("id")long id);
}
