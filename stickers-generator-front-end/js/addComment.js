export default class AddComment {

    constructor(fromInput, forInput) {
        this.fromInput = fromInput,
            this.forInput = forInput;
    }

    add() {

        this.fromInput.style.border = "0";
        this.forInput.innerHTML = this.fromInput.value.slice(0, 16);

    }

}