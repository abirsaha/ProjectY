package projecty


/**
 * This is the key table,
 * This holds all of the Assessment parts together
 */
class AssessmentTechnique {
    String title
    String description
    String assesmentype
    String procedure
    String duration
    String difficulty
    String type
    String whenToCarryOut
    String whereToCarryOut
    String reference

    AssessmentFeedback assessmentFeedback

    static hasMany = [
            //activityFocus: PedagogyActivityFocus,
            assignedLearningObjective: LearningObjective,
            domainCategory: DomainCategory,
            knowledgeDimension: KnowledgeDimension,
            learningDomain: LearningDomain,
            //userFavorite: ImodUser,
            //user:ImodUser
    ]

    static belongsTo = [
            ImodUser,
            LearningObjective
    ]

    static mapping = {
        description type: 'text'
        version false
    }

    static constraints = {
        //activityFocus nullable: true
        assignedLearningObjective nullable: true
        description nullable: true, blank: true
        procedure nullable: true, blank: true
        duration nullable: true, blank: true
        userFavorite nullable: true
        assessmentFeedback nullable: true
        difficulty nullable: true, blank: true
        type nullable: true, blank: true
        whenToCarryOut nullable: true, blank: true
        whereToCarryOut nullable: true, blank: true
        reference nullable: true, blank: true
        assesmentype nullable: true, blank: true
    }

    String toString() {
        title
    }
}
