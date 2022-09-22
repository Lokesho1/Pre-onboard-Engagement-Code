export class Patient {
    constructor(
        public name: String,
        public admissionDate: Date,
        public doctor: {

            "id":number,
            "name": String,
            "specialized": String

        }
    ) { }

}