package org.example

import java.sql.DriverManager

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val jdbcUrl = "jdbc:mysql://localhost:3306/nation" // MariaDB / MySQL

    println("Programmstart!")
    // get the connection
    val connection = DriverManager.getConnection(jdbcUrl, "root", "pw")

    if(connection.isValid(0)) {
        println("Connection erfolgreich")
    }

    // the query is only prepared not executed
    val query = connection.prepareStatement("SELECT * FROM countries where name like 'Y%'")

    // the query is executed and results are fetched
    val result = query.executeQuery()

// an empty list for holding the results
    val nations = mutableListOf<Nation>()

    while(result.next()){
        // getting the value of the name column
        val name = result.getString("name")

        // getting the value of the id column
        val area = result.getFloat("area")
        val code = result.getString("country_code3")

        val neueNation: Nation = Nation(name, area, code)
        nations.add(neueNation)
    }

    println(nations)

    if(connection.isValid(0)) {
        connection.close()
        println("Connection geschlossen")
    }

    println("Programmende!")
}