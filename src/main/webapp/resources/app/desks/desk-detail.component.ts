/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import {Desk} from "./model/Desk";

import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/distinctUntilChanged';

import {DeskService} from "./desk.service";
import {Observable} from "rxjs/Observable";

@Component({
    selector: "bb-desk-detail",
    moduleId: module.id,
    templateUrl: "html/desk-detail.component.html"
})
export class DeskDetailComponent implements OnInit {

    desk: Desk/* = new Desk(1, "admin", "English")*/;

    constructor(
        private deskService: DeskService,
        private route: ActivatedRoute
    ) {};

    ngOnInit() {
        this.route.params
            .distinctUntilChanged()
            .switchMap((params: Params) => {
                return this.findDesk(+params['id']);
            })
            .subscribe((desk: Desk) => {
                this.desk = desk
            });
    }

    private findDesk(deskId: number): Observable<Desk> {
        return this.deskService.getDesk(deskId);
    }
}