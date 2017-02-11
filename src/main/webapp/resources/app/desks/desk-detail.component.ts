/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import {Desk} from "./model/Desk";

import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/distinctUntilChanged';

import {DeskService} from "./desk.service";
import {Observable} from "rxjs/Observable";
import {Card} from "./model/Card";
import {CardService} from "./card.service";

@Component({
    selector: "bb-desk-detail",
    moduleId: module.id,
    templateUrl: "html/desk-detail.component.html",
    styleUrls: ["css/desk-detail.component.css"]
})
export class DeskDetailComponent implements OnInit {

    desk: Desk;
    cards: Card[];

    constructor(
        private deskService: DeskService,
        private cardService: CardService,
        private route: ActivatedRoute
    ) {};

    ngOnInit() {
        this.route.params
            .distinctUntilChanged()
            .do((params: Params) => this.getCards(+params['id']))
            .switchMap((params: Params) => {
                return this.findDesk(+params['id']);
            })
            .subscribe((desk: Desk) => {
                this.desk = desk
            });
    }

    private findDesk(deskId: number): Observable<Desk> {
        return this.deskService.getDesk(deskId);
    }

    private getCards(deskId: number) {
        this.cardService.getCardsForDesk(deskId)
            .subscribe((cards: Card[]) => {
                this.cards = cards
            });
    }
}