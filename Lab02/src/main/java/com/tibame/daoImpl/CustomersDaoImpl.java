package com.tibame.daoImpl;

import com.tibame.dao.IDao;
import com.tibame.entity.Customers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("CustomerDaoImpl") //預設狀態是 singleton 隨著 spring bean factory 同時產生。
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //設定每一個工廠請求產生獨立物件
@Lazy
public class CustomersDaoImpl implements IDao<Customers>{
    private static final Log logger = LogFactory.getLog(CustomersDaoImpl.class);
    private int id;

    @Autowired
    private DataSource dataSource;

    public CustomersDaoImpl() {
        id = (int)(Math.random()*10000)+1;
        logger.debug("CustomersDaoImpl物件活過來了!  編號：" + id);
    }

    @Override
    public Customers queryForObject(String sql, Object... args) throws SQLException {
        // 透過 DataSource 連接工廠
        Customers customers = null;
        if (dataSource == null){
            throw new SQLException("資料來源物件未注入");
        }

        final Connection connection=dataSource.getConnection(); //open
        final PreparedStatement st=connection.prepareStatement(sql);
        int pos = 1;

        for(Object param:args)
        {
            st.setObject(pos,param);
            pos++;
        }

        ResultSet rs=st.executeQuery();
        if(rs.next())
        {
            customers = new Customers();
            customers.setCustomerId(rs.getString("CustomerID"));
            customers.setCompanyName(rs.getString("CompanyName"));
            customers.setAddress(rs.getString("Address"));
            customers.setPhone(rs.getString("Phone"));
            customers.setCountry(rs.getString("Country"));
            customers.setEmail(rs.getString("Email"));
        }
        connection.close();

        return customers;
    }

    @Override
    public List<Customers> queryForList(String sql, Object... args) throws SQLException {
        return null;
    }

    @Override
    public void setDataSource(DataSource datasource) {
        this.dataSource = datasource;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public int number(){
        return this.id;
    }
}
