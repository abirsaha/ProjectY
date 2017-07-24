package projecty

class DomainCategory {
    /**
     * Name for the Category
     */
    String name
    int priority

    static belongsTo = [
            /**
             * This is the overarching domain that this category falls under
             */
            learningDomain: LearningDomain
    ]

    static hasMany = [
            /**
             * These are the action words that fall under this domain
             */
            actionWordCategories: ActionWordCategory
    ]

    static mapWith = "neo4j"
    /**
     * Categories are not versioned
     */
//    static mapping = {
//        sort priority: 'asc'
//        version false
//    }

    /**
     * printing the Domain Category will give the display name for the category
     * @return display name
     */
    String toString() {
        name
    }
}