package org.quickMap.dataService.dao.base;

import java.util.List;

import org.quickMap.dataService.dao.model.UserInfo;
/**
*  @author author
*/
public interface UserInfoBaseMapper {

    int insertUserInfo(UserInfo object);

    int updateUserInfo(UserInfo object);

    int update(UserInfo.UpdateBuilder object);

    List<UserInfo> queryUserInfo(UserInfo object);

    UserInfo queryUserInfoLimit1(UserInfo object);

}