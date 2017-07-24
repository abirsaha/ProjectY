package projecty
//import grails.neo4j.Neo4jEntity
//import org.grails.datastore.gorm.neo4j.GraphPersistentEntity

class ImodUser {
    String email
    String username
    String password
    String firstName
    String lastName
    String location
    String officeHours
    String webPage
    String phoneNumber

    //static mapWith = "neo4j"


    static hasMany = [owns:Imod]
    static constraints = {
        username 	blank: false,	unique: true
        password 	blank: false
        email	 	unique: false, email: false
        location nullable: true
        officeHours nullable: true
        webPage nullable: true, matches: '^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$'
        phoneNumber nullable: true, matches: /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/
        firstName nullable: false, blank: false, minSize: 3, maxSize: 20
        lastName nullable: false, blank: false, minSize: 3, maxSize: 20
    }

    String toString() {
        username
    }

//    static mapping = {
//        id generator:'native'
//
//    }

}
