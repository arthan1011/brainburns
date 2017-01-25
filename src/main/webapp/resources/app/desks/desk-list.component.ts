/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit, EventEmitter, Output, OnDestroy} from "@angular/core";
import {Desk} from "./model/Desk";
import {Router} from "@angular/router";
import {DeskService} from "./desk.service";
import {Subscription} from "rxjs";

@Component({
    selector: "bb-desk-list",
    moduleId: module.id,
    templateUrl: "html/desk-list.component.html",
    styleUrls: ["css/desk-list.component.css"]
})
export class DeskListComponent implements OnInit, OnDestroy {

    public desks: Desk[];
    @Output()
    public onNewDesk: EventEmitter<any> = new EventEmitter();

    private updateDesksSub: Subscription;

    constructor(
        private deskService: DeskService,
        private router: Router
    ) {
        this.updateDesksSub = deskService.deskListUpdate$.subscribe(() => this.updateDesks());
    };

    ngOnInit() {
        this.updateDesks();
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
        this.router.navigate(["/desks", desk.id]);
    }
}