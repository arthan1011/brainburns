/**
* Created by arthan on 17.01.2017.
*/

import {
    Component, OnInit, EventEmitter, Output, OnDestroy, trigger, transition, animate,
    keyframes, style, state
} from "@angular/core";
import {Desk} from "./model/Desk";
import {Router, ActivatedRoute, Params} from "@angular/router";
import {DeskService} from "./desk.service";
import {Subscription} from "rxjs";
import {DeskCommunicationService} from "./desk-communication.service";
import {CardService} from "./card.service";
import {Card} from "./model/Card";

const NEW_CARD_ANIMATION_DURATION = 1000;

@Component({
    selector: "bb-desk-list",
    moduleId: module.id,
    templateUrl: "html/desk-list.component.html",
    styleUrls: ["css/desk-list.component.css"],
    animations: [
        trigger('addNewCard', [
            transition('idle => adding', [
                animate(NEW_CARD_ANIMATION_DURATION, keyframes([
                    style({
                        "background-color": "#6B5661",
                        offset: 0
                    }),
                    style({
                        "background-color": "#a1b96c",
                        offset: 0.1
                    }),
                    style({
                        "background-color": "#6b5661",
                        offset: 1.0
                    })
                ]))
            ])
        ]),
        trigger('tip_addNewCard', [
            transition('idle => adding', [
                animate(NEW_CARD_ANIMATION_DURATION, keyframes([
                    style({
                        "border-color": "transparent #6B5661 transparent",
                        offset: 0
                    }),
                    style({
                        "border-color": "transparent #a1b96c transparent",
                        offset: 0.1
                    }),
                    style({
                        "border-color": "transparent #6B5661 transparent",
                        offset: 1.0
                    })
                ]))
            ])
        ])
    ]
})
export class DeskListComponent implements OnInit, OnDestroy {

    public desks: Desk[];
    public deskListMode: string;
    selectedDesk: Desk;
    public newCards: {[key: string]: number} = {};
    public cardsStates: {[key: string]: string} = {};

    @Output()
    public onNewDesk: EventEmitter<any> = new EventEmitter();

    private updateDesksSub: Subscription;
    private addCardsSub: Subscription;

    constructor(
        private deskService: DeskService,
        private cardService: CardService,
        private deskCommunicationService: DeskCommunicationService,
        private router: Router,
        private route: ActivatedRoute
    ) {
        this.updateDesksSub = deskCommunicationService.deskListUpdate$
            .subscribe(() => this.updateDesks());
        this.addCardsSub = this.cardService.addedCards$
            .subscribe((cards: Card[]) => this.onCardsAdded(cards))
    };

    onCardsAdded(cards: Card[]) {
        console.log("Changing animation state for selected desk");
        console.log("Animation states before", this.cardsStates);
        this.cardsStates[this.selectedDesk.id] = 'adding';
        console.log("Animation states after", this.cardsStates);

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
        this.addCardsSub.unsubscribe();
    }

    private updateDesks() {
        console.log("Updating desks...");
        this.deskService.getDesks()
            .subscribe(desks => {
                this.desks = desks;
                this.setIdleAnimationState();
            });
    }

    private setIdleAnimationState() {
        this.desks.forEach((desk) => {
            this.cardsStates[desk.id] = 'idle'
        });
    }

    newDesk() {
        this.onNewDesk.emit();
    }

    selectDesk(desk: Desk): void {
        if (this.isSelectMode()) {
            this.selectedDesk = desk;
            console.log(`Desk #${this.selectedDesk.id} "${this.selectedDesk.title}" selected`);
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

    onNewCardAnimationDone() {
        console.log("Animation done", this.cardsStates);
        if (this.selectedDesk) {
            this.cardsStates[this.selectedDesk.id] = "idle";
        }
    }
}