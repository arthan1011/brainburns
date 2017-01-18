/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import {Desk} from "./model/Desk";

import 'rxjs/add/operator/switchMap';
import {DeskService} from "./desk.service";

@Component({
    selector: "bb-desk-detail",
    moduleId: module.id,
    templateUrl: "html/desk-detail.component.html"
})
export class DeskDetailComponent implements OnInit {

    desk: Desk = new Desk(1, "English");
    private desks: Desk[] = this.deskService.getDesks();

    constructor(
        private deskService: DeskService,
        private route: ActivatedRoute
    ) {};

    ngOnInit() {
        this.route.params
            .switchMap((params: Params) => {
                return this.findDesk(+params['id']);
            })
            .subscribe((desk: Desk) => {
                // console.log(`Current desk with id ${desk.id}`);
                this.desk = desk
            });
    }

    private findDesk(deskId: number): Promise<Desk> {
        return this.deskService.getDesk(deskId);
    }
}