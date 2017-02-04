/**
* Created by Arthan on 04.01.2017. | Project brainburns
*/

import {Component} from '@angular/core';
import {Card} from "./model/Card";
import {CardService} from "./card.service";

@Component({
    selector: "bb-card-form",
    moduleId: module.id,
    styleUrls: [
        "css/card-form.component.css",
        "../css/form.css"
    ],
    templateUrl: "html/card-form.component.html"
})
export class CardFormComponent {
    card: Card = new Card();

    constructor(
        public cardService: CardService
    ) {}

    public onNext(): void {
        this.cardService.createCard(this.card);
        this.card = new Card();
    }

    public getAddButtonLabel(): string {
        let label: string;
        let selectedDesk = this.cardService.selectedDesk;

        if (selectedDesk) {
            label = `Add to ${selectedDesk.title} desk`;
        } else {
            label = "Add";
        }
        return label
    }
}