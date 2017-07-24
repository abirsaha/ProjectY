package projecty
import org.grails.datastore.gorm.neo4j.GraphPersistentEntity;

class LearningObjective {
    /**
     * These Strings store the information used to make the learning objective definition
     * attributes are the dynamic content from the learning objective
     * Boolean to keep track of whether or not to hide the Condition from the Learning Objective
     */
    // FIXME condition is a SQL reserved word
    String condition
    String customCondition
    // FIXME indicator is a SQL reserved word
    String indicator
    String performance
    // Hide condition from objective
    Boolean hideFromLearningObjectiveCondition

    /**
     * the four criteriae of success are stored here
     */
    // text value of each criteria type
    String criteriaAccuracy
    String criteriaQuality
    String criteriaQuantity
    String criteriaSpeed
    String actionWord

    // Store a custom definition
    String definition

    // if the criteria is enabled or not
//    Boolean criteriaAccuracyEnabled
//    Boolean criteriaQualityEnabled
//    Boolean criteriaQuantityEnabled
//    Boolean criteriaSpeedEnabled
//
//    // if the criteria should be hidden from the definition of the learning objective
//    Boolean criteriaAccuracyHidden
//    Boolean criteriaQualityHidden
//    Boolean criteriaQuantityHidden
//    Boolean criteriaSpeedHidden

    // Learning Objective has many criteria, each  criteria has an enumerated type and hidden or not
    LearningObjectiveCriteriaType criteriaType

    /**
     * Relationships to other domain objects
     */
    static belongsTo = [
            imod: Imod,
            actionWordCategory: ActionWordCategory
    ]

    static hasMany = [
            contents: Content,
           // pedagogyTechniques: PedagogyTechnique,
            assessmentTechniques: AssessmentTechnique,
//            scheduleEvents: ScheduleEvent
    ]

    public static final List genericConditions = [
            'Given a problem specification',
            'Students completing this course will be able to',
            'After completing the course, the student will be able to'
    ]

    /**
     * Constraints on the fields of Learning Objective
     */
    static constraints = {
        criteriaType		nullable: true
       // actionWordCategory	nullable: true
        condition			nullable: true
        customCondition		nullable: true
        definition			nullable: true, maxSize: 65000
        criteriaAccuracy	nullable: true
        criteriaQuality		nullable: true
        criteriaQuantity	nullable: true
        criteriaSpeed		nullable: true
        indicator			nullable: true
        performance			nullable: true
        actionWord			nullable: true
        hideFromLearningObjectiveCondition	nullable: true
    }

    static mapWith = "neo4j"
    String toString() {
        definition
    }

    static mapping = {
        id generator:'native'

    }


}
