package projecty

class LearningDomain {
    String name

    static hasMany=[
            domainCategories: DomainCategory
    ]

    static constraints = {
    }

    static mapWith = "neo4j"

    static mapping = {
        id generator:'native'
    }

    String toString() {
        name
    }
}