package com.gree.ssm.dao;

import com.gree.ssm.bean.Authors;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午2:31 2017/12/10
 * @Description: authors sqlconnect player
 */
@Repository(value = "iAuthorsDao")
public interface AuthorsDao {
    List<Authors> list(@Param("pageIndex")Integer pageIndex , @Param("pageSize")Integer pageSize);

    /**
     * 通过作者id获取作者信息
     * @param authorsId 作者id
     * @return Authors 作者信息
     */
    Authors selectById(@Param("id")Integer authorsId);
    /**
     * 通过作者账户名获取作者信息
     * @param authorsAccount 作者账户名
     * @return Authors 作者信息
     */
    Authors selectByAccount(@Param("account")String authorsAccount);

    /**
     * 通过作者邮箱获取作者信息
     * @param authorsEmail 作者邮箱
     * @return Authors 作者信息
     */
    Authors selectByEmail(@Param("email")String authorsEmail);

    Integer deleteById(Integer id);

    /**
     * 添加作者账号
     * @param authors 作者信息(account,password,userStatus)
     * @return 受影响的行数
     */
    Integer insert(Authors authors);

    /**
     * 更新作者账户的用户状态
     * @param authorsId 作者id
     * @param userStatus 作者账户状态
     */
    void updateAuthorsStatus(@Param("id") Integer authorsId, @Param("userStatus")Integer userStatus);
    int update(Authors info);



}
