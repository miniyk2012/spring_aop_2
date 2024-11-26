package cn.tulingxueyuan.service.impl;

import cn.tulingxueyuan.dao.IUserDao;
import cn.tulingxueyuan.entity.User;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseServiceImpl2 {

  @Autowired
  IUserDao userDao;

  @Logger(name="用户的查询方法")
  public User select(Integer id) throws Exception {
    System.out.println("查询User");
    return userDao.select(id);
  }
  public void add(User user) throws Exception {
    System.out.println("增加User");
    userDao.add(user);
  }

  public void update(User user) throws Exception {
    System.out.println("修改User");
    userDao.update(user);
  }

  public void delete(Integer id) throws Exception {
    System.out.println("删除User");
    userDao.delete(id);
  }

}
