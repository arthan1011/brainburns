/**
 * Created by Arthan on 04.01.2017.
 */

import {Component} from '@angular/core';
import {Card} from "./model/Card";

@Component({
    selector: "bb-card-form",
    moduleId: module.id,
    styleUrls: ["css/card-form.component.css"],
    templateUrl: "template/card-form.component.html"
})
export class CardFormComponent {
    card: Card = new Card();

    public onNext(): void {
        this.card = new Card();
    }
}