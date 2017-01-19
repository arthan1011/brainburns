/**
 * Created by arthan on 17.01.2017.
 */

import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {DesksComponent} from "./desks.component";
import {DeskDetailComponent} from "./desk-detail.component";
import {DeskListComponent} from "./desk-list.component";
import {DesksRoutingModule} from "./desks-routing.module";
import {DeskService} from "./desk.service";
import {HttpModule} from "@angular/http";

@NgModule({
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        DesksRoutingModule
    ],
    declarations: [
        DesksComponent,
        DeskDetailComponent,
        DeskListComponent
    ],
    providers: [
        DeskService
    ]
})
export class DesksModule {

}