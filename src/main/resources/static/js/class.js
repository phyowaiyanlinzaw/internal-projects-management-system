export class Amount {

    constructor(basic_design = 5050, detail_design = 0, coding = 0, unit_testing = 0, integrated_testing = 0) {

        this.basic_design = basic_design
        this.detail_design = detail_design
        this.coding = coding
        this.unit_testing = unit_testing
        this.integrated_testing = integrated_testing

    }

}

export class SystemOutline {

    constructor(analysis = false, sys_design = false, coding = false, testing = false, deploy = false, maintenance = false, document = false) {

        this.analysis = analysis
        this.sys_design = sys_design
        this.coding = coding
        this.testing = testing
        this.deploy = deploy
        this.maintenance = maintenance
        this.document = document

    }

}

export class Project {

    constructor({name, start_date, end_date, userId, clientId, background, objective, amount = new Amount(), systemOutLine = new SystemOutline(), architecutre, deliverable}) {

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