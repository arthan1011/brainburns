/**
 * Created by Arthan on 07.01.2017.
 */

export class Card {
    public id: number;
    public deskId: number;
    public writing: string;
    public transcription: string;
    public meaning: string;

    toString() {
        return `
            writing: ${this.writing}, 
            ${this.transcription ? "transcription: " + this.transcription + "," : ""}
            meaning: ${this.meaning}
        `
    }
}