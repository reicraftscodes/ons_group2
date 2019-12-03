package com.ons.group2.ons_client_project.service.userfinder;

import com.ons.group2.ons_client_project.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserSearchRepoJDBC implements UserSearchRepository {

    private JdbcTemplate jdbc;
    private RowMapper<UserInfo> userInfoRowMapper;

    public UserSearchRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;

        userInfoRowMapper = ((rs, i) -> new UserInfo(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("img_url")
                ));
    }




    @Override
    public List<UserInfo> getUserInformation(Long userID) {
        return jdbc.query("SELECT * FROM user",
                new Object[]{userID},
                userInfoRowMapper
        );

    }

    @Override
    public List<UserInfo> getAllUserInformation() {
        return jdbc.query("SELECT id, first_name, last_name, img_url FROM user",
                userInfoRowMapper
        );
    }
}
