/**
 * Created by arthan on 17.01.2017.
 */

import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import {Desk} from "./model/Desk";

import 'rxjs/add/operator/switchMap';

@Component({
    selector: "bb-desk-detail",
    moduleId: module.id,
    templateUrl: "template/desk-detail.component.html"
})
export class DeskDetailComponent implements OnInit {

    desk: Desk = new Desk(1, "English");
    private desks: Desk[] = [
        new Desk(1, "English"),
        new Desk(2, "Kanji"),
        new Desk(3, "Word")
    ];

    constructor(private route: ActivatedRoute) {};

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
        console.log(`Searching for desk with id ${deskId}`);
        let desk = this.desks.filter(desk => desk.id === deskId)[0];
        return Promise.resolve(desk);
    }
}