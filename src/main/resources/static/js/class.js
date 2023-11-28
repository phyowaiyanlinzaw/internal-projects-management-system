export class Project {

    constructor({
        name,
        start_date,
        end_date,
        userId,
        clientId,
        background,
        objective,
        amount = {
            basic_design: 0,
            detail_design: 0,
            coding: 0,
            unit_testing: 0,
            integrated_testing: 0
        },
        systemOutLine = {
            analysis : false, 
            sys_design : false, 
            coding : false, 
            testing : false, 
            deploy : false, 
            maintenance : false, 
            document : false
        },
        architecutre,
        deliverable
    }) {

        this.name = name
        this.start_date = start_date
        this.end_date = end_date
        this.userId = userId
        this.clientId = clientId
        this.background = background
        this.objective = objective
        this.amount = amount
        this.systemOutLine = systemOutLine
        this.architecutre = architecutre
        this.deliverable = deliverable

    }

}