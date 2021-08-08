import java.sql.DriverManager
import java.sql.SQLException

object ConnectURL {
    @JvmStatic
    fun main(args: Array<String>) {

        // Create a variable for the connection string.
        val connectionUrl = "jdbc:sqlserver://<server>:<port>;databaseName=AdventureWorks;user=<user>;password=<password>"
        try {
            DriverManager.getConnection(connectionUrl).use { con ->
                con.createStatement().use { stmt ->
                    val SQL = "SELECT TOP 10 * FROM Person.Contact"
                    val rs = stmt.executeQuery(SQL)

                    // Iterate through the data in the result set and display it.
                    while (rs.next()) {
                        println(rs.getString("FirstName") + " " + rs.getString("LastName"))
                    }
                }
            }
        } // Handle any errors that may have occurred.
        catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}