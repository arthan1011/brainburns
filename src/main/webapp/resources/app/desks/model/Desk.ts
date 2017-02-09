/**
    * Created by Arthan on 17.01.2017. | Project brainburns
    */

export class Desk {
    animationState: string;

    constructor(
        public id?: number,
        public username?: string,
        public title?: string
    ) {
        this.animationState = 'idle';
    }

}