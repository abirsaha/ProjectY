package projecty

class Author {

    String name
    Date dob

    static hasMany = [ books: Book ]
    static constraints = {
    }
}
