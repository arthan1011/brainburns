/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit, EventEmitter, Output} from "@angular/core";
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

    public desks: Desk[];
    @Output()
    public onNewDesk: EventEmitter<any> = new EventEmitter();

    constructor(
        private deskService: DeskService,
        private router: Router
    ) {};

    ngOnInit() {
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