/**
 * Created by arthan on 17.01.2017.
 */

import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {DesksComponent} from "./desks.component";
import {DeskDetailComponent} from "./desk-detail.component";
import {DeskListComponent} from "./desk-list.component";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
    ],
    declarations: [
        DesksComponent,
        DeskDetailComponent,
        DeskListComponent
    ]
})
export class DesksModule {

}