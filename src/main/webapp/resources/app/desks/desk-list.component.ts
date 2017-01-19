/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit} from "@angular/core";
import {Desk} from "./model/Desk";
import {Router} from "@angular/router";
import {DeskService} from "./desk.service";

@Component({
    selector: "bb-desk-list",
    moduleId: module.id,
    templateUrl: "html/desk-list.component.html",
    styleUrls: ["css/desk-list.component.css"]
})
export class DeskListComponent implements OnInit {

    constructor(
        private deskService: DeskService,
        private router: Router
    ) {};

    ngOnInit() {
        this.deskService.getDesks()
            .then(desks => this.desks = desks);
    }

    public desks: Desk[];

    selectDesk(desk: Desk): void {
        console.log(`Navigating to desk ${desk.id}`);
        this.router.navigate(["/desks", desk.id]);
    }
}