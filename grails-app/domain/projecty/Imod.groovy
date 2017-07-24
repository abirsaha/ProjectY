package projecty

//import grails.neo4j.Neo4jEntity
//import org.grails.datastore.gorm.neo4j.GraphPersistentEntity

class Imod  {
    /*
	 ***********************
	 * Required Attributes *
	 ***********************
	 */
    String	name
    String	url
    String	subjectArea

    /*
     ***********************
     * Optional Attributes *
     ***********************
     */
    String	overview
    String	courseSemester
    String	courseLocation
    String	imodNumber
    Integer	numberOfSeats
    Integer	creditHours
    Boolean saved

    /*
     *****************
     * Relationships *
     *****************
     */
    //CoursePolicy	coursePolicy
    //Schedule		schedule

//    static belongsTo = [
//            /**
//             * instructor that owns the course
//             */
//            owner: ImodUser
//    ]

    static belongsTo = [owner:ImodUser]

    static hasMany = [
            /**
             * all of the learning objectives that will be used in this IMOD
             */
            provides:	LearningObjective,

            /**
             * All instructors who have access this couse
             */
            //instructors:		Instructor,

            /**
             * Components of the course
             */
          //  courseComponents:	CourseComponentCode,

            /**
             * People that is course caters to
             */
           // audience:			Audience,

            /**
             * All of the Topics and Resources to be covered in the course
             */
            contains:			Content,

            /**
             * All of the Topics and Resources to be covered in the course
             */
           // prefs: 				SyllabusPrefs
    ]

    /*
     *****************
     * Configuration *
     *****************
     */

    /**
     * Which attributes are optional or required
     */
    static constraints = {
        // These are required to create an IMOD
        name			nullable: false,	blank: false
        subjectArea		nullable: false,	blank: false
        url 			nullable: true
        // These are optional string atrributes of an IMOD
        overview			nullable: true, maxSize: 65000
        courseSemester		nullable: true
        courseLocation		nullable: true

        imodNumber			nullable: true

        numberOfSeats		nullable: false,		min: 1,		scale: 1
        creditHours			nullable: false,		min: 0,		scale: 1
        saved				nullable: false
        // Domains belonging to an IMOD
//        coursePolicy	nullable: true
//        schedule		nullable: true
    }

    String toString() {
        name
    }
   // static mapWith = "neo4j"

//    static mapping = {
//        id generator:'native'
//
//    }





}
