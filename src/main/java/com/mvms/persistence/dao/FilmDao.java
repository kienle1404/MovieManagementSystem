package com.mvms.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import com.mvms.entity.Film;

public interface FilmDao {
    public void create();
    public void query();
    public void delete();
}
