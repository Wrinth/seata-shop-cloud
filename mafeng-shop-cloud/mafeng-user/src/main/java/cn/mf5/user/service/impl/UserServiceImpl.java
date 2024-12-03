package cn.mf5.user.service.impl;

import cn.mf5.shop.pojo.User;
import cn.mf5.user.mapper.UserMapper;
import cn.mf5.user.service.UserService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    @SentinelResource("queryUserByName")
    public void queryUserByName() {
        System.out.println("根据name查询用户");
    }

    @Override
    public void deduct(Long id, Long money) {
        //根据id查询用户
        User user = getById(id);
        //更新用户余额
        user.setBalance(user.getBalance()-money);
        updateById(user);
    }

    //这个Map用于预留资源
    private Map<String,User> contextMap = new ConcurrentHashMap<>();

    @Override
    public void deductTCC(Long id, Long money) {
        //预览资源，备份更新前的User对象数据
        log.info("执行try方法");
        User oldUser = getById(id);
        //获取全局事务ID
        String xid = RootContext.getXID();
        contextMap.put(xid,oldUser);
    }

    @Override
    public boolean deductCommit(BusinessActionContext actionContext) {
        log.info("执行commit方法");
        //获取try方法的参数
        Integer id = (Integer) actionContext.getActionContext("id");
        Integer money = (Integer) actionContext.getActionContext("money");

        User user = getById(id);
        user.setBalance(user.getBalance()-money);
        boolean flag = updateById(user);
        return flag;
    }

    @Override
    public boolean deductRollback(BusinessActionContext actionContext) {
        log.info("执行cancel方法");
        //获取预留资源
        User oldUser = contextMap.get(actionContext.getXid());
        boolean flag = updateById(oldUser);
        return flag;
    }
}
