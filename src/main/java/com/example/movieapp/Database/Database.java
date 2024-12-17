package com.example.movieapp.Database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:h2:file: ./db/watchlist";
    private static final String username = "dabobi";
    private static final String password = "kaka";
    private static JdbcConnectionSource connectionSource;
    private Dao<WatchlistMovieEntity, Long> dao;
    private static Database instance;

    private Database() {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            TableUtils.createTable(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testDB() throws SQLException {
        WatchlistMovieEntity entity = new WatchlistMovieEntity("0", "King King", "big monke", "Action, Adventure",
                2000, null, 0, 8.5);
        dao.create(entity);
    }

    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Dao<WatchlistMovieEntity, Long> getDao() {
        return this.dao;
    }

    private static void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);

    }
}
