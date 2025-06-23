package com.hanry.minilibrary.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hanry.minilibrary.model.DiaryEntry;

public final class DiaryDAO {
    private static final String INS = """
        INSERT OR REPLACE INTO diary_entries(id,title,timestamp,mood,content)
        VALUES (?,?,?,?,?)
    """;
    public static void saveAll(List<DiaryEntry> list) {
        try (Connection c = DBUtil.get(); PreparedStatement ps = c.prepareStatement(INS)) {
            for (DiaryEntry d : list) {
                ps.setInt   (1,d.getId());
                ps.setString(2,d.getTitle());
                ps.setString(3,d.getTimestamp().toString());
                ps.setString(4,d.getMood());
                ps.setString(5,d.getContent());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<DiaryEntry> loadAll() {
        List<DiaryEntry> out = new ArrayList<>();
        try (Connection c = DBUtil.get(); Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM diary_entries ORDER BY id")) {
            while (rs.next()) {
                DiaryEntry d = new DiaryEntry(0, "", LocalDateTime.now(), "", "");
                d.setId(rs.getInt("id"));
                d.setTitle(rs.getString("title"));
                d.setTimestamp(LocalDateTime.parse(rs.getString("timestamp")));
                d.setMood(rs.getString("mood"));
                d.setContent(rs.getString("content"));
                out.add(d);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return out;
    }
} 