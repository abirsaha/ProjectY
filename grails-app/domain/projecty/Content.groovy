package projecty

class Content {

    //Content parentContent

    /*
     ***********************
     * Optional Attributes *
     ***********************
     */

    /**
     * title of the content to be covered
     */
    String topicTitle

    /**
     * gives a text description of the important of a topic
     */
    String priority

    /**
     * This flags that is requires knowledge from outside the course
     */
    // FIXME rename to hasExternalPreRequisite
    Boolean preReq

    Imod imod

    static mapWith = "neo4j"

    static hasMany = [
            /**
             * These are the various Knowledge Dimensio categories that a Content topic can belong to (many)
             */
            contentHasKD: KnowledgeDimension,

            /**
             * the materials for this content topic
             */
           // resources:		Resource,

            /**
             * A more specific topic pertaining to this general topic
             */
            // FIXME rename to externalSubTopic
           // subTopic:		String,

            /**
             * These are the learning objectives that this content topic tries to teach
             */
            //objectives:		LearningObjective,

            /**
             * A more specific content topic pertaining to this general topic
             */
           // subContents:	Content,
    ]

    static belongsTo = [
            hasContent:Imod
    ]

  // static mappedBy = [subContents: 'parentContent']


    /**
     * Describes which attributes an contain a null (empty) value
     */
    static constraints = {
      //  dimensions	nullable:	true
        preReq		nullable:	true
        priority	nullable:	true
        topicTitle	nullable:	true
//        objectives	nullable:	true
//        subContents	nullable:	true
    }

    static mapping = {
        id generator:'native'
    }




    /**
     * Priorities are read only, they cannot be written
     */
    static transients = ['priorities']

    /**
     * Lists all of the possible priorities that a content could have
     * @return array of priorities
     */
    static List priorities() {
        def priorityList = [
                'Critical',
                'Very Important',
                'Good to Know'
        ]
        priorityList
    }

    String toString() {
        topicTitle
    }

    /**
     * [checkRecursion description]
     * @param  currentContent [description]
     * @param  contentToAdd   [description]
     * @param  errors		 [description]
     * @return				[description]
     */
    def checkRecursion(currentContent, contentToAdd, errors) {
        if (currentContent == null) {
            return true
        }
        if (currentContent == contentToAdd) {
            errors.rejectValue('subContents', 'Cannot contain self')
        } else {
            return checkRecursion(currentContent.parentContent, contentToAdd, errors)
        }
    }
}
