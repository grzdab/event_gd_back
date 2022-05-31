package com.event.event.client.dao;

import com.event.event.client.Client;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ClientDAO {

    private final DataSource dataSource;

    public ClientDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(ClientModel clientModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO client (full_name, short_name, contact_data_id, is_active, client_type_id, notes, tax_info_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, clientModel.getFullName());
            statement.setString(2, clientModel.getShortName());
            statement.setInt(3, clientModel.getContactId());
            statement.setBoolean(4, clientModel.isActive());
            statement.setInt(5, clientModel.getClientTypeId());
            statement.setString(6, clientModel.getNotes());
            statement.setInt(7, clientModel.getTaxInfo());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            clientModel.setId(resultSet.getObject(1, UUID.class)); // wyciaga z kolumny 1 obiekt i zamienia go ma uuid
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating client:", e);
        }
    }

    public ClientModel find(String clientId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, full_name, short_name, contact_data_id, is_active, client_type_id, notes, tax_info_id from client WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, clientId);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ClientModel clientModel = new ClientModel(rs.getObject(1, UUID.class), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
            return clientModel;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading client id:" + clientId, e);
        }
    }

    public void remove(String clientId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM client WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot delete client with id: " + clientId, e);
        }
    }

    public List<ClientModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, full_name, short_name, contact_data_id, is_active, client_type_id, notes, tax_info_id from client";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<ClientModel> clients = new ArrayList<>();
            while (rs.next()) {
                ClientModel clientModel = new ClientModel(rs.getObject(1, UUID.class), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
                clients.add(clientModel);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading clients", e);
        }
    }

    public void update(String clientId, Client client) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE client SET full_name = ?, short_name = ?, contact_data_id = ?, is_active = ?, client_type_id = ?, notes = ?, tax_info_id = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, client.getFullName());
            statement.setString(2, client.getShortName());
            statement.setInt(3, client.getContact().getId());
            statement.setBoolean(4, client.isActive());
            statement.setInt(5, client.getClientType().getId());
            statement.setString(6, client.getNotes());
            statement.setInt(7, client.getTaxInfo().getId());
            statement.setString(8, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("You cannot update client with id: " + clientId, e);
        }
    }

}
