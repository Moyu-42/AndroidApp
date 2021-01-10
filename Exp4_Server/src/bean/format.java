public List<Map<String, Object>> query(String sql, Object... params) {
    List<Map<String, Object>> ans = new ArrayList<Map<String, Object>>();
    ResultSet resultset = null;
    ResultSetMetaData rsmd = null;
    try {
        pstmt = conn.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }

        resultset = pstmt.executeQuery();
        rsmd = resultset.getMetaData();

        while (resultset.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String column_label = rsmd.getColumnLabel(i + 1);
                Object column_object = resultset.getObject(column_label);
                map.put(column_label, column_object);
            }
            ans.add(map);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (resultset != null) {
            try {
                resultset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    return ans;
}
