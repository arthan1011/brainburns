/**
    * Created by arthan on 16.01.2017. | Project brainburns
    */

import {Component} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    selector: "bb-desks",
    moduleId: module.id,
    templateUrl: "html/desks.component.html",
    styleUrls: ["css/desks.component.css"]
})
export class DesksComponent {

    constructor(
        private router: Router
    ) {};

    toNewDeskForm() {
        this.router.navigate(["/desks/new"]);
    }
}