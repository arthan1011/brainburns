/**
  * Created by arthan on 21.01.2017. | Project brainburns
*/

import {Component} from "@angular/core";
import {Desk} from "./model/Desk";
import {DeskService} from "./desk.service";
import {DeskCommunicationService} from "./desk-communication.service";
import {Response} from "@angular/http";
import {NotificationService} from "../notification.service";

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
        private deskCommunicationService: DeskCommunicationService,
        private notificationService: NotificationService
    ) {}

    addDesk() {
        this.deskService.createDesk(this.desk)
            .subscribe(
                () => this.deskCommunicationService.deskCreated("success"),
                (error: Response) => this.notificationService.error(error.json().message)
            );
        this.desk = new Desk();
    }
}