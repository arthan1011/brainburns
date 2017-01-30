/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit, EventEmitter, Output, OnDestroy} from "@angular/core";
import {Desk} from "./model/Desk";
import {Router, ActivatedRoute, Params} from "@angular/router";
import {DeskService} from "./desk.service";
import {Subscription} from "rxjs";
import {DeskCommunicationService} from "./desk-communication.service";

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

    @Output()
    public onNewDesk: EventEmitter<any> = new EventEmitter();

    private updateDesksSub: Subscription;

    constructor(
        private deskService: DeskService,
        private deskCommunicationService: DeskCommunicationService,
        private router: Router,
        private route: ActivatedRoute
    ) {
        this.updateDesksSub = deskCommunicationService.deskListUpdate$
            .subscribe(() => this.updateDesks());
    };

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
            this.selectedDesk = desk;
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