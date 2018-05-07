import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


public class DataBase {

    public void createTable() {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://192.168.99.100:5432/vote", "admin", "admin");
        String sql = "CREATE TABLE IF NOT EXISTS task (id int, title varchar(255), content varchar(255), status boolean)";

        try (Connection con = sql2o.beginTransaction()) {
            con.createQuery(sql).executeUpdate();;
        }
    }
    public List<Task> getAllTasks() {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://192.168.99.100:5432/vote", "admin", "admin");
        String sql =
                "SELECT * FROM task";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Task.class);
        }
    }

    public List<Task> fetchCustomers(int id) {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://192.168.99.100:5432/vote", "admin", "admin");
        try (Connection con = sql2o.open()) {
            final String query =
                    "SELECT id, title, content, status FROM test WHERE id = :id";

            return con.createQuery(query)
                    .addParameter("id",id)
                    .executeAndFetch(Task.class);
        }
    }

    public void addTask(Task task) {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://192.168.99.100:5432/vote", "admin", "admin");
        final String insertQuery =
                "INSERT INTO task (id, title, content, status) " +
                        "VALUES (:id, :title, :content, :status)";

        try (Connection con = sql2o.beginTransaction()) {
            con.createQuery(insertQuery)
                    .addParameter("id", task.getId())
                    .addParameter("title", task.getTitle())
                    .addParameter("content", task.getContent())
                    .addParameter("status", task.isStatus())
                    .executeUpdate();
            // Remember to call commit() when a transaction is opened,
            // default is to roll back.
            con.commit();
        }
    }


     public void editTask(Task task) {
         Sql2o sql2o = new Sql2o("jdbc:postgresql://192.168.99.100:5432/vote", "admin", "admin");
         final String insertQuery =
                 "UPDATE task SET title = :title, content = :content, status = :status WHERE id = :id";

         try (Connection con = sql2o.beginTransaction()) {
             con.createQuery(insertQuery)
                     .addParameter("id", task.getId())
                     .addParameter("title", task.getTitle())
                     .addParameter("content", task.getContent())
                     .addParameter("status", task.isStatus())
                     .executeUpdate();
             // Remember to call commit() when a transaction is opened,
             // default is to roll back.
             con.commit();
         }
     }

    public void deleteTask(Task task) {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://192.168.99.100:5432/vote", "admin", "admin");
        final String insertQuery =
                "DELETE FROM task WHERE id = :id";

        try (Connection con = sql2o.beginTransaction()) {
            con.createQuery(insertQuery)
                    .addParameter("id", task.getId())
                    .addParameter("title", task.getTitle())
                    .addParameter("content", task.getContent())
                    .addParameter("status", task.isStatus())
                    .executeUpdate();
            // Remember to call commit() when a transaction is opened,
            // default is to roll back.
            con.commit();
        }
    }

}



