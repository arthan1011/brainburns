/**
    * Created by arthan on 16.01.2017. | Project brainburns
    */

import {Component, OnDestroy} from "@angular/core";
import {Router} from "@angular/router";
import {DeskService} from "./desk.service";
import {Subscription} from "rxjs";

@Component({
    selector: "bb-desks",
    moduleId: module.id,
    templateUrl: "html/desks.component.html",
    styleUrls: ["css/desks.component.css"]
})
export class DesksComponent implements OnDestroy {
    deskCreatedSub: Subscription;

    constructor(
        private router: Router,
        private deskService: DeskService
    ) {
        this.deskCreatedSub = deskService.deskCreated$.subscribe((status) => this.onNewDeskCreated(status));
    };

    toNewDeskForm() {
        this.router.navigate(["/desks/new"]);
    }

    onNewDeskCreated(status: string) {
        console.log(`Desk creation. Status: ${status}`);
        this.deskService.updateDesks();
    }

    ngOnDestroy() {
        this.deskCreatedSub.unsubscribe();
    }
}