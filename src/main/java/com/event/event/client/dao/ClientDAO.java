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
            throw new RuntimeException("Error while creating client:", e);
        }
    }

    public ClientModel find(int clientId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, full_name, short_name, contact_data_id, is_active, client_type_id, notes, legal_entity_type_id, tax_info_id from client WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ClientModel clientModel = new ClientModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
            return clientModel;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading client id:" + clientId, e);
        }
    }

    public void remove(int clientId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM client WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot delete client with id: " + clientId, e);
        }
    }

}
