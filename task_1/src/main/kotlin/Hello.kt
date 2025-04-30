import java.sql.DriverManager

fun main() {
    println("Hello from Kotlin!")
    
    Class.forName("org.sqlite.JDBC")
    DriverManager.getConnection("jdbc:sqlite::memory:").use { conn ->
        conn.createStatement().executeUpdate("CREATE TABLE test (id INT, name TEXT)")
        conn.createStatement().executeUpdate("INSERT INTO test VALUES (1, 'Hello')")
        val rs = conn.createStatement().executeQuery("SELECT name FROM test WHERE id = 1")
        if (rs.next()) {
            println("Result from SQLite: ${rs.getString("name")}")
        }
    }
}