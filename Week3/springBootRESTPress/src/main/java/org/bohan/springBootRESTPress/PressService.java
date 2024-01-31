package org.bohan.springBootRESTPress;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PressService implements IPressService {
    private static final String hostName = "localhost";
    private static final String username = "root";
    private static final String password = "wbh53100";
    private static final String database = "learn_spring_boot";


    @Override
    public Press findById(long id) {
        // 从数据库获取数据
        DataRepository dataRepository = new DataRepository(hostName, username, password, database);
        Press press = null;
        try {
            press = dataRepository.fetchPressById(id);
            dataRepository.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return press;
    }
}