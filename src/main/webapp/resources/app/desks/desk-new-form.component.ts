/**
  * Created by arthan on 21.01.2017. | Project brainburns
*/

import {Component} from "@angular/core";
import {Desk} from "./model/Desk";
import {DeskService} from "./desk.service";

@Component({
    moduleId: module.id,
    templateUrl: "html/desk-new-form.component.html",
    styleUrls: [
        "css/desk-new-form.component.css",
        "../css/form.css"
    ]
})
export class DeskNewFormComponent {
    public desk: Desk = new Desk();

    constructor(
        private deskService: DeskService,
    ) {}

    addDesk() {
        this.deskService.createDesk(this.desk);
    }
}