/**
 * Created by arthan on 18.01.2017. | Project brainburns
 */
import {Injectable} from "@angular/core";
import {Desk} from "./model/Desk";
import {Http, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/do";
import "rxjs/add/operator/map";
import "rxjs/add/operator/find";
import "rxjs/add/observable/of";
import {Observable} from "rxjs/Observable";
import {Card} from "./model/Card";
import {Subject} from "rxjs/Subject";
import {Router} from "@angular/router";

const URL_CARD = "/api/card";

@Injectable()
export class CardService {
    public selectedDesk: Desk;
    private addedCardsSubject = new Subject();
    // todo: Maybe it would be better to create card-communication.service
    public addedCards$: Observable<Card[]> = this.addedCardsSubject.asObservable();

    constructor(
        private http: Http,
        private router: Router
    ) {
        router.events.subscribe(event => {
            this.selectedDesk = null;
        })
    }

    selectDesk(desk: Desk) {
        this.selectedDesk = desk;
    }

    createCard(card: Card) {
        card.deskId = this.selectedDesk.id;
        this.http.post(URL_CARD, card)
            .map((res: Response) => res.json().data)
            .subscribe((cards: Card[]) => this.addedCardsSubject.next(cards));
    }
}