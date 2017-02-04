/**
* Created by arthan on 17.01.2017.
*/

import {Component, OnInit, EventEmitter, Output, OnDestroy} from "@angular/core";
import {Desk} from "./model/Desk";
import {Router, ActivatedRoute, Params} from "@angular/router";
import {DeskService} from "./desk.service";
import {Subscription} from "rxjs";
import {DeskCommunicationService} from "./desk-communication.service";
import {CardService} from "./card.service";
import {Card} from "./model/Card";

@Component({
    selector: "bb-desk-list",
    moduleId: module.id,
    templateUrl: "html/desk-list.component.html",
    styleUrls: ["css/desk-list.component.css"]
})
export class DeskListComponent implements OnInit, OnDestroy {

    public desks: Desk[];
    public deskListMode: string;
    selectedDesk: Desk;
    public newCards: {[key: string]: number} = {};

    @Output()
    public onNewDesk: EventEmitter<any> = new EventEmitter();

    private updateDesksSub: Subscription;

    constructor(
        private deskService: DeskService,
        private cardService: CardService,
        private deskCommunicationService: DeskCommunicationService,
        private router: Router,
        private route: ActivatedRoute
    ) {
        this.updateDesksSub = deskCommunicationService.deskListUpdate$
            .subscribe(() => this.updateDesks());
        this.cardService.addedCards$
            .subscribe(this.onCardsAdded.bind(this))
    };

    onCardsAdded(cards: Card[]) {
        cards.forEach(card => {
            console.log(`Card with id "${card.id}" added to desk #${card.deskId}`);
            if (this.newCards[card.deskId]) {
                this.newCards[card.deskId]++
            } else {
                this.newCards[card.deskId] = 1;
            }
        }, this);
        console.log("new Cards: ", this.newCards);
    }

    ngOnInit() {
        this.updateDesks();
        this.route.queryParams
            .subscribe((params: Params) => {
                this.deskListMode = params['deskListMode'];
            })
    }

    ngOnDestroy() {
        this.updateDesksSub.unsubscribe();
    }

    private updateDesks() {
        console.log("Updating desks...");
        this.deskService.getDesks()
            .subscribe(desks => this.desks = desks);
    }

    newDesk() {
        this.onNewDesk.emit();
    }

    selectDesk(desk: Desk): void {
        if (this.isSelectMode()) {
            console.log(`Desk #${desk.id} "${desk.title}" selected`);
            this.selectedDesk = desk;
            this.cardService.selectDesk(desk);
        } else {
            this.router.navigate(["/desks", desk.id]);
        }
    }

    isSelectMode(): boolean {
        return this.deskListMode === 'select';
    }

    isSelected(desk: Desk): boolean {
        return this.selectedDesk && this.selectedDesk.id === desk.id;
    }
}