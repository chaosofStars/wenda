package com.nowcoder.wenda.dao;

import com.nowcoder.wenda.model.LoginTicket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginTicketDAO {

    String TABLE_NAME = " login_ticket ";
    String INSERT_FIELDS = " user_id,  expired, status, ticket ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId}, #{expired}, #{status}, #{ticket})"})
    void addTicket(LoginTicket ticket);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}"})
    LoginTicket selectTicketByTicket(String ticket);

    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);
}