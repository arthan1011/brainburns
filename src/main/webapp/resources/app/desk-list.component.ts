/**
 * Created by arthan on 17.01.2017.
 */

import {Component} from "@angular/core";
import {Desk} from "./model/Desk";

@Component({
    selector: "bb-desk-list",
    moduleId: module.id,
    templateUrl: "template/desk-list.component.html",
    styleUrls: ["css/desk-list.component.css"]
})
export class DeskListComponent {
    public desks: Desk[] = [
        new Desk("English"),
        new Desk("Kanji"),
        new Desk("Word")
    ]
}