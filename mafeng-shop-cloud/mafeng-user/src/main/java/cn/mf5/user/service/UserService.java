package cn.mf5.user.service;

import cn.mf5.shop.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface UserService extends IService<User> {

    public void queryUserByName();

    void deduct(Long id, Long money);

    @TwoPhaseBusinessAction(name = "deductTCC"
            ,commitMethod = "deductCommit",
            rollbackMethod = "deductRollback")
    void deductTCC(@BusinessActionContextParameter("id") Long id, @BusinessActionContextParameter("money")Long money);

    public boolean deductCommit(BusinessActionContext actionContext);

    public boolean deductRollback(BusinessActionContext actionContext);
}
