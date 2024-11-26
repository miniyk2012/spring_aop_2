package cn.tulingxueyuan.dao.impl;

import cn.tulingxueyuan.dao.IUserDao;
import cn.tulingxueyuan.entity.User;
import org.springframework.stereotype.Repository;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Repository
public class UserDaoImpl implements IUserDao {
    @Override
    public User select(Integer id) throws Exception {
        if(id==null){
            throw new Exception("Id不能为null");
        }
        // 访问数据库，查询User
        return new User();
    }

    @Override
    public void add(User user) throws Exception {
        if(user==null){
            throw new Exception("user不能为null");
        }

    }

    @Override
    public void update(User user) throws Exception {
        if(user==null){
            throw new Exception("user不能为null");
        }

    }

    @Override
    public void delete(Integer id) throws Exception {

        if(id==null){
            throw new Exception("id不能为null");
        }

    }
}
