package com.event.event.client.dao;

import javax.sql.DataSource;
import java.sql.*;

public class ClientDAO {

    private final DataSource dataSource;


    public ClientDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(ClientModel clientModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO client (full_name, short_name, contact_data_id, is_active, client_type_id, notes, legal_entity_type_id, tax_info_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, clientModel.getFullName());
            statement.setString(2, clientModel.getShortName());
            statement.setInt(3, clientModel.getContactId());
            statement.setBoolean(4, clientModel.isActive());
            statement.setInt(5, clientModel.getClientTypeId());
            statement.setString(6, clientModel.getNotes());
            statement.setInt(7, clientModel.getLegalEntityType());
            statement.setInt(8, clientModel.getTaxInfo());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
