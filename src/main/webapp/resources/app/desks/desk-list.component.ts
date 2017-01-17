/**
 * Created by arthan on 17.01.2017.
 */

import {Component} from "@angular/core";
import {Desk} from "./model/Desk";
import {Router} from "@angular/router";

@Component({
    selector: "bb-desk-list",
    moduleId: module.id,
    templateUrl: "template/desk-list.component.html",
    styleUrls: ["css/desk-list.component.css"]
})
export class DeskListComponent {

    constructor(private router: Router) {};

    public desks: Desk[] = [
        new Desk(1, "English"),
        new Desk(2, "Kanji"),
        new Desk(3, "Word")
    ];

    selectDesk(desk: Desk): void {
        console.log(`Navigating to desk ${desk.id}`);
        this.router.navigate(["/desks", desk.id]);
    }
}