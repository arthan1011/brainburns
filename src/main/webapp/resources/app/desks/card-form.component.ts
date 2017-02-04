/**
 * Created by Arthan on 04.01.2017.
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
        private cardService: CardService
    ) {}

    public onNext(): void {
        this.cardService.createCard(this.card);
        this.card = new Card();
    }
}